<template>
  <div>
    <b-card :class="{'mt-3': true, 'border-primary': personalData.isOwner}">
      <div class="d-flex">
        <div class="avatarBox mr-4">
          <img class="avatarImage" v-if="avatar" :src="avatar">
        </div>
        <div class="align-self-center">
          {{ name ? name : personalData.phoneNumber }}
          <br>
        </div>
      </div>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "InitalCard",
  props: {
    personalData: Object
  },

  data: () => ({
    avatar: null,
    name: null,
    id: null
  }),

  created() {
    var vm = this;
    fetch(
      "http://178.128.201.98:8080/users/phone/" + vm.personalData.phoneNumber
    )
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error();
        }
      })
      .then(data => {
        (vm.avatar = data.photoUrl), (vm.name = data.name), (vm.id = data.id);
      })
      .catch(error => {
        var headers = new Headers();
        headers.append("Content-type", "application/json");
        fetch("http://178.128.201.98:8080/users", {
          method: "POST",
          headers: headers,
          body: JSON.stringify({
            name: null,
            phoneNumber: vm.personalData.phoneNumber,
            photoUrl: null
          })
        }).then(response =>
          fetch(
            "http://178.128.201.98:8080/users/phone/" +
              vm.personalData.phoneNumber
          )
            .then(result => {
              return response.json();
            })
            .then(data => {
              vm.id = data.id;
            })
        );
      });
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
