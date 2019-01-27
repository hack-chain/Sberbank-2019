<template></template>

<script>
export default {
  name: "AfterCreated",
  created() {
    var url = new URL(window.location.href);
    var name =
      url.searchParams.get("first_name") +
      " " +
      url.searchParams.get("last_name");
    localStorage.setItem("name", name);
    var avatar = url.searchParams.get("photo");
    localStorage.setItem("avatar", avatar);

    var headers = new Headers();
    headers.append("Content-type", "application/json");
    fetch("http://javathon.kolebor.ru:8080/users", {
      method: "POST",
      headers: headers,
      body: JSON.stringify({
        name: name,
        phoneNumber: localStorage.getItem("phoneNumber"),
        photoUrl: avatar
      })
    }).then(response =>
      fetch(
        "http://javathon.kolebor.ru:8080/users/phone/" +
          localStorage.getItem("phoneNumber")
      )
        .then(result => {
          return response.json();
        })
        .then(data => {
          localStorage.setItem("id", data.id);
          window.location.href = "/";
        })
    );
  }
};
</script>

<style>
</style>
