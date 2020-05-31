import {SentenceActionType} from "../actions/actionTypes";
import {AbstractAction} from "../types";
import {Sentence, SentenceInput} from "../../model/models";

export type SentenceRequest = {tableKey: string, topics: string[]};
export type ChangedInputValue = [string, number]//{newValue: string, index: number};

export class NextSentenceRequestAction extends AbstractAction<SentenceRequest, SentenceActionType> {
    getType(): SentenceActionType {
        return "NEXT_SENTENCE_REQUEST";
    }
}

export class NextSentenceReceiveAction extends AbstractAction<Sentence, SentenceActionType> {
    getType(): SentenceActionType {
        return "NEXT_SENTENCE_RECEIVE";
    }
}

export class NextSentenceFailAction extends AbstractAction<SentenceRequest, SentenceActionType> {
    getType(): SentenceActionType {
        return "NEXT_SENTENCE_FAIL";
    }
}

export class ChangeSentenceInputAction extends AbstractAction<ChangedInputValue, SentenceActionType> {
    getType(): SentenceActionType {
        return "CHANGE_INPUT_VALUE";
    }
}

export class MarkInvalidInputsAction extends AbstractAction<number[], SentenceActionType> {
    getType(): SentenceActionType {
        return "MARK_INVALID_INPUTS";
    }
}
