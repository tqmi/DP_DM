import React from "react";
import Routes from "../routes";
import Sidebar from "./Sidebar";
import Nav from "./Nav";
import { expFunc } from "./expFunct";
import "bootstrap/dist/css/bootstrap.min.css";
import { Container, Row, Col, Card } from "react-bootstrap";
import "../style/Layout.css";

function Layout(props) {
  return (
    <div>
      <div style={{ display: "flex" }}>
        <Sidebar history={props.history} />
        <div class="flexbox-container">
          <div><Nav /></div>
          {props.children}
        </div>
      </div>
    </div>
  );
}

export default Layout;
