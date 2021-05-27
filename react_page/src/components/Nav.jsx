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
import { useEffect ,useState} from "react";
import { useOperationMethod } from 'react-openapi-client';
import sendReqWithToken from "./SendReqWithToken";

import { Link } from "react-router-dom";



function Nav(props) {
  const [user] = useAuthState(auth);
  


  const signInWithGoogle = () => {
	  const provider = new firebase.auth.GoogleAuthProvider();
	  auth.signInWithPopup(provider);
	};



  
  
  
 
  return (
    
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" >
    <Navbar.Toggle />
    <Navbar.Collapse className="justify-content-end">
      <Navbar.Text>
        Signed in as: <a href="#login">{props.account ? props.account.name : ""}</a>
      </Navbar.Text>
      {user ?   <button onClick={() => auth.signOut()}> Sign out</button> : <button onClick={signInWithGoogle}> Sign In</button>} 
      <Link to={"/user-info"}>
            <div>User Info</div>
     </Link>
    </Navbar.Collapse>
  </Navbar>
  );
}

export default Nav;
