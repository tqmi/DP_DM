import firebase from 'firebase/app';
import 'firebase/auth';

const firebaseConfig = {
    apiKey: "AIzaSyDOlhTcWW799DuO7wi19WyFWslmuaU-GZ4",
    authDomain: "dp-dm-58734.firebaseapp.com",
    projectId: "dp-dm-58734",
    storageBucket: "dp-dm-58734.appspot.com",
    messagingSenderId: "34369500762",
    appId: "1:34369500762:web:c7e8e643ffa911b5e60a63",
    measurementId: "G-M29FHG8JR6"
};

firebase.initializeApp(firebaseConfig);

const auth = firebase.auth();

export {firebase,auth};
