import React from 'react';
import {connect} from "react-redux";
import * as actions from "../../redux/actions/actions";
import {bindActionCreators} from "redux";
import {ClickHintActionData} from "../../redux/action-data/hint-actions-data";
import {Hint} from "../../model/models";

const HintComponent  = (props: {hint: Hint, [key: string]: any}) => {
    return (
        <div className={`vocab_button_inline vocab_switch ${props.hint.hintUi.cssClass}`}
             onClick={() => props.actions.genericAction(new ClickHintActionData(props.hint))}>
            <div hidden={props.hint.clicks > 0}>
                {props.hint.hintUi.typeName}
            </div>
            <div hidden={props.hint.clicks === 0}>{props.hint.hintUi.typeName}:{props.hint.text}</div>
        </div>)
};

const mapDispatchToProps = (dispatch) => ({
    actions: bindActionCreators(actions, dispatch)
});

export default connect(
    ({hints}) => ({hints}),
    mapDispatchToProps
)(HintComponent);
