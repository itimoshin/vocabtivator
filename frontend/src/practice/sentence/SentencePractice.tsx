import React from 'react';
import {SentenceWithHint, VocabTable} from "../../model/models";
import {useHistory} from "react-router-dom";
import {SentencePracticeService} from "./SentencePracticeService";
import {VocabTopicList} from "../vocab/VocabTopicList";
import {HintComponent} from "../HintComponent";
import './SentencePractice.scss'
import update from 'immutability-helper';

interface Properties {
    history: any
}

interface State {
    sentence: SentenceWithHint;
    vocabTable: VocabTable;
    inputValue: string[]

}

class SentencePractice extends React.Component<Properties, State> {

    private readonly vocabListRef = React.createRef<VocabTopicList>();

    constructor(props: Readonly<Properties>) {
        super(props);
    }

    componentDidMount() {
        this.setState({
            vocabTable: {
                key: this.props.history.location.vocabTable.key,
                topics: this.props.history.location.vocabTable.topics.map((it) => {
                    return {name: it, enabled: false}
                })
            },
            inputValue: []
        }, () => this.nextSentence());
    }

    /*
        handlePressEnter(e: any) {
            debugger
        }*/

    render() {
        return (
            <div className="sentence_practice_root">
                <h1 className="vocabtivator_header">Sentence practice</h1>
                <VocabTopicList ref={this.vocabListRef} topics={this.state?.vocabTable.topics}/>
                <div className="sentence_box_wrapper">
                    <div className="sentence_box">
                        {this.state?.sentence?.sentence.text.split(/{\d}/).map((text, i, arr) =>
                            i === arr.length - 1 ?
                                <div className="sentence_segment" key={i}>
                                    {text}
                                </div>
                                : <div className="sentence_segment" key={i}>
                                    {text}<input type="text" value={this.state.inputValue[i]} onChange={(e) => {
                                        let value = e.target.value;
                                        this.setState((state) => {
                                            debugger;
                                            update(state, {inputValue: {[i]: {$set: value}}})
                                        })
                                }}/>
                                </div>)}
                    </div>
                    <div className="vocabtivator_hints">
                        {this.state?.sentence?.hints.map((h, i) => <HintComponent key={i + "_" + new Date().getDate()}
                                                                                  hint={h}/>)}
                    </div>
                </div>
                <div className="sentence_practice_footer">
                    <button className="vocab_button" onClick={() => this.nextSentence()}>Next sentence</button>
                </div>
            </div>
        )
    };

    private nextSentence() {
        const enabledTopics = this.vocabListRef.current.getState().filter(v => v.enabled).map(v => v.name);
        return new SentencePracticeService().nextSentence(this.state.vocabTable.key, enabledTopics).then(resp => this.setState({sentence: resp}))
    }
}

export default function (props) {
    const history = useHistory();
    return <SentencePractice {...props} history={history}/>;
}
