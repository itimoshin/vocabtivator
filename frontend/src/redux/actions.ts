import {
    SET_HINTS,
    SET_SENTENCE,
    SHOW_HINT,
    UPLOAD_XLS_TABLE_RECEIVE,
    UPLOAD_XLS_TABLE_REQUEST
} from "./actions/actionTypes";
import {Hint, SentenceWithHint, VocabTable, VocabTopic} from "../model/models";
import {VocabService} from "../vocab/VocabService";
import {appHistory} from "../index";
import {UploadXlsTableReceiveActionData} from "./action-data/topic-actions-data";


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

export const uploadXlsVocabRequest = (file) => dispatch => {
    return new VocabService().uploadXls(file).then((vocabTableResp) => {
        const vocabTable: VocabTable = {
            key: vocabTableResp.key,
            topics: vocabTableResp.topics.map((it) => ({name: it, enabled: false}))
        };
        dispatch(new UploadXlsTableReceiveActionData(vocabTable));
        appHistory.push({pathname: '/practice/sentence'})

    });
};


