
import React from 'react';

function Dashboard(props) {
    console.log("Dashboard props", props);
    return (
        <div className="App">
            <h2>I'm the dashboard.</h2>
            <p>This is the home page </p>
        </div>
    );
}

export default Dashboard;