import firebase from 'firebase/app';
import 'firebase/auth';

// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyCbCPcSq4lX7h6TR6YbVYCZHAORjiydNbM",
    authDomain: "dp-dm-f9041.firebaseapp.com",
    projectId: "dp-dm-f9041",
    storageBucket: "dp-dm-f9041.appspot.com",
    messagingSenderId: "315519950204",
    appId: "1:315519950204:web:94c52eed2cf074277518f9",
    measurementId: "G-CL908XW7TX"
  };

firebase.initializeApp(firebaseConfig);

const auth = firebase.auth();

export {firebase,auth};
