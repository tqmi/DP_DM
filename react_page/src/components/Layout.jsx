import React from "react";
import Routes from "../routes";
import {BrowserRouter, Route, Switch, Redirect} from "react-router-dom";
import Sidebar from "./Sidebar";
import Nav from "./Nav";
import Authentication from "./Authentication";
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


function Layout(props) {

  const [user] = useAuthState(auth);
  console.log(user);
  const [isOpen, setIsOpen] = React.useState(false)
  const toggleDrawer = () => {
        setIsOpen((prevState) => !prevState)

    }
  if(user)
  {
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
    //<Route>
    //    <Redirect to="/?#login"/>
    //  </Route>
    return (
      <>
      
      <Authentication/>
      </>
    );
  }
}



export default Layout;
