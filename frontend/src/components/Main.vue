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
    payments: [
      {
        id: "123",
        name: "Интернет",
        sum: 40000,
        authorPays: false,
        people: [
          {
            id: "3",
            paid: false,
            remindDaily: true,
            remindDate: null
          },

          {
            id: "4",
            paid: true,
            remindDaily: true,
            remindDate: null
          },

          {
            id: null,
            phoneNumber: "+79774713293",
            paid: true
          }
        ]
      }
    ]
  })
};
</script>

<style>
</style>
