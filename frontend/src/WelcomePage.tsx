import React from 'react';
import './App.scss';
import GoogleSpreadsheet from './vocab/GoogleSpreadsheet'
import UploadXls from './vocab/UploadXls'

export class WelcomePage extends React.Component {

    render() {
        return (
            <div className="App">

                <GoogleSpreadsheet></GoogleSpreadsheet>
                <UploadXls></UploadXls>
            </div>
        );
    }


}

export default WelcomePage;
