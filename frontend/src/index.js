import React from 'react';
import ReactDOM from 'react-dom';
import {HashRouter as Router, Route, Switch} from "react-router-dom";

import './index.css';
import './style/common.scss'
import WelcomePage from './WelcomePage';
import * as serviceWorker from './serviceWorker';
import SentencePractice from "./practice/sentence/SentencePractice";
import {Provider} from "react-redux";


ReactDOM.render(
    <Provider>
        <div className="vocabtivator_root">
            <React.StrictMode>
                <Router>
                    <Switch>
                        <Route exact path="/">
                            <WelcomePage/>
                        </Route>
                        <Route exact path="/practice/sentence">
                            <SentencePractice/>
                        </Route>
                        <Route path="/practice-sentence/abs" render={() => {
                            return <button>111</button>
                        }}/>
                    </Switch>
                </Router>
            </React.StrictMode>
        </div>
    </Provider>
    ,
    document.getElementById('root')
);

const script = document.createElement("script");

script.src = "https://apis.google.com/js/api.js?onload=onApiLoad";
script.async = true;

document.body.appendChild(script);


// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
