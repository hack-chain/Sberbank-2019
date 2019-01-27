<template>
  <div>
    <b-card :class="{'mt-3': true, 'border-primary': personalData.status == 'ok'}">
      <div class="d-flex">
        <div class="avatarBox mr-4">
          <img class="avatarImage" v-if="avatar" :src="avatar">
        </div>
        <div class="align-self-center">
          {{ name ? name : personalData.phoneNumber }}
          <br>
          <b-badge v-if="personalData.status == 'ok'" variant="primary">Готов</b-badge>
          <b-badge v-if="personalData.status == 'rejected'" variant="danger">Отказался</b-badge>
          <b-badge v-if="personalData.status == 'waiting'" variant="secondary">Ожидаем ответа</b-badge>
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
    name: null
  })
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
