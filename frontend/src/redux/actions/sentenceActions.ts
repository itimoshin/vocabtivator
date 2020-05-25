import {
    UploadXlsTableFailActionData,
    UploadXlsTableReceiveActionData,
    UploadXlsTableRequestActionData
} from "../action-data/topic-actions-data";
import {VocabService} from "../../vocab/VocabService";
import {VocabTable} from "../../model/models";
import {appHistory} from "../../index";
import {
    ChangeSentenceInputAction,
    NextSentenceFailAction,
    NextSentenceReceiveAction,
    NextSentenceRequestAction,
    SentenceRequest
} from "../action-data/sentence-actions-data";
import {SentencePracticeService} from "../../practice/sentence/SentencePracticeService";

export const nextSentenceRequest = (sentenceRequest: SentenceRequest) => dispatch => {
    dispatch(new NextSentenceRequestAction().toObject());
    return new SentencePracticeService().nextSentence(sentenceRequest.tableKey, sentenceRequest.topics)
        .then((resp) => dispatch(new NextSentenceReceiveAction(resp).toObject()))
        .catch(() => dispatch(new NextSentenceFailAction().toObject()));
};

