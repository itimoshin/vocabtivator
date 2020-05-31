import {
    MarkInvalidInputsAction,
    NextSentenceFailAction,
    NextSentenceReceiveAction,
    NextSentenceRequestAction
} from "../action-data/sentence-actions-data";
import {SentencePracticeService} from "../../practice/sentence/SentencePracticeService";
import {SetHintsActionData} from "../action-data/hint-actions-data";
import {SentenceWithInputs} from "../../model/models";
import {RootState} from "../redux-store";

export const nextSentenceRequest = () => (dispatch, getState: () => RootState) => {
    dispatch(new NextSentenceRequestAction().toObject());
    return new SentencePracticeService().nextSentence(getState().vocab.data.key, getState().vocab.data.topics.filter(t => t.enabled).map(v => v.name))
        .then((resp) => {
            dispatch(new NextSentenceReceiveAction(resp.sentence).toObject());
            dispatch(new SetHintsActionData(resp.hints).toObject())
        }).catch(() => dispatch(new NextSentenceFailAction().toObject()));
};


export const confirmInputs = () => (dispatch, getState: () => RootState) => {
    debugger
    const sentenceState = getState().sentence.data;
    const invalidInputsIndexes = sentenceState.inputs
        .filter((input, i) => input.value !== sentenceState.sentence.placeholders[i])
        .map((input, i) => i);
    if (invalidInputsIndexes.length) {
        dispatch(new MarkInvalidInputsAction(invalidInputsIndexes).toObject())
    } else {
        dispatch(nextSentenceRequest());
    }
};
