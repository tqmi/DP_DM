import React, { Component, useState } from "react";
import "./Authentication.css"
import PickUserType from "./PickUserType";

export default function SignUpCompany(props) {


    const [goBack, setGoBack] = useState(0);

    function ReturnToPick() {
        setGoBack(1)
    }

    
    if (goBack == 1) return <PickUserType/>;
    return (
        <div className="Authentication">
        <div className="outer">
            <div className="inner">

            <form>
                        <h3>Register as Company</h3>

                        <div className="form-group">
                            <label>Company name</label>
                            <input type="text" className="form-control" placeholder="Company name" />
                        </div>

                        <div className="form-group">
                            <label>Address</label>
                            <input type="text" className="form-control" placeholder="Address" />
                        </div>

                        <div className="form-group">
                            <label>Phone number</label>
                            <input type="phone" className="form-control" placeholder="Phone number" />
                        </div>

                        <button type="submit" className="btn btn-dark btn-lg btn-block">Register</button>

                        <button type="button" onClick = {ReturnToPick} className="btn btn-dark btn-lg btn-block">Go back to user types</button>
                    </form>

            </div>
        </div>
        </div>
    );
        
    
}
