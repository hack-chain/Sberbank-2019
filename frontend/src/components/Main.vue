<template>
  <div id="app" class="text-center">
    <b-container>
      <PaymentCard v-for="p of payments" :paymentData="p" :key="p.id"/>
      <b-button class="mt-4" variant="outline-success" href="/new">+</b-button>
    </b-container>
  </div>
</template>

<script>
import PaymentCard from "./PaymentCard.vue";
export default {
  name: "Main",
  components: { PaymentCard },
  data: () => ({
    payments: []
  }),
  created() {
    var id = localStorage.getItem("id");
    if (!id) {
      window.location.href = "/number";
    }

    fetch("http://localhost:8080/orders/author/" + id)
      .then(response => {
        if (response.ok) {
          return response.json();
        }
      })
      .then(data => {
        var paymentList = data._embedded.orderList;
        for (let p of paymentList) {
          fetch("http://localhost:8080/orders/" + p.id)
            .then(response => {
              if (response.ok) {
                return response.json();
              }
            })
            .then(data => {
              var payment = {};
              payment.name = data.description;
              payment.cost = data.cost;
              payment.people = [];
              for (p in data.map) {
                console.log(data.map[p]);
                if (data.map[p] == "PAID") {
                  payment.people.push({ id: p, paid: true });
                } else if (data.map[p] == "NOT_PAID") {
                  payment.people.push({ id: p, paid: false });
                }
              }
              this.payments.push(payment);
            });
        }
      });
  }
};
</script>

<style>
</style>
