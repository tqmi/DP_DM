import React, { Component, useState, useEffect } from 'react';
import '../../style/Page2.css';
import FileCard from './FileCard';
import { Container, Row, Col, Card } from "react-bootstrap";
import { useOperationMethod } from 'react-openapi-client';

import { auth ,firebase} from '../../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';

import sendReqWithToken from "../../components/SendReqWithToken";

function CompanyTemplates(props) {

  const [user] = useAuthState(auth);
  const [getInstitutionsTemplates,{ loading, data, error }] = useOperationMethod('getInstitutionTemplates');
  const [filesData,setFilesData] = useState([]);
  const [totalData,setTotalData] = useState(0);
  


  const getFileSuccess = resp => {
    if(resp)
    {
      setFilesData(resp.data);
      setTotalData(resp.data.length);
    }
    else
    {
      setFilesData(null);
    }
  } 

 
  useEffect(() => { 
    sendReqWithToken(user,getInstitutionsTemplates,props.id,null,{},getFileSuccess);
    
   }, 
   []
  )

  const currentPage = null;
  const totalPages = null;

  if(filesData == null) return null;
  const headerClass = ['text-dark py-2 pr-4 m-0', currentPage ? 'border-gray border-right' : ''].join(' ').trim();

  return (
    <div className="container mb-5">
      <Container fluid className="vh-100 d-flex flex-column ">

        <Row>
          <Col sm='10'>
          <button className="returnButton" onClick={props.return} >  Return </button>
          <div className="row d-flex flex-row py-5">
            <div className="w-100 px-4 py-5 d-flex flex-row flex-wrap align-items-center justify-content-between">
              <div className="d-flex flex-row align-items-center">
                <h2 className={headerClass}>
                  <strong className="text-secondary">{totalData}</strong> Documents
                </h2>
                { currentPage && (
                  <span className="current-page d-inline-block h-100 pl-4 text-secondary">
                    Page <span className="font-weight-bold">{ currentPage }</span> / <span className="font-weight-bold">{ totalPages }</span>
                  </span>
                ) }
              </div>
              {/* <div className="d-flex flex-row py-4 align-items-center">
                <Pagination totalRecords={totalData} pageLimit={3} pageNeighbours={1} onPageChanged={onPageChanged} />
              </div> */}
            </div>
            { filesData.map(file => <FileCard linkID={props.id } file={file} />) }
          </div>
          </Col>

        </Row>

      </Container>

      
    </div>
  );
  

}

export default CompanyTemplates;