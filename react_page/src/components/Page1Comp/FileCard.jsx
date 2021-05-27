import React, { Component, useState, useEffect } from "react";
import PropTypes from 'prop-types';
import { useOperationMethod } from 'react-openapi-client';
import "./FileCard.css"

import { auth ,firebase} from '../../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';
import sendReqWithToken from "../SendReqWithToken";

const FileCard = props => {
  const {
    download_link = null, fileid = null, fileName = null, owner = null, status=null,
  } = props.file || {};

  const [user] = useAuthState(auth);

  const [sigList, setSigList] = useState(0);
  let signedBy =
  {
    "name": "string",
    "email": "string",
    "address": "string",
    "phone": "string",
    "accesslevel": "string",
    "type": "string",
    "institutionlink": "string",
    "cnp": "string"
  };
  
 


  const [getFileDownloadLink,{ loading, data, error }] = useOperationMethod('getTemplateDownloadLink');
  const useDownloadLink = (resp) => {
    if(resp){
      window.open(resp.data, "_blank")
    }

  }


  function getLink()
  {
    sendReqWithToken(user,getFileDownloadLink,{"fileid" : fileid, "id" : props.linkID},null,{},useDownloadLink);
  }

  function signatureButtonClick()
  {
    if(sigList)
      setSigList(0);
    else setSigList(1);
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
              <button class="sigButton" onClick={signatureButtonClick} >  Signature </button>
              { sigList > 0 &&
                  <div class="dropdownSignature">
                    <ul>
                      <li>name: {signedBy.name}</li>
                      <li>email: {signedBy.email}</li>
                      <li>address: {signedBy.address}</li>
                      <li>phone: {signedBy.phone}</li>
                      <li>user type: {signedBy.type}</li>
                      <li>institution: {signedBy.institutionlink}</li>
                      <li>cnp: {signedBy.cnp}</li>
                    </ul>
                  </div>
              }
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