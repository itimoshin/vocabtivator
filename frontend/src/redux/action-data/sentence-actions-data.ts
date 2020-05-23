import {SentenceActionType} from "../actions/actionTypes";
import {Sentence} from "../../model/models";
import {TypedActionData} from "../types";

export class SetSentenceActionData extends TypedActionData<Sentence, SentenceActionType> {
    getType(): SentenceActionType {
        return "SET_SENTENCE";
    }
}
