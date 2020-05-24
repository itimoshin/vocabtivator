import {
    SET_HINTS,
    SET_SENTENCE,
    SHOW_HINT,
    UPLOAD_XLS_TABLE_RECEIVE,
    UPLOAD_XLS_TABLE_REQUEST
} from "./actionTypes";
import {Hint, SentenceWithHint, VocabTable, VocabTopic} from "../../model/models";
import {VocabService} from "../../vocab/VocabService";
import {appHistory} from "../../index";
import {
    UploadXlsTableFailActionData,
    UploadXlsTableReceiveActionData,
    UploadXlsTableRequestActionData
} from "../action-data/topic-actions-data";
import {AbstractAction} from "../types";


export const showHint = (hint: Hint) => ({
    type: SHOW_HINT,
    data: hint
});

export const setHints = (hints: Hint[]) => ({
    type: SET_HINTS,
    data: hints
});

export const setSentence = (sentence: SentenceWithHint) => ({
    type: SET_SENTENCE,
    data: sentence
});

export const switchTopic = (topic: VocabTopic) => ({
    type: SET_SENTENCE,
    data: topic
});

export const genericAction = (actionData: AbstractAction<any, any>) => {
    debugger
    return dispatch => {
        dispatch(actionData.toObject());
    };
}
