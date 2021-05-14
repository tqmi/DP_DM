import React from 'react';
import { render } from 'react-dom';
import "./Upload.css";

import { Container, Row, Col, Card } from "react-bootstrap";
import { useOperationMethod } from 'react-openapi-client';


const styles = {
  fontFamily: 'sans-serif',
  textAlign: 'center',
  display: 'flex',
};

function Upload(props) {
  
  const [putFile,{ loading, data, error }] = useOperationMethod('putFile');

  return <UploadWrapped putFileApi={{'operation' : putFile , "result" : { loading, data, error }}} />;
}

class UploadWrapped extends React.Component {
  
  constructor() {
    super();
    this.onChange = this.onChange.bind(this);
    this.uploadFile = this.uploadFile.bind(this);
    this.state = {
      files: [],
    };
  }

  onChange(e) {
    var files = e.target.files;
    console.log(files);
    var filesArr = Array.prototype.slice.call(files);
    console.log(filesArr);
    this.setState({ files: [...this.state.files, ...filesArr] });
  }
  
  removeFile(f) {
       this.setState({ files: this.state.files.filter(x => x !== f) }); 
  }

  uploadFile(){
    var formData = new FormData();
    formData.append("filename",this.state.files[0]);
    console.log(this.state.files[0]);
    this.props.putFileApi.operation(null,formData,{headers:{'userToken' : "whatever"}}).then(response => console.log(response));
    window.location.reload();
  }

  render() {
    return (
      <div style={styles}>



      
       <Container fluid className="vh-100 d-flex flex-column ">

         <Row >
          <Col  sm='0'>        
           <label className="custom-file-upload">
              <input type="file" multiple onChange={this.onChange} />
              <i className="fa fa-cloud-upload" /> Attach
           </label>
          </Col>
           <Col sm='3'>         
           {this.state.files.map(x => 
            <div className="file-preview" onClick={this.removeFile.bind(this, x)}>{x.name}</div>
           )}
           </Col>
         </Row>
     
         <Row >
            <button className="custom-file-upload" onClick={this.uploadFile}>Upload</button>
         </Row>
       
       </Container>
       </div>
    );

  }
}

export default Upload;