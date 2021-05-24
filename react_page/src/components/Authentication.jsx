import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Login from "./Authentication/Login.jsx";
import SignUp from "./Authentication/Signup.jsx";
import "../style/Authentication.css"

function Authentication() {
    return (<Router>
        <div className="Authentication">
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

export default Authentication;