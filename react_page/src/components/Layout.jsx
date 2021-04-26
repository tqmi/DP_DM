import React from "react";
import Routes from "../routes";
import Sidebar from "./Sidebar";
import Nav from "./Nav";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Card } from "react-bootstrap";
import "../style/Layout.css";


function Layout(props) {
  return (

    
    <Container fluid className="vh-100 d-flex flex-column ">

        <Row>
          <Col> <Nav /></Col>
        </Row>

        <Row className="h-100">
          <Col sm="1.9" > <Sidebar history={props.history} /> </Col>
          <Col> {props.children} </Col>
        </Row>
      
      </Container>
  );
}

export default Layout;
