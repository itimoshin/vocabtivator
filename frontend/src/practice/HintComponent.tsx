import React from 'react';
import {VocabTopic} from "../model/models";
import {Hint} from "../model/models";

interface Properties {
    hint: Hint
}

interface State {
    show: boolean
}

export class HintComponent extends React.Component<Properties, State> {

    constructor(props: Readonly<Properties>) {
        super(props);
        this.state = {
            show: false
        }
    }


    componentDidUpdate(prevProps: Readonly<Properties>, prevState: Readonly<State>, snapshot?: any): void {
        if(prevProps !== this.props) {
            this.setState({show: false})
        }
    }

    render() {
        return (
            <div className={`vocab_button_inline vocab_switch ${this.props.hint.hintUi.cssClass}`}>
                <div hidden={this.state.show} onClick={() => this.showHint()}>{this.props.hint.hintUi.typeName}</div>
                <div hidden={!this.state.show}>{this.props.hint.hintUi.typeName}:{this.props.hint.text}</div>
            </div>)
    };

    showHint() {
        this.setState({show: true});
    }
}
