import Vue from 'vue';
import Vuex from 'vuex';
import firebase from 'firebase'
import 'firebase/firestore'
import router from '@/router'
import firebaseApp from '@/firebase-config'


Vue.use(Vuex)

const defaultDatabase = firebaseApp.firestore();
const docRef = defaultDatabase.collection('r2Uso').doc('recambios');

export default new Vuex.Store({
  state: {
    appTitle: 'Recambios Segundo Uso',
    user: null,
    error: null,
    loading: false,
    listaRepuestos: []
  },
  mutations: {
    setUser(state, payload) {
      state.user = payload
    },
    setError(state, payload) {
      state.error = payload
    },
    setLoading(state, payload) {
      state.loading = payload
    },
    setRepuestos(state, repuestos) {
      state.listaRepuestos = repuestos;
    }
  },
  actions: {
    getRespuestos({commit}) {
      docRef.get().then((doc) => {
        commit('setLoading', false);
        if (!doc.exists) {
          console.log('No such document!');
        } else {
          commit('setRepuestos', doc.data().root);
        }
      }).catch(err => {
        console.log('Error getting document', err);
      });
    },
    userSignUp({commit}, payload) {
      commit('setLoading', true)
      firebase.auth().createUserWithEmailAndPassword(payload.email, payload.password)
        .then(firebaseUser => {
          commit('setUser', {email: firebaseUser.email})
          commit('setLoading', false)
          router.push('/admin')
        })
        .catch(error => {
          commit('setError', error.message)
          commit('setLoading', false)
        })
    },
    userSignIn({commit}, payload) {
      commit('setLoading', true)
      firebase.auth().signInWithEmailAndPassword(payload.email, payload.password)
        .then(firebaseUser => {
          commit('setUser', {email: firebaseUser.email})
          commit('setLoading', false)
          commit('setError', null)
          router.push('/admin')
        })
        .catch(error => {
          commit('setError', error.message)
          commit('setLoading', false)
        })
    },
    autoSignIn({commit}, payload) {
      commit('setUser', {email: payload.email})
    },
    userSignOut({commit}) {
      firebase.auth().signOut()
      commit('setUser', null)
      router.push('/')
    }
  },
  getters: {
    isAuthenticated(state) {
      return state.user !== null && state.user !== undefined
    }
    ,
    listaRepuestos(state) {
      return state.listaRepuestos;
    }
  }
})
