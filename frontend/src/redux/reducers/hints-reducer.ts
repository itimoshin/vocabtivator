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
        debugger
        let firstLetterHintInitialValue: Hint = {
            hintUi: {cssClass: 'hint_first_letter', typeName: 'First letters'},
            type: 'FIRST_LETTER',
            clicks: 0,
            data: HintsReducer.generateNextCharSequence(meaningHint.data[0]),
            text: meaningHint.text.replace(/[a-zA-Z]/g, '_')
        };
        debugger
        return [...action.data, firstLetterHintInitialValue];
    }

    private clickHint(state: Hint[], action: ClickHintActionData): Hint[] {
        const stateClone = [...state];
        const clickedHint = stateClone[state.indexOf(action.data)];
        clickedHint.text = clickedHint.data[clickedHint.clicks++];
        return stateClone;
    }

    private static generateNextCharSequence(fullString: string): string[] {
        const template = fullString.replace(/[a-zA-Z]/g, '_');
        const result = [template];
        template.split('').forEach((char, i) => {
            if(char === '_') {
                result.push(
                    result[result.length-1].substring(0, i) + fullString.charAt(i) + result[result.length-1].substring(i + 1)
                )
            }
        });
        return result.slice(1);
    }
}
