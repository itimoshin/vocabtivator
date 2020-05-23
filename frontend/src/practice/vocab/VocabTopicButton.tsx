import React from 'react';
import {VocabTopic} from "../../model/models";
import {connect} from "react-redux"
import {switchTopic} from "../../redux/actions";

interface Properties {
    topic: VocabTopic
}

interface State extends VocabTopic {
}

const VocabTopicButton = (props) => {


        return (
            <div onClick={() => switchTopic}
                 className={`vocab_switch
                 vocab_button_inline
                 vocab_topic_button 
                 ${props.topic.enabled ? "vocab_topic_button_enabled" : ""}`}>
                {JSON.stringify(props)}
            </div>
        );
};

export default connect(null, {switchTopic})(VocabTopicButton)
