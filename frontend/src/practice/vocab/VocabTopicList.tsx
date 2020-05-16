import React from 'react';
import {VocabTopic} from "../../model/models";
import {VocabTopicButton} from "./VocabTopicButton";
import './VocabTopic.scss'

interface Properties {
    topics: VocabTopic[]
}

interface State {
}

export class VocabTopicList extends React.Component<Properties, State> {

    private childrenRefs = [];

    constructor(props: Readonly<Properties>) {
        super(props);
    }

    setRef(ref, i) {
        this.childrenRefs[i] = ref;
    };

    getState() {
        return this.childrenRefs.map(c => c.getState())
    }

    render() {
        return (
            <div className="vocab_topic_list">
                {this.props.topics?.map((t, i) => <VocabTopicButton key={i} topic={t} ref={(r) => this.setRef(r, i)}/>)}
            </div>)
    };
}
