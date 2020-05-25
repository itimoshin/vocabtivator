import React, {useState} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from "react-redux";
import {useHistory} from 'react-router-dom'
import * as xlsActions from "../redux/actions/xlsActions";

const UploadXlsButton = (props) => {

    const [fileUploadRef] = useState(React.createRef<HTMLInputElement>());
    const history = useHistory();

    function uploadFile() {
        fileUploadRef.current.click();
        fileUploadRef.current.onchange = (event: any) => {
            props.actions.uploadXlsVocabRequest(event.target.files[0]);
        }
    }

    function clearFile(event: any) {
        event.currentTarget.value = null;
    }

    return (
        <div>
            <input ref={fileUploadRef} id="fileButton" type="file" name="file" hidden
                   onClick={(e) => clearFile(e)}/>
            <button className={"vocab_button"} onClick={() => uploadFile()}>Upload XLS</button>
        </div>)

};

const mapStateToProps = ({vocab}) => {
    return {vocab}
};
const mapDispatchToProps = (dispatch) => ({
    actions : bindActionCreators(xlsActions, dispatch)
});


export default connect(
    mapStateToProps,
    mapDispatchToProps
)(UploadXlsButton);
