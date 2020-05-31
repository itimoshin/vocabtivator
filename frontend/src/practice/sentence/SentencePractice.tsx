import React from 'react';
import {Hint, SentenceWithInputs, VocabTable} from "../../model/models";
import VocabTopicList from "../vocab/VocabTopicList";
import HintComponent from "../hint/HintComponent";
import './SentencePractice.scss'
import {genericAction} from "../../redux/actions/actions";
import {connect} from "react-redux"
import {AjaxState} from "../../redux/types";
import {bindActionCreators} from "redux";
import {ChangeSentenceInputAction} from "../../redux/action-data/sentence-actions-data";
import {confirmInputs, nextSentenceRequest} from "../../redux/actions/sentenceActions";
import HintsListComponent from "../hint/HintsListComponent";

const SentencePractice = (props: { vocab: AjaxState<VocabTable>, sentence: AjaxState<SentenceWithInputs>, hints: Hint[], [key: string]: any }) => {

    React.useEffect(() => {
        props.actions.nextSentenceRequest();
    }, []);
    return (
        <div className="sentence_practice_root">
            <h1 className="vocabtivator_header">Sentence practice</h1>
            <VocabTopicList/>
            <div className="sentence_box_wrapper">
                <div className="sentence_box">
                    {props.sentence?.data?.sentence.text.split(/{\d}/).map((text, i, arr) =>
                        (i === arr.length - 1 && arr[i] !== '') ?
                            <div className="sentence_segment" key={i}>
                                {text}
                            </div>
                            : <div className="sentence_segment" key={i}>
                                {text}<input type="text"
                                             className={`${props.sentence.data?.inputs[i].invalid ? 'invalid_input' : ''}`}
                                             value={props.sentence.data?.inputs[i].value} onChange={(e) => {
                                props.actions.genericAction(new ChangeSentenceInputAction([e.target.value, i]))
                            }}/>
                            </div>)}
                </div>
                <HintsListComponent/>
            </div>
            <div className="sentence_practice_footer">
                <button className="vocab_button" onClick={props.actions.confirmInputs}>Confirm</button>
                <button className="vocab_button" onClick={props.actions.nextSentenceRequest}>Next sentence</button>
            </div>
        </div>
    );
};

function mapStateToProps({sentence, vocab, hints}) {
    return {sentence, vocab, hints}
}

const mapDispatchToProps = (dispatch) => ({
    actions: {
        genericAction: bindActionCreators(genericAction, dispatch),
        nextSentenceRequest: bindActionCreators(nextSentenceRequest, dispatch),
        confirmInputs: bindActionCreators(confirmInputs, dispatch)
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SentencePractice);

