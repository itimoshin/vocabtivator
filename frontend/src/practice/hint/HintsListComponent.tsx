import React from 'react';
import {connect} from "react-redux";
import * as actions from "../../redux/actions/actions";
import {bindActionCreators} from "redux";
import {Hint} from "../../model/models";
import HintComponent from "./HintComponent";
import './HintsListComponent.scss'
import {ClickHintActionData} from "../../redux/action-data/hint-actions-data";

const HintsListComponent = (props: { hints: Hint[], [key: string]: any }) => {

    function handleKeyDown(e: KeyboardEvent) {
        if (e.shiftKey && /Digit\d/.test(e.code)) {
            const idx = Number(e.code[e.code.length - 1]) - 1;
            if (idx <= props.hints.length - 1 && idx >= 0) {
                props.actions.genericAction(new ClickHintActionData(props.hints[idx]))
            }
        }
    }

    React.useEffect(() => {
        document.addEventListener('keydown', handleKeyDown);
        return () => {
            document.removeEventListener('keydown', handleKeyDown)
        }
    }, [props.hints]);

    return (
        <div className="vocabtivator_hints">
            {props.hints.map((h, i) => <HintComponent key={i} hint={h}/>)}
        </div>
    )
};

const mapDispatchToProps = (dispatch) => ({
    actions: bindActionCreators(actions, dispatch)
});

export default connect(
    ({hints}) => ({hints}),
    mapDispatchToProps
)(HintsListComponent);
