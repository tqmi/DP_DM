import React, { Component, useState, useEffect } from "react";
import PropTypes from 'prop-types';
import { useOperationMethod } from 'react-openapi-client';
import "./FileCard.css"

import { auth ,firebase} from '../../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';
import sendReqWithToken from "../SendReqWithToken";

const FileCard = props => {
  const {
    accID = null,id=null,by = {}, fileid = null,  owner = null, status=null,
  } = props.file || {};

  const [user] = useAuthState(auth);

  const [sigList, setSigList] = useState(0);
  let signedEx =
  {
    "by": {
      "name": "string",
      "email": "string",
      "address": "string",
      "phone": "string",
      "accesslevel": "string",
      "type": "string",
      "institutionlink": "string",
      "cnp": "string"
    },
    "publicKey": "string"
  };
  const[signed,setSigned]=useState(signedEx);

 
  
  const [showInstitutions, setShowInstitutions] = useState(0);

  
  const [deleteFile,{ loading2, data2, error2 }] = useOperationMethod('deleteRequest');

  const deleteSuccess = (resp) => {
    
    props.refreshPage();

  }


  const [getFileDownloadLink,{ loading, data, error }] = useOperationMethod('getDownloadLink');
  const useDownloadLink = (resp) => {
    if(resp){
      window.open(resp.data, "_blank")
    }

  }


  function getLink()
  {
    sendReqWithToken(user,getFileDownloadLink,fileid,null,{},useDownloadLink);
  }

  function signatureButtonClick()
  {
    if(sigList)
      setSigList(0);
    else setSigList(1);
  }

  function showComp()
  {
    if(showInstitutions)
      setShowInstitutions(0);
    else setShowInstitutions(1);
  }

 function deleteFileClick()
 {
  sendReqWithToken(user,deleteFile,{"id" : props.account.institutionlink, "reqid" : id},null,{},deleteSuccess);
 }






 const [signFile, { loading4, info4, error4 }] = useOperationMethod("signFile");
 function signatureButtonClick()
 {
  sendReqWithToken(user,
    signFile,
    {"fileid" : fileid, "ownerId": user.uid},
    {
      "by": props.account,
      "publicKey": "",
      "signature": ""
    },
    {},
    ()=>console.log("Success"));
 }


  return (
    <div className="col-sm-6 col-md-12 country-card">
      <div className="country-card-container border-gray rounded border mx-2 my-3 d-flex flex-row align-items-center p-0 bg-light">
        <div >
          <button><img src={process.env.PUBLIC_URL + "./sign_button.png"} alt="my image" onClick={signatureButtonClick} /></button>
        </div>
        <div className="px-3">
          <span className="country-name text-dark d-block font-weight-bold">{ by.name }({by.cnp}) - email:{by.email}</span>
          <span className="country-region text-secondary d-block text-uppercase">
            { owner }  
          </span>
          <span className="signatureButton">
              <button class="reqSigButton" onClick={getLink} >  Download </button>
              <button class="delButton" onClick={deleteFileClick} >  Delete </button>
          </span>
        </div>
      </div>
    </div>
  )
}



export default FileCard;