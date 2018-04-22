import firebase from 'firebase'

let config = {
  apiKey: "AIzaSyA2LnMFAKIo7O07BcwC75eqLs3HV2VvUQI",
  authDomain: "recambiosegundouso.firebaseapp.com",
  databaseURL: "https://recambiosegundouso.firebaseio.com",
  projectId: "recambiosegundouso",
  storageBucket: "recambiosegundouso.appspot.com",
  messagingSenderId: "165840390701"
};
let firebaseApp = firebase.initializeApp(config);
export default firebaseApp


