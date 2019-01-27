import Vue from "vue";
import VueRouter from "vue-router";
import BootstrapVue from "bootstrap-vue";
import Auth from "./components/Auth.vue";
import Main from "./components/Main.vue";
import New from "./components/New.vue";
import AskForNumber from "./components/AskForNumber.vue";

const routes = {
  "/": Main,
  "/auth": Auth,
  "/new": New,
  "/number": AskForNumber
};

Vue.config.productionTip = false;
Vue.use(VueRouter);
Vue.use(BootstrapVue);

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

new Vue({
  el: "#app",
  data: {
    currentRoute: window.location.pathname
  },
  computed: {
    ViewComponent() {
      return routes[this.currentRoute];
    }
  },
  render(h) {
    return h(this.ViewComponent);
  }
});
