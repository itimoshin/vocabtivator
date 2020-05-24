import {
    UploadXlsTableFailActionData,
    UploadXlsTableReceiveActionData,
    UploadXlsTableRequestActionData
} from "../action-data/topic-actions-data";
import {VocabService} from "../../vocab/VocabService";
import {VocabTable} from "../../model/models";
import {appHistory} from "../../index";

export const uploadXlsVocabRequest = (file) => dispatch => {
    dispatch(new UploadXlsTableRequestActionData().toObject());
    return new VocabService().uploadXls(file).then((vocabTableResp) => {
        const vocabTable: VocabTable = {
            key: vocabTableResp.key,
            topics: vocabTableResp.topics.map((it) => ({name: it, enabled: false}))
        };
        appHistory.push({pathname: '/practice/sentence'})
        return dispatch(new UploadXlsTableReceiveActionData(vocabTable).toObject());

    }).catch(() => dispatch(new UploadXlsTableFailActionData().toObject()));
};

export const uploadGoogleSpreadsheetRequest = (fileId, oauthToken) => dispatch => {
    dispatch(new UploadXlsTableRequestActionData().toObject());
    return new VocabService().uploadGoogleSpreadsheet(fileId, oauthToken).then((vocabTableResp) => {
        const vocabTable: VocabTable = {
            key: vocabTableResp.key,
            topics: vocabTableResp.topics.map((it) => ({name: it, enabled: false}))
        };
        appHistory.push({pathname: '/practice/sentence'})
        return dispatch(new UploadXlsTableReceiveActionData(vocabTable).toObject());

    }).catch(() => dispatch(new UploadXlsTableFailActionData().toObject()));
};

