import React, { Component, useState } from "react";
import "./Authentication.css"
import PickUserType from "./PickUserType";
import { auth ,firebase} from "../../firebase.js";
import { useAuthState } from 'react-firebase-hooks/auth'; 
import { useOperationMethod } from 'react-openapi-client';

export default function SignUpUser(props) {

    const [user] = useAuthState(auth);
    const [createUser,{ loading, data, error }] = useOperationMethod('createUser');

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
            "name": event.target.firstName.value + ' ' + event.target.lastName.value,
            "email": "string",
            "address": "string",
            "phone": event.target.phone.value,
            "accesslevel": "string",
            "type": "string",
            "institutionlink": "string",
            "cnp": "string"
        };

        
        sendReqWithToken(createUser,null,loginInfo,{},TestReturn)


    }

    const sendReqWithToken = (req,params,args,options,succ) => {
        user.getIdToken().then(
          (token) => {
            options = {headers:{'Authorization' : "Bearer " + token}};
            req(params,args,options).then(
              succ
            )
          }
        )
    }

    
    if (goBack == 1) return <PickUserType/>;
    return (
        <div className="Authentication">
        <div className="outer">
            <div className="inner">
            <form onSubmit = {handleSubmit}>
                        
                        <h3>Register as User</h3>

                        <div className="form-group">
                            <label>First name</label>
                            <input type="text" name="firstName"  className="form-control" placeholder="First name" />
                        </div>
                        

                        <div className="form-group">
                            <label>Last name</label>
                            <input type="text" name="lastName" className="form-control" placeholder="Last name" />
                        </div>

                        <div className="form-group">
                            <label>Phone number</label>
                            <input type="phone" name="phone" className="form-control" placeholder="Phone number" />
                        </div>

                        <button type="submit"  className="btn btn-dark btn-lg btn-block">Register</button>
                    </form>
                    <button onClick = {ReturnToPick} className="btn btn-dark btn-lg btn-block">Go back to user types</button>
            </div>
        </div>
        </div>
    );
        
    
}
