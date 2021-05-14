import React from 'react';
import PropTypes from 'prop-types';

const FileCard = props => {
  const {
    download_link = null, fileName = null, owner = null
  } = props.file || {};

  return (
    <div className="col-sm-6 col-md-6 country-card">
      <div className="country-card-container border-gray rounded border mx-2 my-3 d-flex flex-row align-items-center p-0 bg-light">
        <div >
          <a href={download_link}>
            <img src={process.env.PUBLIC_URL + "./download_button.png"}
            />
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

FileCard.propTypes = {
  file: PropTypes.shape({
    download_link: PropTypes.string.isRequired,
    fileName: PropTypes.string.isRequired,
    owner: PropTypes.string.isRequired,
  }).isRequired
};

export default FileCard;