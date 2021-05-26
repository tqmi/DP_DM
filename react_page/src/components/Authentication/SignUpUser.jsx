import React, { Component, useState, useEffect } from "react";
import "./Authentication.css"
import PickUserType from "./PickUserType";
import { auth ,firebase} from "../../firebase.js";
import { useAuthState } from 'react-firebase-hooks/auth'; 
import { useOperationMethod } from 'react-openapi-client';
import sendReqWithToken from "../SendReqWithToken";

export default function SignUpUser(props) {

    const [user] = useAuthState(auth);
    const [createUser,{ loading, data, error }] = useOperationMethod('createUser');
    const [getInstitutions,{ loading2, data2, error2 }] = useOperationMethod('getInstitutions');

    const [institutionList, setInstitutionList] = useState(null);

    const [goBack, setGoBack] = useState(0);

    function ReturnToPick() {
        setGoBack(1)
    }

    function TestReturn() {
        console.log("Success");
        auth.signOut();
    }

    useEffect(() => { 

        sendReqWithToken(user,getInstitutions,null,null,{},getInstitutionData);
        
       }, 
       []
      )


    const getInstitutionData = (resp) => {
        if(resp){
            setInstitutionList(resp.data.map((institution) => 
            <option value ={institution.id} >{institution.name}</option>));
            
        }
        else
        {
            setInstitutionList(null);
        }
      }

    const handleSubmit = (event) => {
        event.preventDefault();
        
        let loginInfo = {
            "name": event.target.firstName.value + ' ' + event.target.lastName.value,
            "email": event.target.email.value,
            "address": event.target.address.value,
            "phone": event.target.phone.value,
            "accesslevel": "string",
            "type": "User",
            "institutionlink": event.target.institution.value,
            "cnp": event.target.cnp.value
        };

        
        sendReqWithToken(user,createUser,null,loginInfo,{},TestReturn);
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
                            <label>Email</label>
                            <input type="text" name="email"  className="form-control" placeholder="Email" />
                        </div>

                        <div className="form-group">
                            <label>Address</label>
                            <input type="text" name="address"  className="form-control" placeholder="Address" />
                        </div>

                        <div className="form-group">
                            <label>Phone number</label>
                            <input type="text" name="phone" className="form-control" placeholder="Phone number" />
                        </div>

                        <div className="form-group">
                            <label>Institution (optional)</label>
                            <select name = "institution" className="form-control">
                                <option value = "NAF" defaultValue>No affiliation</option>
                                {institutionList}
                            </select>
                        </div>

                        <div className="form-group">
                            <label>Cnp</label>
                            <input type="cnp" name="cnp" className="form-control" placeholder="Cnp" />
                        </div>

                        <button type="submit"  className="btn btn-dark btn-lg btn-block">Register</button>
                        
                        <button type="button" onClick = {ReturnToPick} className="btn btn-dark btn-lg btn-block">Go back to user types</button>
                    </form>
            </div>
        </div>
        </div>
    );
        
    
}
