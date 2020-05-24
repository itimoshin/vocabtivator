import React from 'react';
import {VocabTable, VocabTopic} from "../../model/models";
import VocabTopicButton from "./VocabTopicButton";
import './VocabTopic.scss'
import {connect} from "react-redux"
import {AjaxState} from "../../redux/types";

interface Properties {
    topics: VocabTopic[]
}

interface State {
}

const VocabTopicList = (props) => {

    return (
        <div className="vocab_topic_list">
            {props.topics.map((t, i) => <VocabTopicButton key={i} topic={t}/>)}
        </div>)
};

function mapStateToProps({vocab}) {
    return {topics: vocab.data?.topics || []}
}

export default connect(mapStateToProps)(VocabTopicList)
