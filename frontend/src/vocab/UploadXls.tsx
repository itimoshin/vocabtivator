import React, {RefObject} from 'react';
import {useHistory} from 'react-router-dom'
import {VocabService} from "./VocabService";

interface Properties {
    history
}

interface State {
    fileUploadRef: RefObject<HTMLInputElement>;
}

class UploadXls extends React.Component<Properties, State> {

    constructor(props) {
        super(props);
        this.state = {
            fileUploadRef: React.createRef()
        }
    }


    render() {
        return (
            <div>
                <input ref={this.state.fileUploadRef} id="fileButton" type="file" name="file" hidden
                       onClick={(e) => this.clearFile(e)}/>
                <button className={"vocab_button"} onClick={() => this.uploadFile()}>Upload XLS</button>
            </div>)
    };

    clearFile(event: any) {
        event.currentTarget.value = null;
    }

    uploadFile() {
        this.state.fileUploadRef.current.click();
        this.state.fileUploadRef.current.onchange = (event: any) => {
            new VocabService().uploadXls(event.target.files[0]).then((vocabTable) => {
                this.props.history.push({pathname: '/practice/sentence', vocabTable: vocabTable})
            });
        }
    }
}


export default function (props) {
    const history = useHistory();
    return <UploadXls {...props} history={history}/>;
}
