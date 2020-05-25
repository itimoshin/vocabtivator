import {SET_HINTS, SET_SENTENCE, SHOW_HINT} from "./actionTypes";
import {Hint, SentenceWithHint, VocabTopic} from "../../model/models";
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

export const genericAction = (actionData: AbstractAction<any, any>) => dispatch => {
    dispatch(actionData.toObject());
};
