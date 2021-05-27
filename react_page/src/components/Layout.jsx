import React, { useEffect } from "react";
import Sidebar from "./Sidebar";
import Nav from "./Nav";
import Login from "./Authentication/Login";
import PickUserType from "./Authentication/PickUserType";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Card } from "react-bootstrap";

import Drawer from 'react-modern-drawer'
import 'react-modern-drawer/dist/index.css'

import "../style/Layout.css";
import {AiOutlineMenu} from "react-icons/ai";

import { auth ,firebase} from '../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';
import sendReqWithToken from "./SendReqWithToken";

import {useState} from 'react';
import { useOperationMethod } from 'react-openapi-client';




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
    //normal user
    if(account){
    if(account.institutionlink == "NAF"){
      return (
        <Container fluid className="vh-100 d-flex flex-column ">
    
            <Row className='header'>
            <Col  sm='0.3'><AiOutlineMenu onClick={toggleDrawer}  color="white" size={50}/></Col>
              <Col><Nav account={account}/> </Col>
            </Row>
    
            <Row name="drawerRow" className="h-100">
              <Drawer open={isOpen} onClose={toggleDrawer} direction='left'>
                  <Sidebar history={props.history} />
              </Drawer>
              <Col> {props.children} </Col>
            </Row>
          
          </Container>
      );
    }
    //affiliated user
    else 
    {
      return(
        <Container fluid className="vh-100 d-flex flex-column ">
    
        <Row className='header'>
        <Col  sm='0.3'><AiOutlineMenu onClick={toggleDrawer}  color="white" size={50}/></Col>
          <Col><Nav account={account}/> </Col>
        </Row>

        <Row name="drawerRow" className="h-100">
              <Drawer open={isOpen} onClose={toggleDrawer} direction='left'>
                  <Sidebar history={props.history} />
              </Drawer>
              <Col> {props.children} </Col>
        </Row>
      
      </Container>
        
      );
    }
  }
    //not registered
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
