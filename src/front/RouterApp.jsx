import React from "react";
import {HashRouter as Router, IndexRoute, Link, Route} from "react-router-dom";
import Form1 from './Form1.jsx';
import {Nav, Navbar, NavItem} from 'react-bootstrap';

import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/css/bootstrap-theme.css';


const style = `
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333333;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 16px;
    text-decoration: none;
}

li a:hover {
    background-color: #111111;
}
`;
const RouterApp = () => (
    <Router>
      <div>
        <Navbar>
          <Navbar.Header>
            <Navbar.Brand>
              <a href="#">Сервисы</a>
            </Navbar.Brand>
          </Navbar.Header>
          <Nav>
            <NavItem eventKey={1} href="#">
              <Link to="/form1">Отправка формы</Link>
            </NavItem>
          </Nav>
        </Navbar>
        <hr/>
        <Route exact path="/" component={Home}/>
        <Route path="/form1" component={Form1}/>
      </div>
    </Router>
);

const Home = () => (
    <div>
      <h2>Начало</h2>
      <p>&#128513;</p>
    </div>
);
export default RouterApp;