<template>
  <div>
    <b-card :class="{'mt-3': true, 'border-success': personalData.paid}" @click="showRemindModal">
      <div class="d-flex">
        <div class="avatarBox mr-4">
          <img class="avatarImage" v-if="avatar" :src="avatar">
        </div>
        <div class="align-self-center">
          {{ name ? name : phoneNumber }}
          <br>
          <b-badge v-if="personalData.paid" variant="success">Оплатил</b-badge>
        </div>
      </div>
    </b-card>

    <b-modal
      :id="'modal' + personalData.id"
      ref="modal"
      hide-footer
      hide-header
      class="text-center"
    >
      <h3 class="my-5">Напомнить о платеже?</h3>
      <b-button class="mr-2" variant="outline-success" @click="$refs.modal.hide()">Напомнить</b-button>
      <b-button variant="outline-secondary" @click="$refs.modal.hide()">Отмена</b-button>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "PersonalCard",
  props: {
    personalData: Object
  },

  data: () => ({
    avatar: null,
    name: null,
    phoneNumber: null
  }),

  created() {
    fetch("http://localhost:8080/users/" + this.personalData.id)
      .then(response => {
        return response.json();
      })
      .then(data => {
        this.avatar = data.photoUrl;
        this.name = data.name;
        this.phoneNumber = data.phoneNumber;
      });
  },

  methods: {
    showRemindModal() {
      if (!this.personalData.paid) {
        this.$refs.modal.show();
      }
    }
  }
};
</script>

<style>
.avatarBox {
  width: 50px;
  height: 50px;
}

.avatarImage {
  border-radius: 100%;
  max-height: 50px;
  max-width: 50px;
}
</style>
