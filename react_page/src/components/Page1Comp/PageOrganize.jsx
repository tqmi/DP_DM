import React, { useState, useEffect } from "react";
import ReactPaginate from "react-paginate";
import "./PageOrganize.css";
import SearchField from "react-search-field";
import { Container, Row, Col, Card } from "react-bootstrap";
import { useOperationMethod } from "react-openapi-client";
import sendReqWithToken from "../SendReqWithToken";

import { auth, firebase } from "../../firebase";
import { useAuthState } from "react-firebase-hooks/auth";

import { useHistory } from "react-router-dom";


const PER_PAGE = 4;

var institutions=[];


export default function PageOrganize() {
  const [currentPage, setCurrentPage] = useState(0);
  const [data, setData] = useState([]);
  const [getInstitutions, { loading, info, error }] =
    useOperationMethod("getInstitutions");

    
  const [user] = useAuthState(auth);

  useEffect(() => {
    sendReqWithToken(
      user,
      getInstitutions,
      null,
      null,
      {},
      getInstitutionStatus
    );
  }, []);
  function getInstitutionStatus(resp) {
    console.log(resp);
    setData(resp.data);
  }

  function handlePageClick({ selected: selectedPage }) {
    setCurrentPage(selectedPage);
  }

  const offset = currentPage * PER_PAGE;
  const displayNumber = 0;

  const currentPageData = (
    <ul class="flex-container wrap">
      {data.slice(offset, offset + PER_PAGE).map(({ name }) => (
        <button type="button" class="btn btn-light" >
          {name}
          
        </button>
      ))}
    </ul>
  );

  const pageCount = Math.ceil(data.length / PER_PAGE);

  return (
    <div className="PageOrganize">
      <h1>Institutions</h1>
      <div class="container">
        <ul class="flex-container nowrap">
          <li class="flex-item1">
            <ReactPaginate
              previousLabel={"← Previous"}
              nextLabel={"Next →"}
              pageCount={pageCount}
              onPageChange={handlePageClick}
              containerClassName={"pagination"}
              previousLinkClassName={"pagination__link"}
              nextLinkClassName={"pagination__link"}
              disabledClassName={"pagination__link--disabled"}
              activeClassName={"pagination__link--active"}
            />
          </li>
          <li class="flex-item2">
            <SearchField
              placeholder="Search..."
              //onChange={onChange}
              //searchText="Search"
              classNames="test-class"
            />
          </li>
        </ul>

        {currentPageData}
      </div>
    </div>
  );
}
