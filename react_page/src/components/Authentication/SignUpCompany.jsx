import React, { Component, useState, useEffect } from "react";
import "./Authentication.css"
import PickUserType from "./PickUserType";
import { auth ,firebase} from "../../firebase.js";
import { useAuthState } from 'react-firebase-hooks/auth'; 
import { useOperationMethod } from 'react-openapi-client';
import sendReqWithToken from "../SendReqWithToken";

export default function SignUpCompany(props) {
    const [user] = useAuthState(auth);
    const [createInstitution,{ loading, data, error }] = useOperationMethod('createInstitution');

    const [goBack, setGoBack] = useState(0);

    function ReturnToPick() {
        setGoBack(1)
    }

    function TestReturn() {
        console.log("Success");
        auth.signOut();
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        
        let loginInfo = {
            "id": '' ,
            "name": event.target.name.value,
            "location": event.target.location.value
        };

        
        sendReqWithToken(user,createInstitution,null,loginInfo,{},TestReturn);
    }
    
    
    if (goBack == 1) return <PickUserType/>;
    return (
        <div className="Authentication">
        <div className="outer">
            <div className="inner">

            <form onSubmit = {handleSubmit}>
                        <h3>Register as Company</h3>

                        <div className="form-group">
                            <label>Company name</label>
                            <input name="name" type="text" className="form-control" placeholder="Company name" />
                        </div>

                        <div className="form-group">
                            <label>Location</label>
                            <input name="location" type="text" className="form-control" placeholder="Location" />
                        </div>



                        <button type="submit" className="btn btn-dark btn-lg btn-block">Register</button>

                        <button type="button" onClick = {ReturnToPick} className="btn btn-dark btn-lg btn-block">Go back to user types</button>
                    </form>

            </div>
        </div>
        </div>
    );
        
    
}
