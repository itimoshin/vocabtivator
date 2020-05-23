import React, {useState} from 'react';
import {connect} from "react-redux";
import {useHistory} from 'react-router-dom'
import {uploadXlsVocabRequest} from "../redux/actions";


const UploadXlsButton = (props) => {

    const [fileUploadRef] = useState(React.createRef<HTMLInputElement>());
    const history = useHistory();

    function uploadFile() {
        fileUploadRef.current.click();
        fileUploadRef.current.onchange = (event: any) => {
            props.dispatch(uploadXlsVocabRequest(event.target.files[0]))
/*            new VocabService().uploadXls(event.target.files[0]).then((vocabTable) => {
                history.push({pathname: '/practice/sentence', vocabTable: vocabTable})
            });*/
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

function mapStateToProps(state, ownProps) {
    // component receives additionally:
    return {/* vocab: state.vocab */}
}
/*

function mapDispatchToProps (state, ownProps) {
    // component receives additionally:
    return { vocab: state.vocab }
}

*/

export default connect(
    mapStateToProps,
    // mapDispatchToProps
)(UploadXlsButton);
