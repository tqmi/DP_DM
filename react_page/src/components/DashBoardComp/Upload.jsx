import React, { useState } from "react";
import { render } from 'react-dom';
import "./Upload.css";

import { Container, Row, Col, Card } from "react-bootstrap";
import { useOperationMethod } from 'react-openapi-client';

import { auth ,firebase} from '../../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';

import sendReqWithToken from "../SendReqWithToken";

const styles = {
  fontFamily: 'sans-serif',
  textAlign: 'center',
  display: 'flex',
};


function Upload(props)  {

  const [user]=useAuthState(auth);
  const [putFile,{ loading, data, error }] = useOperationMethod('uploadFile');
  const [files,setFiles] = useState([]);



  function onChange(e) {
    let filesArr = Array.prototype.slice.call(e.target.files);
    setFiles(filesArr);
  }
  
  function onFileUpload() {
    setFiles(files.filter(x => x !== files[0])); 
    props.uploadSuccess();
  }

  

  function uploadFile(){
    let formData = new FormData();
    formData.append("file",files[0]);
    console.log(files[0]);
    console.log(formData.getAll("file"));
    sendReqWithToken(user,putFile,null,formData,{},onFileUpload);

    //window.location.reload();
  }


    return (
      <div style={styles}>



      
       <Container fluid className="vh-100 d-flex flex-column ">

         <Row >
          <Col  sm='0'>        
           <label className="custom-file-upload">
              <input type="file" multiple onChange={onChange} />
              <i className="fa fa-cloud-upload" /> Attach
           </label>
          </Col>
           <Col sm='3'>         
           {files.map(x => 
            <div className="file-preview" >{x.name}</div>
           )}
           </Col>
         </Row>
     
         <Row >
            <button className="custom-file-upload" onClick={uploadFile}>Upload</button>
         </Row>
       
       </Container>
       </div>
    );


}

export default Upload;