import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import { Test } from "./components/expFunct";
import { Sidebar } from "./components/Sidebar";

import {Container, Row, Col, Card } from "react-bootstrap";


import "./App.css";

function App() {
  return (
    <>
      <Container fluid className="vh-100 d-flex flex-column ">
        <Row>
          <Col> <Test /></Col>
        </Row>
        <Row className="h-100">
          <Sidebar />
        </Row>
      
      </Container>
      
    </>
  );
}

export default App;
