export type HintType = 'FIRST_LETTER' | 'MEANING' | 'TOPIC' | 'ANSWER'

export interface Hint {
    type: HintType;
    text: string;
    hintUi: HintUI;
}

export interface HintUI {
    cssClass: 'hint_first_letter' | 'hint_meaning' | 'hint_topic' | 'hint_answer';
    typeName: string
}

export interface SentenceWithHint {
    sentence: {
        text: string,
        placeholders: string[]
    }
    hints: Hint[]
}

export interface VocabTableDTO {
    key: string;
    topics: string[];
}

export interface VocabTopic {
    name: string
    enabled: boolean
}

export interface VocabTable {
    key: string;
    topics: VocabTopic[];
}
