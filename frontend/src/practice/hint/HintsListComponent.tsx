import React from 'react';
import {connect} from "react-redux";
import * as actions from "../../redux/actions/actions";
import {bindActionCreators} from "redux";
import {Hint} from "../../model/models";
import HintComponent from "./HintComponent";
import './HintsListComponent.scss'

const HintsListComponent  = (props: {hints: Hint[], [key: string]: any}) => {
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
