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
    send() {
      localStorage.setItem("phoneNumber", this.phoneNumber);
      fetch("http://178.128.201.98:8080/users/phone/" + this.phoneNumber)
        .then(response => {
          if (response.ok) {
            return response.json();
          } else {
            window.location.href = "/auth";
          }
        })
        .then(data => {
          localStorage.setItem("id", data.id);
          localStorage.setItem("avatar", data.photoUrl);
          localStorage.setItem("name", data.name);
          window.location.href = "/";
        });
    }
  }
};
</script>

<style>
</style>
