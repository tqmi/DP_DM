import React, { Component, useState, useEffect } from 'react';
import '../style/Page2.css';
import Pagination from '../components/Page3Comp/Pagination';
import FileCard from '../components/Page3Comp/FileCard';
import Upload from '../components/Page3Comp/Upload';
import { Container, Row, Col, Card } from "react-bootstrap";
import { useOperationMethod } from 'react-openapi-client';

import { auth ,firebase} from '../firebase';
import { useAuthState } from 'react-firebase-hooks/auth';

import sendReqWithToken from "../components/SendReqWithToken";

function Page3(props) {

  const [user] = useAuthState(auth);
  const [getTemplates,{ loading, data, error }] = useOperationMethod('getInstitutionTemplates');
  const [filesData,setFilesData] = useState([]);
  const [totalData,setTotalData] = useState(0);
  
  const [account, setAccount] = useState(null);
  const [getAccount,{ loading2, data2, error2 }] = useOperationMethod('getUserInfo');


  const getTemplateSuccess = resp => {
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

  const getAccountData = (resp) => {
    if(resp){
      setAccount(resp.data);
      sendReqWithToken(user,getTemplates,resp.data.institutionlink,null,{},getTemplateSuccess);
    }
    else
    {
      setAccount(null);
    }
  }

  const reloadPage = () =>
  {
    sendReqWithToken(user,getTemplates,account.institutionlink,null,{},getTemplateSuccess);
  }

  useEffect(() => { 
    sendReqWithToken(user,getAccount,null,null,{},getAccountData);
    
    
   }, 
   []
  )

  const currentPage = null;
  const totalPages = null;

  if(filesData == null) return null;
  const headerClass = ['text-dark py-2 pr-4 m-0', currentPage ? 'border-gray border-right' : ''].join(' ').trim();

  if(account && account.institutionlink == "NAF")
  {
      return (<h1>You must be linked to a company to access this page.</h1>);
  }
  return (
    <div className="container mb-5">
      <Container fluid className="vh-100 d-flex flex-column ">

        <Row>
          <Col sm='10'>
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
            { filesData.map(file => <FileCard linkID={account ? account.institutionlink : ''}  file={file} refreshPage={reloadPage}/>) }
          </div>
          </Col>

          <Col> 
            <Row><Upload link={account ? account.institutionlink : ''} uploadSuccess={reloadPage}/></Row>
          </Col>
        </Row>

      </Container>

      
    </div>
  );
  

}

export default Page3;