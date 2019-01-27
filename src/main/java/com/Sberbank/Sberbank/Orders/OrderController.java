package com.Sberbank.Sberbank.Orders;

import com.Sberbank.Sberbank.Payments.QiwiPayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class OrderController {

    @Autowired
    JdbcTemplate jdbcTemplate;


    class OrderRowMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getLong("id"));
            order.setDescription(rs.getString("description"));
            order.setAuthor(rs.getLong("author"));
            order.setCost(rs.getInt("cost"));
            return order;
        }
    }
    public List <Order> findAll(Long author) {
        return jdbcTemplate.query("select * from orders where author=?", new Object[] {author}, new OrderRowMapper());
    }

    private final OrderRepository orderRepository;
    private final OrderResourceAssembler assembler;

    OrderController(OrderRepository orderRepository,
                    OrderResourceAssembler assembler) {

        this.orderRepository = orderRepository;
        this.assembler = assembler;
    }

    @GetMapping("/orders")
    Resources<Resource<Order>> all() {

        List<Resource<Order>> orders = orderRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orders,
                linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @GetMapping("/orders/{id}")
    Resource<Order> one(@PathVariable Long id) {
        return assembler.toResource(
                orderRepository.findById(id)
                        .orElseThrow(() -> new OrderNotFoundException(id)));
    }

    @GetMapping("/orders/author/{author}")
    Resources<Resource<Order>> getByAuthor(@PathVariable Long author) {
        List<Resource<Order>> orders = findAll(author).stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(orders,
                linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    private Order findById(Long id) {
        return jdbcTemplate.queryForObject("select * from orders where id=?", new Object[]{
                        id
                },
                new BeanPropertyRowMapper<Order>(Order.class));
    }

    @PostMapping("/orders")
    ResponseEntity<Resource<Order>> newOrder(@RequestBody Order order) {

        Order newOrder = orderRepository.save(order);

        ArrayList<Long> payers = new ArrayList<Long> (newOrder.getMap().keySet());
        ArrayList<Long> usedPayers = new ArrayList<Long>();
        int temp = 0;
        while (true) {
            for (Long userId : payers) {
                if (usedPayers.contains(userId)) {
                    continue;
                }
                System.out.println(userId);
                if (QiwiPayer.checkPayment(newOrder.getCost()/newOrder.getMap().size(), "0", "79850937035", userId, newOrder.getId(), "1c9111a7bf73364806348fa580ddae8c")) {
                    usedPayers.add(userId);
                    Order tmp = findById(newOrder.getId());
                    tmp.getMap().replace(userId, Status.ACCEPTED);
                }
            }

            if(usedPayers.size() == payers.size()) {
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("got interrupted");
            }
        }

        return ResponseEntity
                .created(linkTo(methodOn(OrderController.class).one(newOrder.getId())).toUri())
                .body(assembler.toResource(newOrder));
    }
}
