import {VocabTable, VocabTopic} from "../../model/models";
import {VocabActionType} from "../actions/actionTypes";
import {SwitchTopicActionData, UploadXlsTableReceiveActionData} from "../action-data/topic-actions-data";
import {ReducerMethod, TypedReducer} from "../types";


export const vocabReducer = (state = null, action) => {
    debugger;
    return new VocabReducer().reduce(state, action);
}

class VocabReducer extends TypedReducer<VocabTable, VocabActionType> {
    factory(): Map<VocabActionType, ReducerMethod<VocabTable>> {
        const result = new Map<VocabActionType, ReducerMethod<VocabTable>>();
        result.set("UPLOAD_XLS_TABLE_RECEIVE", this.uploadXlsTableReceive);
        result.set("SWITCH_TOPIC", this.switchTopic);
        return result;
    }

    private switchTopic(state: VocabTable, action: SwitchTopicActionData): VocabTable {
        const topic: VocabTopic = action.data;
        topic.enabled = !topic.enabled;
        const topicIdx = state.topics.indexOf(topic);
        return {...state, topics: {...state.topics, [topicIdx]: topic}};
    }

    private uploadXlsTableReceive(state: VocabTable, action: UploadXlsTableReceiveActionData): VocabTable {
        return action.data;
    }
}
