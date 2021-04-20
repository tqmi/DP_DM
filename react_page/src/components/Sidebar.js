import React from "react";
import SideNav, { Toggle, Nav, NavItem, NavIcon, NavText } from '@trendmicro/react-sidenav';
import '@trendmicro/react-sidenav/dist/react-sidenav.css';

export function Sidebar() {
  return (
    <>
      <SideNav
        onSelect={(selected) => {
          <h1>{selected.eventKey}</h1>
        }}
      >
        <SideNav.Toggle />
        <SideNav.Nav defaultSelected="Blue">
          <NavItem eventKey="Blue">
            <NavIcon>
              <i className="fa fa-fw fa-home" style={{ fontSize: "1.75em" }} />
            </NavIcon>
            <NavText>Blue</NavText>
            
          </NavItem>

          <NavItem eventKey="options">
            <NavIcon>
              <i
                className="fa fa-fw fa-line-chart"
                style={{ fontSize: "1.75em" }}
              />
            </NavIcon>
            <NavText>Options</NavText>
            <NavItem eventKey="options/red">
              <NavText>Red</NavText>
            </NavItem>
            <NavItem eventKey="options/green">
              <NavText>Green</NavText>
            </NavItem>
          </NavItem>
        </SideNav.Nav>
      </SideNav>
    </>
  );
}
