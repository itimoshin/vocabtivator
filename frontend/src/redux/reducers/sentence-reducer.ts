import {SentenceActionType} from "../actions/actionTypes";
import {Sentence} from "../../model/models";
import {ReducerMethod, TypedReducer} from "../types";
import {SetSentenceActionData} from "../action-data/sentence-actions-data";

export const sentenceReducer = (state = null, action) => new SentenceReducer().reduce(state, action);

class SentenceReducer extends TypedReducer<Sentence, SentenceActionType> {
    factory(): Map<SentenceActionType, ReducerMethod<Sentence>> {
        const result = new Map<SentenceActionType, ReducerMethod<Sentence>>();
        result.set("SET_SENTENCE", this.setSentence);
        return result;
    }

    private setSentence(state: Sentence, action: SetSentenceActionData): Sentence {
        return action.data;
    }
}
