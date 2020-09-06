import {HttpService} from "../../HttpService";
import {HintType, HintUI, SentenceWithHint} from "../../model/models";
import {AxiosResponse} from "axios";

export class SentencePracticeService {

    private readonly httpService = new HttpService();
    private readonly hintUiMap = this.getHintUiMap();

    public nextSentence(tableKey: string, topics: string[]): Promise<SentenceWithHint> {
        return this.httpService.get('/practice-service/practice/sentence', {
            params: {
                tableKey: tableKey,
                topics: topics.join(",")
            }
        }).then((resp: AxiosResponse<SentenceWithHint>) => resp.data)
            .then(sentence => {
                sentence.hints.forEach(h => h.hintUi = this.hintUiMap[h.type]);
                return sentence;
            });
    }

    private getHintUiMap(): { [key in HintType]: HintUI; } {
        return {
            'FIRST_LETTER': {cssClass: 'hint_first_letter', typeName: 'First letters'},
            'MEANING': {cssClass: 'hint_meaning', typeName: 'Meaning'},
            'TOPIC': {cssClass: 'hint_topic', typeName: 'Topic'},
            'ANSWER': {cssClass: 'hint_answer', typeName: 'Show answer'}
        }
    }
}
