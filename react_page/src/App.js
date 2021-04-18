import "bootstrap/dist/css/bootstrap.min.css";
import React from "react";
import { Test } from "./components/expFunct";
import { Sidebar } from "./components/Sidebar";

import {Container, Row, Col, Card, Form, Button } from "react-bootstrap";


import "./App.css";

function App() {
  return (
    <>
      <Test />,
      <Sidebar />

      
    </>
  );
}

export default App;
