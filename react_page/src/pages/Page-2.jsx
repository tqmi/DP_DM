import React, { Component } from 'react';
import Countries from 'countries-api';
import '../style/Page2.css';
import Pagination from '../components/Page2Comp/Pagination';
import CountryCard from '../components/Page2Comp/CountryCard';
import Upload from '../components/Page2Comp/Upload';
import { Container, Row, Col, Card } from "react-bootstrap";
import { useOperationMethod } from 'react-openapi-client';


function Page2(props) {
  
  const [getFiles,{ loading, data, error }] = useOperationMethod('getFiles');

  return <Page2Wrapped getFileApi={{'operation' : getFiles , "result" : { loading, data, error }}}/>;
}


class Page2Wrapped extends Component {
  state = { allCountries: [], 
    currentCountries: [], 
    currentPage: null, 
    totalPages: null,
    filesData: [],
  }

  constructor() {
    super();
  }



  componentDidMount() {
    const { data: allCountries = [] } = Countries.findAll();
    console.log(allCountries);
    this.setState({ allCountries });

    this.props.getFileApi.operation().then(response => {
      console.log(response.data);
      this.setState({filesData: response.data});
      console.log(this.state.filesData);
    });
  }

  onPageChanged = data => {
    const { allCountries } = this.state;
    const { currentPage, totalPages, pageLimit } = data;
    const offset = (currentPage - 1) * pageLimit;
    const currentCountries = allCountries.slice(offset, offset + pageLimit);

    this.setState({ currentPage, currentCountries, totalPages });
  }

  render() {
    const { allCountries, currentCountries, currentPage, totalPages, filesData } = this.state;
    const totalCountries = allCountries.length;

    if (totalCountries === 0) return null;

    const headerClass = ['text-dark py-2 pr-4 m-0', currentPage ? 'border-gray border-right' : ''].join(' ').trim();

    return (
      <div className="container mb-5">
        <Container fluid className="vh-100 d-flex flex-column ">

          <Row>
            <Col sm='10'>
            <div className="row d-flex flex-row py-5">
              <div className="w-100 px-4 py-5 d-flex flex-row flex-wrap align-items-center justify-content-between">
                <div className="d-flex flex-row align-items-center">
                  <h2 className={headerClass}>
                    <strong className="text-secondary">{totalCountries}</strong> Countries
                  </h2>
                  { currentPage && (
                    <span className="current-page d-inline-block h-100 pl-4 text-secondary">
                      Page <span className="font-weight-bold">{ currentPage }</span> / <span className="font-weight-bold">{ totalPages }</span>
                    </span>
                  ) }
                </div>
                <div className="d-flex flex-row py-4 align-items-center">
                  <Pagination totalRecords={totalCountries} pageLimit={18} pageNeighbours={1} onPageChanged={this.onPageChanged} />
                </div>
              </div>
              { currentCountries.map(country => <CountryCard key={country.cca3} country={country} />) }
            </div>
            </Col>

            <Col> 
              <Row><Upload/></Row>
              {filesData.map(file => <div>{file.fileName}</div>)}
            </Col>
          </Row>

        </Container>

        
      </div>
    );
  }
}

export default Page2;