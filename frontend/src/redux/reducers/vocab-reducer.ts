import {VocabTable} from "../../model/models";
import {VocabActionType} from "../actions/actionTypes";
import {SwitchTopicActionData, UploadXlsTableReceiveActionData} from "../action-data/topic-actions-data";
import {AjaxState, ReducerMethod, TypedReducer} from "../types";


export const vocabReducer = (state = null, action) => {
    return new VocabReducer().reduce(state, action);
}

class VocabReducer extends TypedReducer<AjaxState<VocabTable>, VocabActionType> {
    factory(): Map<VocabActionType, ReducerMethod<AjaxState<VocabTable>>> {
        const result = new Map<VocabActionType, ReducerMethod<AjaxState<VocabTable>>>();
        result.set("UPLOAD_XLS_TABLE_RECEIVE", this.uploadXlsTableReceive);
        result.set("UPLOAD_XLS_TABLE_REQUEST", this.uploadXlsTableRequest);
        result.set("UPLOAD_XLS_TABLE_FAIL", this.uploadXlsTableFail);
        result.set("SWITCH_TOPIC", this.switchTopic);
        return result;
    }

    private switchTopic(state: AjaxState<VocabTable>, action: SwitchTopicActionData): AjaxState<VocabTable> {
        const topicIdx = state.data.topics.indexOf(action.data);
        const topicsClone = [...state.data.topics];
        topicsClone[topicIdx].enabled = !topicsClone[topicIdx].enabled;
        return {...state, data: {...state.data, topics: topicsClone}};
    }

    private uploadXlsTableRequest(state: AjaxState<VocabTable>, action: UploadXlsTableReceiveActionData): AjaxState<VocabTable> {
        return {...state, isFetching: true};
    }

    private uploadXlsTableFail(state: AjaxState<VocabTable>, action: UploadXlsTableReceiveActionData): AjaxState<VocabTable> {
        return {...state, isFetching: false};
    }

    private uploadXlsTableReceive(state: AjaxState<VocabTable>, action: UploadXlsTableReceiveActionData): AjaxState<VocabTable> {
        return {...state, data: action.data, isFetching: false, lastUpdate: new Date()};
    }
}
