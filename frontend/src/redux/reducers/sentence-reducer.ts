import {SentenceActionType} from "../actions/actionTypes";
import {SentenceWithInputs} from "../../model/models";
import {AjaxState, ReducerMethod, TypedReducer} from "../types";
import {
    ChangeSentenceInputAction,
    NextSentenceReceiveAction,
    NextSentenceRequestAction
} from "../action-data/sentence-actions-data";

export const sentenceReducer = (state = null, action) => new SentenceReducer().reduce(state, action);

class SentenceReducer extends TypedReducer<AjaxState<SentenceWithInputs>, SentenceActionType> {
    factory(): Map<SentenceActionType, ReducerMethod<AjaxState<SentenceWithInputs>>> {
        const result = new Map<SentenceActionType, ReducerMethod<AjaxState<SentenceWithInputs>>>();
        result.set("NEXT_SENTENCE_REQUEST", this.nextSentenceRequest);
        result.set("NEXT_SENTENCE_RECEIVE", this.nextSentenceReceive);
        result.set("NEXT_SENTENCE_FAIL", this.nextSentenceFail);
        result.set("CHANGE_INPUT_VALUE", this.changeInputValue);
        return result;
    }

    private nextSentenceRequest(state: AjaxState<SentenceWithInputs>, action: NextSentenceRequestAction): AjaxState<SentenceWithInputs> {
        return {...state, isFetching: true};
    }

    private nextSentenceReceive(state: AjaxState<SentenceWithInputs>, action: NextSentenceReceiveAction): AjaxState<SentenceWithInputs> {
        const inputs =  Array.from({length: action.data.placeholders.length},() => ({value: '', invalid: false}));
        return {...state, isFetching: false, data: {sentence: action.data, inputs: inputs}};
    }

    private nextSentenceFail(state: AjaxState<SentenceWithInputs>, action: NextSentenceRequestAction): AjaxState<SentenceWithInputs> {
        return {...state, isFetching: false};
    }

    private changeInputValue(state: AjaxState<SentenceWithInputs>, action: ChangeSentenceInputAction): AjaxState<SentenceWithInputs> {
        const [value, idx] = action.data;
        const inputsClone = [...state.data.inputs];
        inputsClone[idx].value = value;
        return {...state, data: {...state.data, inputs: inputsClone}};
    }
}
