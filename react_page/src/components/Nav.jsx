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
import sendReqWithToken from "./SendReqWithToken";



function Nav(props) {
  const [user] = useAuthState(auth);
  const [deleteAccount,{ loading, data, error }] = useOperationMethod('deleteUser');
  
  // useEffect(() => {
  //   if(user){
  //     let userToken = user.getIdToken().then((val) => getPet(null,null,{headers:{'userToken' : val}}).then((response) => console.log(response.data)));
  //   }
  // },[user]);


  const signInWithGoogle = () => {
	  const provider = new firebase.auth.GoogleAuthProvider();
	  auth.signInWithPopup(provider);
	};


  function deleteUser() {
    sendReqWithToken(user,deleteAccount,null,null,{},() => auth.signOut());
  }

  

  return (
    
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" >
    <Navbar.Toggle />
    <Navbar.Collapse className="justify-content-end">
      <Navbar.Text>
        Signed in as: <a href="#login">{user ? user.displayName : "not logged in"}</a>
      </Navbar.Text>
      {user ?   <button onClick={() => auth.signOut()}> Sign out</button> : <button onClick={signInWithGoogle}> Sign In</button>} 
      <button onClick={deleteUser}> Delete user</button>
    </Navbar.Collapse>
  </Navbar>
  );
}

export default Nav;
