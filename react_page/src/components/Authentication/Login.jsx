import React from "react";

import { auth ,firebase} from "../../firebase.js";

import { useAuthState } from 'react-firebase-hooks/auth'; 

export default function Login()  {
    const [user] = useAuthState(auth);
    // useEffect(() => {
    //   if(user){
    //     let userToken = user.getIdToken().then((val) => getPet(null,null,{headers:{'userToken' : val}}).then((response) => console.log(response.data)));
    //   }
    // },[user]);
  
  
    const signInWithGoogle = () => {
        const provider = new firebase.auth.GoogleAuthProvider();
        auth.signInWithPopup(provider);
      };
  

     return (
            <form>

                <h3>Log in with Google</h3>

                <div className="form-group">
                    
                    <div>
                    {user ?   <button type="submit" className="btn btn-dark btn-lg btn-block" onClick={() => auth.signOut()}> Sign out</button> 
                    : <button type="submit" className="btn btn-dark btn-lg btn-block" onClick={signInWithGoogle}> Sign In</button>} 
                    </div>
                </div>

                

            </form>
    );

}