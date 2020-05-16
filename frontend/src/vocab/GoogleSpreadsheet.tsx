import React from 'react';
import {GOOGLE_SPREADSHEET_API} from "../config";
import {VocabService} from "./VocabService";
import {VocabTableDTO} from "../model/models";


class GoogleSpreadsheed extends React.Component<any, any> {
    self = this;
    pickerApiLoaded = false;
    oauthToken: any;
    vocabKey = '';
    readonly scope = ['https://www.googleapis.com/auth/drive.readonly'];


    render() {
        return (
            <button className={"vocab_button"} onClick={() => this.showGoogleFilesDialog()}>Upload Google Spreadsheet file</button>
        );
    }

    // Use the Google API Loader script to load the google.picker script.
    loadPicker() {
        window.gapi.load('auth', {'callback': this.onAuthApiLoad.bind(this)});
        window.gapi.load('picker', {'callback': this.onPickerApiLoad.bind(this)});
    }

    onAuthApiLoad() {
        window.gapi.auth.authorize(
            {
                'client_id': GOOGLE_SPREADSHEET_API.CLIENT_ID,
                'scope': this.scope,
                'immediate': false
            },
            this.handleAuthResult.bind(this));
    }

    showGoogleFilesDialog() {
        this.loadPicker();
    }

    onPickerApiLoad() {
        this.pickerApiLoaded = true;
        this.createPicker();
    }

    handleAuthResult(authResult: any) {
        if (authResult && !authResult.error) {
            this.oauthToken = authResult.access_token;
            this.createPicker();
        }
    }

    // Create and render a Picker object for searching images.
    createPicker() {
        if (this.pickerApiLoaded && this.oauthToken) {
            var view = new window.google.picker.View(window.google.picker.ViewId.DOCS);
            view.setMimeTypes("application/vnd.ms-excel" +
                ",application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" +
                ",application/vnd.google-apps.spreadsheet");
            var picker = new window.google.picker.PickerBuilder()
                .enableFeature(window.google.picker.Feature.NAV_HIDDEN)
                .enableFeature(window.google.picker.Feature.MULTISELECT_ENABLED)
                .setAppId(GOOGLE_SPREADSHEET_API.APP_ID)
                .setOAuthToken(this.oauthToken)
                .addView(view)
                .addView(new window.google.picker.DocsUploadView())
                .setDeveloperKey(GOOGLE_SPREADSHEET_API.DEVELOPER_KEY)
                .setCallback(this.pickerCallback.bind(this))
                .build();
            picker.setVisible(true);
        }
    }

    // A simple callback implementation.
    pickerCallback(data: any) {
        if (data.action === window.google.picker.Action.PICKED) {
            new VocabService().uploadGoogleSpreadsheet(data.docs[0].id, this.oauthToken).then((vocabTable: VocabTableDTO) => {
                this.props.history.push({pathname: '/practice/sentence', vocabTable: vocabTable})
            })
        }
    }
}


export default GoogleSpreadsheed;
