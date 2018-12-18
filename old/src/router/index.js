import Vue from 'vue'
import Router from 'vue-router'
import firebase from 'firebase'
import Home from '@/components/Home'
import Signin from '@/components/Signin'
import Signup from '@/components/Signup'
import Admin from '@/components/Admin'


const routes = [
  { path: '/', component: Home },
  { path: '/signin', component: Signin },
  { path: '/signup', component: Signup },
  { path: '/admin', component: Admin, meta: { requiresAuth: true } },
  { path: '*', component: Home }
]


Vue.use(Router)

const router = new Router({
  routes
})


router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)
  const isAuthenticated = firebase.auth().currentUser
  if (requiresAuth && !isAuthenticated) {
    next('/signin')
  } else {
    next()
  }
})

export default router
