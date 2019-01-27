<template>
  <b-card class="mt-5">
    <h3 class="mt-2">{{ paymentData.name }}</h3>
    <h2>{{ paymentData.sum / 100 }} &#8381;</h2>
    <PersonalCard v-for="p of paymentData.people" :personalData="p" :key="p.id"/>
    <div class="progressBar bg-success" :style="{width: percentPaid + '%'}"></div>
  </b-card>
</template>

<script>
import PersonalCard from "./PersonalCard.vue";
export default {
  name: "PaymentCard",
  components: { PersonalCard },
  props: {
    paymentData: Object
  },

  computed: {
    percentPaid() {
      var nTotal = this.paymentData.people.length;
      var nPaid = this.paymentData.people.filter(d => d.paid).length;
      if (this.authorPays) {
        nTotal += 1;
        nPaid += 1;
      }

      return (nPaid / nTotal) * 100;
    }
  }
};
</script>

<style>
.progressBar {
  height: 10px;
  position: absolute;
  top: 0;
  left: 0;
}
</style>
