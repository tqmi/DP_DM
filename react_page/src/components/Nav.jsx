import React from "react";
import {
  Navbar,
  NavDropdown,
  Form,
  FormControl,
  Button,
} from "react-bootstrap";

import { auth ,firebase} from '../firebase';

import { useAuthState } from 'react-firebase-hooks/auth';
import { useEffect } from "react";
import { useOperationMethod } from 'react-openapi-client';



function Nav(props) {
  const [user] = useAuthState(auth);
  const [getPet,{ loading, data, error }] = useOperationMethod('getUser');
  useEffect(() => {
    if(user){
      let userToken = user.getIdToken().then((val) => getPet(null,null,{headers:{'userToken' : val}}).then((response) => console.log(response.data)));
    }
  },[user]);


  const signInWithGoogle = () => {
	  const provider = new firebase.auth.GoogleAuthProvider();
	  auth.signInWithPopup(provider);
	};

  return (
    
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" >
    <Navbar.Brand href="#home">Navbar with text</Navbar.Brand>
    <Navbar.Toggle />
    <Navbar.Collapse className="justify-content-end">
      <Navbar.Text>
        Signed in as: <a href="#login">{user ? user.displayName : "shit"}</a>
      </Navbar.Text>
      {user ?   <button onClick={() => auth.signOut()}> Sign out</button> : <button onClick={signInWithGoogle}> Sign In</button>} 
    </Navbar.Collapse>
  </Navbar>
  );
}

export default Nav;
