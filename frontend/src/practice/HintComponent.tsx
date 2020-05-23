import React from 'react';
import {connect} from "react-redux";
import {showHint} from "../redux/actions";


const HintComponent  = (props: any) => {

    return (
        <div className={`vocab_button_inline vocab_switch ${props.hint.hintUi.cssClass}`}>
            <div hidden={props.hint.show} onClick={() => props.showHint(props.hint)}>{props.hint.hintUi.typeName}</div>
            <div hidden={!props.hint.show}>{props.hint.hintUi.typeName}:{props.hint.text}</div>
        </div>)
};

export default connect(
    null,
    { showHint }
)(HintComponent);
// export default AddTodo;

