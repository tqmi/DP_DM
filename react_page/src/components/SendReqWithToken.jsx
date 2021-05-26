import { useOperationMethod } from "react-openapi-client";
import { useAuthState } from "react-firebase-hooks/auth";
import { auth } from "../firebase";

 
  const sendReqWithToken = (user,req,params,args,options,succ) => {
    user.getIdToken().then(
      (token) => {
        options = {headers:{'Authorization' : "Bearer " + token}};
        req(params,args,options).then(
          succ
        )
      }
    )
  }


export default sendReqWithToken;
