import {
    NextSentenceFailAction,
    NextSentenceReceiveAction,
    NextSentenceRequestAction
} from "../action-data/sentence-actions-data";
import {SentencePracticeService} from "../../practice/sentence/SentencePracticeService";
import {SetHintsActionData} from "../action-data/hint-actions-data";
import {SentenceWithInputs} from "../../model/models";

export const nextSentenceRequest = () => (dispatch, getState) => {
    dispatch(new NextSentenceRequestAction().toObject());
    return new SentencePracticeService().nextSentence(getState().vocab.data.key, getState().vocab.data.topics.filter(t => t.enabled).map(v => v.name))
        .then((resp) => {
            dispatch(new NextSentenceReceiveAction(resp.sentence).toObject());
            dispatch(new SetHintsActionData(resp.hints).toObject())
        }).catch(() => dispatch(new NextSentenceFailAction().toObject()));
};


export const confirmInputs = () => (dispatch, getState) => {
    const sentenceState:SentenceWithInputs = getState().sentence;
    //sentenceState.
};