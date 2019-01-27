<template>
  <div id="app">
    <b-container class="text-center">
      <b-input class="mt-5" v-model="phoneNumber" placeholder="Номер телефона"/>
      <br>
      <b-button variant="primary" @click="send">Отправить</b-button>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "AskForNumber",
  data: () => ({
    name: null,
    avatar: null,
    phoneNumber: null
  }),
  methods: {
    send() {}
  }
};
</script>

<style>
</style>
