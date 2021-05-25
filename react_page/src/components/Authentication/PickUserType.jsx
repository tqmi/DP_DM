import React, { Component, useState } from "react";
import "./Authentication.css"
import SignUpUser from "./SignUpUser";
import SignUpCompany from "./SignUpCompany";

export default function PickUserType(props)  {


    const [userType, setUserType] = useState("Not Selected");
    

    function loadUserSignUp() {
        setUserType("UserSignUp");
    }

    function loadCompanySignUp() {
        setUserType("CompanySignUp");
    }
    
   
    
    if (userType === "UserSignUp") return <SignUpUser />;
    if (userType === "CompanySignUp") return <SignUpCompany />;
    return (
        <div className="Authentication">
        <div className="outer">
            <div className="inner">

                <div>
                        <h3>Pick User Type</h3>

                        <button onClick = {loadUserSignUp} className="btn btn-dark btn-lg btn-block">Register as User</button>
                        <button onClick = {loadCompanySignUp} className="btn btn-dark btn-lg btn-block">Register as Company</button>
                    </div>

            </div>
        </div>
        </div>
    )
    
}

