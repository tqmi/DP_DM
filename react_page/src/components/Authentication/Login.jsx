import React from "react";

import { auth ,firebase} from "../../firebase.js";

import { useAuthState } from 'react-firebase-hooks/auth'; 
import "./Authentication.css"

export default function Login()  {
    const [user] = useAuthState(auth);
  
    const signInWithGoogle = () => {
        
        const provider = new firebase.auth.GoogleAuthProvider();
        
        auth.signInWithPopup(provider);
      };
  

     return (
        
        <div className="Authentication">
        <div className="outer">
            <div className="inner">
                    <form>
                        <h3>Log in with Google</h3>

                        <div className="form-group">
                            
                            <div>
                            {user ?   <button type="button" className="btn btn-dark btn-lg btn-block" onClick={() => auth.signOut()}> Sign out</button> 
                            : <button type="button" className="btn btn-dark btn-lg btn-block" onClick={signInWithGoogle}> Sign In</button>} 
                            </div>
                        </div>
                    </form>

            </div>
        </div>
        </div>
    );

}