import React from 'react';
import ReactDOM from "react-dom";
import PageOrganize from '../components/Page1Comp/PageOrganize';

const rootElement = document.getElementById("root");
function Page1(props) {
    return (
       
            <React.StrictMode>
              <PageOrganize />
            </React.StrictMode>
        
    );
}

export default Page1;