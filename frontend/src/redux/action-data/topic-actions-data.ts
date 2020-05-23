import {VocabTable, VocabTopic} from "../../model/models";
import {VocabActionType} from "../actions/actionTypes";
import {TypedActionData} from "../types";

export class SwitchTopicActionData extends TypedActionData<VocabTopic, VocabActionType> {
    getType(): VocabActionType {
        return "SWITCH_TOPIC";
    }
}

export class UploadXlsTableReceiveActionData extends TypedActionData<VocabTable, VocabActionType> {
    getType(): VocabActionType {
        return "UPLOAD_XLS_TABLE_RECEIVE";
    }
}

