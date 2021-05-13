import React, { useState, useEffect } from "react";
import ReactPaginate from "react-paginate";
import "./PageOrganize.css";


const PER_PAGE = 9;

export default function PageOrganize() {
  const [currentPage, setCurrentPage] = useState(0);
  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  function fetchData() {
    fetch("https://ihsavru.me/Demo/uploads.json")
      .then((res) => res.json())
      .then((data) => {
        const {
          course: { uploads },
        } = data;
        setData(uploads);
      });
  }

  function handlePageClick({ selected: selectedPage }) {
    setCurrentPage(selectedPage);
  }

  const offset = currentPage * PER_PAGE;
  const displayNumber = 0;

    
    
    const currentPageData = <ul class="flex-container wrap">{data .slice(offset, offset + PER_PAGE).map(({uploader,id}) => (<button type="button" class="btn btn-light" >{uploader} {id}</button>
    ))}</ul>
    
 

  const pageCount = Math.ceil(data.length / PER_PAGE);

  return (
    <div className="PageOrganize">
      <h1>Institutions</h1>
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

      {currentPageData}
    </div>
  );
}
