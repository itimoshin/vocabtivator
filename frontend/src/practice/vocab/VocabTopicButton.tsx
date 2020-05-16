import React from 'react';
import {VocabTopic} from "../../model/models";

interface Properties {
    topic: VocabTopic
}

interface State extends VocabTopic {
}

export class VocabTopicButton extends React.Component<Properties, State> {

    constructor(props: Readonly<Properties>) {
        super(props);
        this.state = props.topic;
    }

    render() {
        return (
            <div onClick={() => this.switchTopic()}
                 className={`vocab_switch
                 vocab_button_inline
                 vocab_topic_button 
                 ${this.state.enabled ? "vocab_topic_button_enabled" : ""}`}> {this.state.name}</div>)
    };

    getState() {
        return this.state;
    }

    switchTopic() {
        this.setState({enabled: !this.state.enabled});
    }
}
