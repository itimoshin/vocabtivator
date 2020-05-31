import {HintActionType} from "../actions/actionTypes";
import {Hint} from "../../model/models";
import {ReducerMethod, TypedReducer} from "../types";
import {SetHintsActionData, ShowHintActionData} from "../action-data/hint-actions-data";


export const hintReducer = (state: Hint[] = [], action) => new HintsReducer().reduce(state, action);

class HintsReducer extends TypedReducer<Hint[], HintActionType> {
    factory(): Map<HintActionType, ReducerMethod<Hint[]>> {
        const result = new Map<HintActionType, ReducerMethod<Hint[]>>();
        result.set("SET_HINTS", this.setHints);
        result.set("SHOW_HINT", this.showHint);
        return result;
    }

    private setHints(state: Hint[], action: SetHintsActionData): Hint[] {
        return action.data;
    }

    private showHint(state: Hint[], action: ShowHintActionData): Hint[] {
        const stateClone = [...state];
        stateClone[state.indexOf(action.data)].show = true;
        return stateClone;
    }
}
