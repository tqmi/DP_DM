import React, { Component, useState, useEffect } from "react";
import PropTypes from 'prop-types';
import { useOperationMethod } from 'react-openapi-client';
import "./FileCard.css"

import { auth ,firebase} from '../../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';
import sendReqWithToken from "../SendReqWithToken";

const FileCard = props => {
  const {
    download_link = null, fileid = null, fileName = null, owner = null, status=null,signedBy=[]
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
          <button><img src={process.env.PUBLIC_URL + "./download_button.png"} alt="my image" onClick={getLink} /></button>
        </div>
        <div className="px-3">
          <span className="country-name text-dark d-block font-weight-bold">{ fileName }({status}) - id:{fileid}</span>
          <span className="country-region text-secondary d-block text-uppercase">
            { owner }  
          </span>
          <span className="signatureButton">
              <button class="sigButton" onClick={signatureButtonClick} >  Signed By </button>
              { sigList > 0 &&
                  <div class="dropdownSignature">
                    <ul>
                      {props.signatures}
                    </ul>
                  </div>
              }
              <button class="reqSigButton" onClick={showComp} >  Request Signature </button>
              { showInstitutions > 0 &&
                  <div class="dropdownSignature">
                    <ul>
                      {
                      props.institutionList.map(instit => <li onClick={() => sendRequest(instit.id)}>{instit.name}</li>)
                      }
                    </ul>
                  </div>
              }
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