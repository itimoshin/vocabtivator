export type HintType = 'FIRST_LETTER' | 'MEANING' | 'TOPIC' | 'ANSWER'

export interface Hint {
    type: HintType;
    text: string;
    data: string[];
    hintUi: HintUI;
    clicks: number;
}

export interface HintUI {
    cssClass: 'hint_first_letter' | 'hint_meaning' | 'hint_topic' | 'hint_answer';
    typeName: string
}

export interface Sentence {
    text: string,
    placeholders: string[]
}


export interface SentenceWithInputs {
    sentence: Sentence,
    inputs: SentenceInput[]
}

export interface SentenceInput {
    value: string,
    invalid: boolean
}

export interface SentenceWithHint {
    sentence: Sentence
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
