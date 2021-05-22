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

import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Login from "./Login.jsx";
import SignUp from "./Signup.jsx";
import "../style/Login.css"

function Layout(props) {

  const [isOpen, setIsOpen] = React.useState(false)
  const toggleDrawer = () => {
        setIsOpen((prevState) => !prevState)

    }
  if(0)
  {
    return (

    
      <Container fluid className="vh-100 d-flex flex-column ">
  
          <Row className='header'>
          <Col  sm='0.3'><AiOutlineMenu onClick={toggleDrawer}  color="white" size={50}/></Col>
            <Col> </Col>
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
    return (<Router>
      <div className="App">
        <nav className="navbar navbar-expand-lg navbar-light fixed-top">
          <div className="container">
            <Link className="navbar-brand" to={"/sign-in"}>RemoteStack</Link>
            <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
              <ul className="navbar-nav ml-auto">
                <li className="nav-item">
                  <Link className="nav-link" to={"/sign-in"}>Sign in</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to={"/sign-up"}>Sign up</Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
  
        <div className="outer">
          <div className="inner">
            <Switch>
              <Route exact path='/' component={Login} />
              <Route path="/sign-in" component={Login} />
              <Route path="/sign-up" component={SignUp} />
            </Switch>
          </div>
        </div>
      </div></Router>
    );
  }
}



export default Layout;
