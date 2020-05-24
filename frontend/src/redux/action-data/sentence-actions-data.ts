import {SentenceActionType} from "../actions/actionTypes";
import {Sentence} from "../../model/models";
import {AbstractAction} from "../types";

export class SetSentenceActionData extends AbstractAction<Sentence, SentenceActionType> {
    getType(): SentenceActionType {
        return "SET_SENTENCE";
    }
}
