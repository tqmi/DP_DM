import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import Routes from "./routes";
import { OpenAPIProvider } from 'react-openapi-client';
import "bootstrap/dist/css/bootstrap.min.css";

ReactDOM.render(<OpenAPIProvider definition={process.env.PUBLIC_URL + "/storage_service_api.json"}><Routes /></OpenAPIProvider>, document.getElementById("root"));

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
