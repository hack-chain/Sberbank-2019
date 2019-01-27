<template>
  <div id="app">
    <b-container>
      <b-form>
        <b-form-input
          class="mt-3"
          size="lg"
          id="nameInput"
          v-model="name"
          placeholder="Название покупки"
        />
        <b-form-input class="mt-3" size="lg" id="amountInput" v-model="amount" placeholder="Сумма"/>
        <h3 class="mt-4">Участники покупки:</h3>
        <b-form-checkbox class="mt-3" v-model="ownerPays">Включить меня</b-form-checkbox>
      </b-form>
      <InitialCard v-if="ownerPays" :personalData="{phoneNumber: ownerNumber, status: 'ok'}"/>
      <InitialCard v-for="p of people" :personalData="p" :key="p.phoneNumber"/>
      <div class="text-center">
        <b-button class="mt-3" variant="outline-primary" @click="$refs.modal.show()">+</b-button>
      </div>
      <b-button
        class="mt-5"
        variant="primary"
        @click="addPayment"
        :disabled="!canAdd"
      >Добавить покупку</b-button>
    </b-container>

    <b-modal ref="modal" hide-footer hide-header class="text-center">
      <b-form-input
        class="my-3"
        size="lg"
        id="newPhoneNumberInput"
        v-model="newPerson.phoneNumber"
        placeholder="Сумма"
      />
      <b-button class="mr-2" variant="outline-primary" @click="addPerson">Добавить</b-button>
      <b-button variant="outline-secondary" @click="$refs.modal.hide()">Отмена</b-button>
    </b-modal>
  </div>
</template>

<script>
import InitialCard from "./InitialCard.vue";
export default {
  name: "New",
  components: { InitialCard },
  data: () => ({
    name: null,
    amount: null,
    people: [],
    ownerPays: true,
    newPerson: {
      phoneNumber: "+7"
    },
    ownerNumber: null
  }),

  created() {
    this.ownerNumber = window.localStorage.getItem("phoneNumber");
  },

  methods: {
    addPerson() {
      this.newPerson.status = "waiting";
      this.people.push(this.newPerson);
      this.newPerson = {};
      this.$refs.modal.hide();
    },

    addPayment() {}
  },

  computed: {
    canAdd() {
      return this.name && this.amount && this.people.length;
    }
  }
};
</script>

<style>
</style>
