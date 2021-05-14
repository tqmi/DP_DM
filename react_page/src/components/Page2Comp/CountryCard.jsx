import React from 'react';
import PropTypes from 'prop-types';
import Flag from 'react-flags';

const CountryCard = props => {
  const {
    download_link = null, fileName = null, owner = null
  } = props.file || {};

  return (
    <div className="col-sm-6 col-md-4 country-card">
      <div className="country-card-container border-gray rounded border mx-2 my-3 d-flex flex-row align-items-center p-0 bg-light">
        <div className="h-100 position-relative border-gray border-right px-2 bg-white rounded-left">
          <a href={download_link}>
            <img src={process.env.PUBLIC_URL + "./download_button.png"}
            width="60" height="60"/>
          </a>
        </div>
        <div className="px-3">
          <span className="country-name text-dark d-block font-weight-bold">{ fileName }</span>
          <span className="country-region text-secondary text-uppercase">{ owner }</span>
        </div>
      </div>
    </div>
  )
}

CountryCard.propTypes = {
  file: PropTypes.shape({
    download_link: PropTypes.string.isRequired,
    fileName: PropTypes.string.isRequired,
    owner: PropTypes.string.isRequired,
  }).isRequired
};

export default CountryCard;