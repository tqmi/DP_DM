import React, { Component, useState, useEffect } from "react";
import PropTypes from 'prop-types';
import { useOperationMethod } from 'react-openapi-client';
import "./FileCard.css"

import { auth ,firebase} from '../../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';
import sendReqWithToken from "../SendReqWithToken";

const FileCard = props => {
  const {
    download_link = null,by = {}, fileid = null,  owner = null, status=null,
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

  
  const [deleteFile,{ loading2, data2, error2 }] = useOperationMethod('deleteFile');
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
  sendReqWithToken(user,deleteFile,fileid,null,{},deleteSuccess);
 }






 const [sendSigRequest, { loading4, info4, error4 }] = useOperationMethod("sendRequest");
 function sendRequest(id)
 {
  sendReqWithToken(user,sendSigRequest,id,
    { "id": '',
      "by": props.account,
      "owner": user.uid,
      "fileid": fileid
    }
  ,{},()=>console.log("Success"));
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
              <button class="sigButton" onClick={signatureButtonClick} >  Signed By </button>
              { sigList > 0 &&
                  <div class="dropdownSignature">
                    <ul>
                      <li>name: {signed.by.name}</li>
                      <li>email: {signed.by.email}</li>
                      <li>address: {signed.by.address}</li>
                      <li>phone: {signed.by.phone}</li>
                      <li>user type: {signed.by.type}</li>
                      <li>institution: {signed.by.institutionlink}</li>
                      <li>cnp: {signed.by.cnp}</li>
                    </ul>
                  </div>
              }
              <button class="reqSigButton" onClick={getLink} >  Download </button>
              <button class="delButton" onClick={deleteFileClick} >  Delete </button>
          </span>
        </div>
      </div>
    </div>
  )
}

FileCard.propTypes = {
  file: PropTypes.shape({
    download_link: PropTypes.string.isRequired,
    fileName: PropTypes.string.isRequired,
    owner: PropTypes.string.isRequired,
  }).isRequired
};

export default FileCard;