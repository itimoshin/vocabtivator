import React from 'react';
import {GOOGLE_SPREADSHEET_API} from "../config";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import * as xlsActions from "../redux/actions/xlsActions";


const UploadGoogleSpreadsheedButton = (props) => {
    let pickerApiLoaded = false;
    let oauthToken: any;
    const scope = ['https://www.googleapis.com/auth/drive.readonly'];

    return (
        <button className={"vocab_button"} onClick={() => showGoogleFilesDialog()}>
            Upload Google Spreadsheet file
        </button>
    );

    // Use the Google API Loader script to load the google.picker script.
    function loadPicker() {
        window.gapi.load('auth', {'callback': () => onAuthApiLoad()});
        window.gapi.load('picker', {'callback': () => onPickerApiLoad()});
    }

    function onAuthApiLoad() {
        window.gapi.auth.authorize(
            {
                'client_id': GOOGLE_SPREADSHEET_API.CLIENT_ID,
                'scope': scope,
                'immediate': false
            },
            handleAuthResult.bind(this));
    }

    function showGoogleFilesDialog() {
        loadPicker();
    }

    function onPickerApiLoad() {
        pickerApiLoaded = true;
        createPicker();
    }

    function handleAuthResult(authResult: any) {
        if (authResult && !authResult.error) {
            oauthToken = authResult.access_token;
            createPicker();
        }
    }

    // Create and render a Picker object for searching images.
    function createPicker() {
        if (pickerApiLoaded && oauthToken) {
            const view = new window.google.picker.View(window.google.picker.ViewId.DOCS);
            view.setMimeTypes("application/vnd.ms-excel" +
                ",application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" +
                ",application/vnd.google-apps.spreadsheet");
            const picker = new window.google.picker.PickerBuilder()
                .enableFeature(window.google.picker.Feature.NAV_HIDDEN)
                .enableFeature(window.google.picker.Feature.MULTISELECT_ENABLED)
                .setAppId(GOOGLE_SPREADSHEET_API.APP_ID)
                .setOAuthToken(oauthToken)
                .addView(view)
                .addView(new window.google.picker.DocsUploadView())
                .setDeveloperKey(GOOGLE_SPREADSHEET_API.DEVELOPER_KEY)
                .setCallback(pickerCallback.bind(this))
                .build();
            picker.setVisible(true);
        }
    }

    // A simple callback implementation.
    function pickerCallback(data: any) {
        if (data.action === window.google.picker.Action.PICKED) {
            props.actions.uploadGoogleSpreadsheetRequest(data.docs[0].id, oauthToken);
        }
    }
};

const mapStateToProps = ({vocab}) => {
    return {vocab}
};
const mapDispatchToProps = (dispatch) => ({
    actions : bindActionCreators(xlsActions, dispatch)
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(UploadGoogleSpreadsheedButton);

