import React from "react";
import Routes from "../routes";
import Sidebar from "./Sidebar";
import Nav from "./Nav";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Card } from "react-bootstrap";

// import component 👇
import Drawer from 'react-modern-drawer'

//import styles 👇
import 'react-modern-drawer/dist/index.css'

import "../style/Layout.css";
import {AiOutlineMenu} from "react-icons/ai";


function Layout(props) {

  const [isOpen, setIsOpen] = React.useState(false)
  const toggleDrawer = () => {
        setIsOpen((prevState) => !prevState)

    }

  return (

    
    <Container fluid className="vh-100 d-flex flex-column ">

        <Row className='header'>
        <Col  sm='0.3'><AiOutlineMenu onClick={toggleDrawer}  color="white" size={50}/></Col>
          <Col> <Nav /></Col>
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

export default Layout;
