import {HintActionType} from "../actions/actionTypes";
import {Hint} from "../../model/models";
import {TypedActionData} from "../types";

export class SetHintsActionData extends TypedActionData<Hint[], HintActionType> {
    getType(): HintActionType {
        return "SET_HINTS";
    }
}

export class ShowHintActionData extends TypedActionData<Hint, HintActionType> {
    getType(): HintActionType {
        return "SHOW_HINT";
    }
}

