import {HintActionType} from "../actions/actionTypes";
import {Hint} from "../../model/models";
import {ReducerMethod, TypedReducer} from "../types";
import {SetHintsActionData, ClickHintActionData} from "../action-data/hint-actions-data";


export const hintReducer = (state: Hint[] = [], action) => new HintsReducer().reduce(state, action);

class HintsReducer extends TypedReducer<Hint[], HintActionType> {
    factory(): Map<HintActionType, ReducerMethod<Hint[]>> {
        const result = new Map<HintActionType, ReducerMethod<Hint[]>>();
        result.set("SET_HINTS", this.setHints);
        result.set("CLICK_HINT", this.clickHint);
        return result;
    }

    private setHints(state: Hint[], action: SetHintsActionData): Hint[] {
        const meaningHint = action.data.find(h => h.type === "ANSWER");
        action.data.forEach(h => h.clicks = 0);
        let firstLetterHintInitialValue: Hint = {
            hintUi: {cssClass: 'hint_first_letter', typeName: 'First letters'},
            type: 'FIRST_LETTER',
            clicks: 0,
            text: meaningHint.text.replace(/[a-zA-Z]/g, '_')
        };
        return [...action.data, firstLetterHintInitialValue];
    }

    private clickHint(state: Hint[], action: ClickHintActionData): Hint[] {
        const stateClone = [...state];
        const clickedHint = stateClone[state.indexOf(action.data)];
        const meaningHint = state.find(h => h.type === "ANSWER");
        clickedHint.clicks++;
        clickedHint.text = HintsReducer.showNextChar(meaningHint.text, clickedHint.text);
        return stateClone;
    }

    private static showNextChar(fullString: string, currentString: string): string {
        const indexOfNextChar = currentString.indexOf('_');
        return currentString.substring(0, indexOfNextChar) + fullString.charAt(indexOfNextChar) + currentString.substring(indexOfNextChar + 1);
    }
}
