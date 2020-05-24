import React from 'react';
import {connect} from "react-redux"
import {bindActionCreators} from 'redux';
import {SwitchTopicActionData} from "../../redux/action-data/topic-actions-data";
import * as actions from "../../redux/actions/actions";

const VocabTopicButton = (props) => {

    return (
        <div onClick={() => props.actions.genericAction(new SwitchTopicActionData(props.topic))}
             className={`vocab_switch
                 vocab_button_inline
                 vocab_topic_button 
                 ${props.topic.enabled ? "vocab_topic_button_enabled" : ""}`}>
            {props.topic.name}
        </div>
    );
};
const mapStateToProps = ({vocab}) => {
    return {vocab}
};
const mapDispatchToProps = (dispatch) => ({
    actions: bindActionCreators(actions, dispatch)
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(VocabTopicButton);
