import {HintActionType} from "../actions/actionTypes";
import {Hint} from "../../model/models";
import {AbstractAction} from "../types";

export class SetHintsActionData extends AbstractAction<Hint[], HintActionType> {
    getType(): HintActionType {
        return "SET_HINTS";
    }
}

export class ShowHintActionData extends AbstractAction<Hint, HintActionType> {
    getType(): HintActionType {
        return "SHOW_HINT";
    }
}

