import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import store from './store'


Vue.use(Vuetify, {
  theme: {
    primary: "#2F343A",
    secondary: "#607D8B",
    accent: "#FF6D00",
    error: "#FF6D00",
    warning: "#FBC02D",
    info: "#2196f3",
    success: "#4caf50"
  }
})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>'
})
