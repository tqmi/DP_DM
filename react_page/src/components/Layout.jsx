import React, { useEffect } from "react";
import Routes from "../routes";
import {BrowserRouter, Route, Switch, Redirect} from "react-router-dom";
import Sidebar from "./Sidebar";
import Nav from "./Nav";
import Login from "./Authentication/Login";
import SignUpUser from "./Authentication/SignUpUser";
import PickUserType from "./Authentication/PickUserType";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Card } from "react-bootstrap";


// import component 👇
import Drawer from 'react-modern-drawer'

//import styles 👇
import 'react-modern-drawer/dist/index.css'

import "../style/Layout.css";
import {AiOutlineMenu} from "react-icons/ai";

import { auth ,firebase} from '../firebase';

import { useAuthState } from 'react-firebase-hooks/auth';

import {useState} from 'react';
import { useOperationMethod } from 'react-openapi-client';
import sendReqWithToken from "./SendReqWithToken";


function Layout(props) {


  const [user] = useAuthState(auth);

  const [account, setAccount] = useState(null);
  const [getAccount,{ loading, data, error }] = useOperationMethod('getUserInfo');

  const [isOpen, setIsOpen] = React.useState(false)
  const toggleDrawer = () => {
        setIsOpen((prevState) => !prevState)

    }

    const getAccountData = (resp) => {
      if(resp){
        setAccount(resp.data);
      }
      else
      {
        setAccount(null);
      }
    }
  
    
  
  
    useEffect(() => { 
      if(user) 
      {
     
        sendReqWithToken(user,getAccount,null,null,{},getAccountData);
  
      }
  
     }, 
     [user]
    )
  

  if(user)
  {
    if(account){
      return (
        <Container fluid className="vh-100 d-flex flex-column ">
    
            <Row className='header'>
            <Col  sm='0.3'><AiOutlineMenu onClick={toggleDrawer}  color="white" size={50}/></Col>
              <Col><Nav/> </Col>
            </Row>
    
            <Row className="h-100">
              
              <Drawer open={isOpen} onClose={toggleDrawer} direction='left'>
                  <Sidebar history={props.history} />
              </Drawer>
              <Col> {props.children} </Col>
            </Row>
          
          </Container>
      );
    }
    else
    {
      return (
      <>
      <Nav/>
      <PickUserType userData = {user}/>
      </>
      );
    }
  }
  else
  {
    //<Route>
    //    <Redirect to="/?#login"/>
    //  </Route>
    return (
      <>
      <Login/>
      </>
    );
  }
}



export default Layout;
