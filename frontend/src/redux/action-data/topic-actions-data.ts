import {VocabTable, VocabTopic} from "../../model/models";
import {VocabActionType} from "../actions/actionTypes";
import {AbstractAction} from "../types";

export class SwitchTopicActionData extends AbstractAction<VocabTopic, VocabActionType> {
    getType(): VocabActionType {
        return "SWITCH_TOPIC";
    }
}

export class UploadXlsTableRequestActionData extends AbstractAction<VocabTable, VocabActionType> {
    getType(): VocabActionType {
        return "UPLOAD_XLS_TABLE_REQUEST";
    }
}

export class UploadXlsTableReceiveActionData extends AbstractAction<VocabTable, VocabActionType> {
    getType(): VocabActionType {
        return "UPLOAD_XLS_TABLE_RECEIVE";
    }
}

export class UploadXlsTableFailActionData extends AbstractAction<VocabTable, VocabActionType> {
    getType(): VocabActionType {
        return "UPLOAD_XLS_TABLE_FAIL";
    }
}

