export type HintType = 'FIRST_LETTER' | 'MEANING' | 'TOPIC' | 'ANSWER'

export interface ReduxAction<T> {
    type: string;
    data: T
}

export interface Hint {
    type: HintType;
    text: string;
    hintUi: HintUI;
    show: boolean;
}

export interface HintUI {
    cssClass: 'hint_first_letter' | 'hint_meaning' | 'hint_topic' | 'hint_answer';
    typeName: string
}

export interface Sentence {
    text: string,
    placeholders: string[]
}

export interface SentenceWithInput {
    text: string,
    placeholders: string[]
}


export interface SentenceWithHint {
    sentence: Sentence
    inputs: string[]
    hints: Hint[]
}

export interface VocabTableDTO {
    key: string;
    topics: string[];
}

export interface VocabTable {
    key: string;
    topics: VocabTopic[];
}

export interface VocabTopic {
    name: string
    enabled: boolean
}
