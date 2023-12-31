import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListEmployeeComponent from './components/ListEmployeeComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateEmployeeComponent from './components/CreateEmployeeComponent';
import UpdateEmployeeComponent from './components/UpdateEmployeeComponent';
import ViewEmployeeComponent from './components/ViewEmployeeComponent';
import employeeService from "./services/EmployeeService";
import TokenComponent from "./components/TokenComponent";
import RegisterComponent from "./components/RegisterComponent";
import AuthenticateComponent from "./components/AuthenticateComponent";

function App() {
    return (
        <div>
            <Router>
                <HeaderComponent/>
                <div className="container">
                    <Switch>
                        <Route path="/" exact component={TokenComponent}></Route>
                        <Route path="/register" exact component={RegisterComponent}></Route>
                        <Route path="/authenticate" exact component={AuthenticateComponent}></Route>
                        {/*<Route path = "/" exact component = {ListEmployeeComponent}></Route>*/}
                        <Route path="/employees" component={ListEmployeeComponent}></Route>
                        <Route path="/add-employee/_add" component={CreateEmployeeComponent}></Route>
                        <Route path="/view-employee/:id" component={ViewEmployeeComponent}></Route>
                         <Route path = "/add-employee/:id" component = {UpdateEmployeeComponent}></Route>
                    </Switch>
                </div>
                <FooterComponent/>
            </Router>
        </div>

    );
}

export default App;
