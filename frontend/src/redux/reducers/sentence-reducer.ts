import {SentenceActionType} from "../actions/actionTypes";
import {Sentence, SentenceWithHint} from "../../model/models";
import {AjaxState, ReducerMethod, TypedReducer} from "../types";
import {
    ChangeSentenceInputAction, NextSentenceReceiveAction,
    NextSentenceRequestAction
} from "../action-data/sentence-actions-data";

export const sentenceReducer = (state = null, action) => new SentenceReducer().reduce(state, action);

class SentenceReducer extends TypedReducer<AjaxState<SentenceWithHint>, SentenceActionType> {
    factory(): Map<SentenceActionType, ReducerMethod<AjaxState<SentenceWithHint>>> {
        const result = new Map<SentenceActionType, ReducerMethod<AjaxState<SentenceWithHint>>>();
        result.set("NEXT_SENTENCE_REQUEST", this.nextSentenceRequest);
        result.set("NEXT_SENTENCE_RECEIVE", this.nextSentenceReceive);
        result.set("NEXT_SENTENCE_FAIL", this.nextSentenceFail);
        result.set("CHANGE_INPUT_VALUE", this.changeInputValue);
        return result;
    }

    private nextSentenceRequest(state: AjaxState<SentenceWithHint>, action: NextSentenceRequestAction): AjaxState<SentenceWithHint> {
        return {...state, isFetching: true};
    }

    private nextSentenceReceive(state: AjaxState<SentenceWithHint>, action: NextSentenceReceiveAction): AjaxState<SentenceWithHint> {
        return {...state, isFetching: false, data: action.data};
    }

    private nextSentenceFail(state: AjaxState<SentenceWithHint>, action: NextSentenceRequestAction): AjaxState<SentenceWithHint> {
        return {...state, isFetching: false};
    }

    private changeInputValue(state: AjaxState<SentenceWithHint>, action: ChangeSentenceInputAction): AjaxState<SentenceWithHint> {
        const [value, idx] = action.data;
        const inputsClone = [...state.data.inputs];
        inputsClone[idx] = value;
        return {...state, data: {...state.data, inputs: inputsClone}};
    }
}
