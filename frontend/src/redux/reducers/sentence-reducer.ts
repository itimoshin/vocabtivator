import {SentenceActionType} from "../actions/actionTypes";
import {Sentence} from "../../model/models";
import {AjaxState, ReducerMethod, TypedReducer} from "../types";
import {SetSentenceActionData} from "../action-data/sentence-actions-data";

export const sentenceReducer = (state = null, action) => new SentenceReducer().reduce(state, action);

class SentenceReducer extends TypedReducer<AjaxState<Sentence>, SentenceActionType> {
    factory(): Map<SentenceActionType, ReducerMethod<AjaxState<Sentence>   >> {
        const result = new Map<SentenceActionType, ReducerMethod<AjaxState<Sentence>>>();
        result.set("SET_SENTENCE", this.setSentence);
        return result;
    }

    private setSentence(state: AjaxState<Sentence>, action: SetSentenceActionData): AjaxState<Sentence> {
        return {...state, data: action.data};
    }
}
