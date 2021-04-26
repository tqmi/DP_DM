import React from "react";
import {
  Navbar,
  NavDropdown,
  Form,
  FormControl,
  Button,
} from "react-bootstrap";
import "../style/expFunct.css";



function Nav(props) {
  return (
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" >
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

export default Nav;
