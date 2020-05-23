import React from 'react';
import './App.scss';
import UploadXlsButton from './vocab/UploadXlsButton'
import {UploadGoogleSpreadsheedButton} from "./vocab/GoogleSpreadsheet";

export const WelcomePage = () => {
    return (
        <div className="App">
            <UploadGoogleSpreadsheedButton/>
            <UploadXlsButton/>
        </div>
    );
};
