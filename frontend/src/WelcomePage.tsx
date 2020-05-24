import React from 'react';
import './App.scss';
import UploadXlsButton from './vocab/UploadXlsButton'
import UploadGoogleSpreadsheetButton from "./vocab/GoogleSpreadsheet";

export const WelcomePage = () => {
    return (
        <div className="App">
            <UploadGoogleSpreadsheetButton/>
            <UploadXlsButton/>
        </div>
    );
};
