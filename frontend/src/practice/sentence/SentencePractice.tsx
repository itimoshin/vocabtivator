import React from 'react';
import {SentenceWithHint, VocabTable} from "../../model/models";
import {SentencePracticeService} from "./SentencePracticeService";
import VocabTopicList from "../vocab/VocabTopicList";
import HintComponent from "../HintComponent";
import './SentencePractice.scss'
import update from 'immutability-helper';
import {setHints, setSentence} from "../../redux/actions/actions";
import {connect} from "react-redux"
import {AjaxState} from "../../redux/types";

interface Properties {
    history: any
}

interface State {
    sentence: SentenceWithHint;
    vocabTable: VocabTable;
    inputValue: string[]

}

const SentencePractice = (props: {vocab: AjaxState<VocabTable>, [key: string]: any}) => {

    //private readonly vocabListRef = React.createRef<VocabTopicList>();


    /*componentDidMount() {
        this.setState({
            vocabTable: {
                key: this.props.history.location.vocabTable.key,
                topics: this.props.history.location.vocabTable.topics.map((it) => {
                    return {name: it, enabled: false}
                })
            },
            inputValue: []
        }, () => this.nextSentence());
    }*/

    /*
        handlePressEnter(e: any) {
            debugger
        }*/

    return (
        <div className="sentence_practice_root">
            <h1 className="vocabtivator_header">Sentence practice</h1>
            <VocabTopicList/>
            <div className="sentence_box_wrapper">
                {/*<div className="sentence_box">
                    {props?.sentence?.sentence.text.split(/{\d}/).map((text, i, arr) =>
                        i === arr.length - 1 ?
                            <div className="sentence_segment" key={i}>
                                {text}
                            </div>
                            : <div className="sentence_segment" key={i}>
                                {text}<input type="text" value={this.state.inputValue[i]} onChange={(e) => {
                                    let value = e.target.value;
                                    this.setState((state) => {
                                        update(state, {inputValue: {[i]: {$set: value}}})
                                    })
                            }}/>
                            </div>)}
                </div>*/}
                <div className="vocabtivator_hints">
                    {props?.sentence?.hints.map((h, i) => <HintComponent key={i + "_" + new Date().getDate()}
                                                                              hint={h}/>)}
                </div>
            </div>
            <div className="sentence_practice_footer">
                <button className="vocab_button" onClick={nextSentence}>Next sentence</button>
            </div>
        </div>
    )

    function nextSentence() {
        //const enabledTopics = this.vocabListRef.current.getState().filter(v => v.enabled).map(v => v.name);
        const enabledTopics = [];
        return new SentencePracticeService().nextSentence(props.vocab.data.key, enabledTopics).then(resp => this.setState({sentence: resp}))
    }
}

function mapStateToProps({router, sentence, vocab}) {

/*    var isFetching = athletes.isFetching
    var items = athletes.items
    var lastUpdated = athletes.lastUpdated

    return {
        isFetching,
        items,
        lastUpdated
    }*/
    return {sentence, vocab}
}

export default connect(
    mapStateToProps,
    {setHints, setSentence}
)(SentencePractice);

