import React from "react";
import {
  Navbar,
  Nav,
  NavDropdown,
  Form,
  FormControl,
  Button,
} from "react-bootstrap";
import "../style/expFunct.css";

export function Test() {
  return (
    <Navbar bg="dark" variant="dark">
      <Navbar.Brand href="#home">Navbar with text</Navbar.Brand>
      <Navbar.Toggle />
      <Navbar.Collapse className="justify-content-end">
        <Navbar.Text>
          Signed in as: <a href="#login">Mark Otto</a>
        </Navbar.Text>
      </Navbar.Collapse>
    </Navbar>
  );
}
