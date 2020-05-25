import React from 'react';
import {SentenceWithHint, VocabTable} from "../../model/models";
import {SentencePracticeService} from "./SentencePracticeService";
import VocabTopicList from "../vocab/VocabTopicList";
import HintComponent from "../HintComponent";
import './SentencePractice.scss'
import {genericAction, setHints, setSentence} from "../../redux/actions/actions";
import {connect} from "react-redux"
import {AjaxState} from "../../redux/types";
import {bindActionCreators} from "redux";
import * as xlsActions from "../../redux/actions/xlsActions";
import {ChangeSentenceInputAction} from "../../redux/action-data/sentence-actions-data";

const SentencePractice = (props: { vocab: AjaxState<VocabTable>, [key: string]: any }) => {

    React.useEffect(() => {

    });

    return (
        <div className="sentence_practice_root">
            <h1 className="vocabtivator_header">Sentence practice</h1>
            <VocabTopicList/>
            <div className="sentence_box_wrapper">
                <div className="sentence_box">
                    {props?.sentence?.sentence.text.split(/{\d}/).map((text, i, arr) =>
                        i === arr.length - 1 ?
                            <div className="sentence_segment" key={i}>
                                {text}
                            </div>
                            : <div className="sentence_segment" key={i}>
                                {text}<input type="text" value={this.state.inputValue[i]} onChange={(e) => {
                                let value = e.target.value;
                                props.actions.genericAction(new ChangeSentenceInputAction([value, i]))
                            }}/>
                            </div>)}
                </div>
                <div className="vocabtivator_hints">
                    {props?.sentence?.hints.map((h, i) => <HintComponent key={i + "_" + new Date().getDate()}
                                                                         hint={h}/>)}
                </div>
            </div>
            <div className="sentence_practice_footer">
                <button className="vocab_button" onClick={nextSentence}>Next sentence</button>
            </div>
        </div>
    );

    function nextSentence() {
        const enabledTopics = props.vocab.data.topics.filter(v => v.enabled).map(v => v.name);
        return new SentencePracticeService().nextSentence(props.vocab.data.key, enabledTopics).then(resp => this.setState({sentence: resp}))
    }
};

function mapStateToProps({router, sentence, vocab}) {
    return {sentence, vocab}
}

const mapDispatchToProps = (dispatch) => ({
    actions: {
        genericAction: bindActionCreators(genericAction, dispatch)
    }
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(SentencePractice);

