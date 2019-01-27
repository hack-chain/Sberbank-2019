package com.Sberbank.Sberbank.Orders;

import com.Sberbank.Sberbank.SMS.SMS;
import com.Sberbank.Sberbank.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @PostMapping("/orders")
    ResponseEntity<Resource<Order>> newOrder(@RequestBody Order order) {

        Order newOrder = orderRepository.save(order);

        HashMap<Long, Status> map = newOrder.getMap();
        int nUsers = map.size();
        Integer sumInt = newOrder.getCost() / nUsers;
        for (Map.Entry<Long, Status> entry: map.entrySet()){
            SMS.main("https://qiwi.com/payment/form/99?extra%5B%27account%27%5D=79850937035&amountInteger="+ sumInt + "&amountFraction=%sum_fraction%&extra%5B%27comment%27%5D="+ Long.toString(newOrder.getId()) +"&currency=643&blocked[0]=account&blocked[1]=sum&blocked[2]=comment", Long.toString(entry.getKey()));
        }

        return ResponseEntity
                .created(linkTo(methodOn(OrderController.class).one(newOrder.getId())).toUri())
                .body(assembler.toResource(newOrder));
    }
}


    /*@DeleteMapping("/orders/{id}/cancel")
    ResponseEntity<ResourceSupport> cancel(@PathVariable Long id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));


        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't cancel an order that is in the " + " status"));
    }

    @PutMapping("/orders/{id}/complete")
    ResponseEntity<ResourceSupport> complete(@PathVariable Long id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));


        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new VndErrors.VndError("Method not allowed", "You can't complete an order that is in the " + " status"));
    }*/