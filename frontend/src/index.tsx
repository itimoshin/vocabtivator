import React from 'react';
import ReactDOM from 'react-dom';
import {Route, Switch} from "react-router-dom";
import {Router} from "react-router"
import './index.css';
import './style/common.scss'
import {WelcomePage} from './WelcomePage';
import * as serviceWorker from './serviceWorker';
import SentencePractice from "./practice/sentence/SentencePractice";
import {Provider} from "react-redux";
import {store} from "./redux/redux-store";
import createBrowserHistory from 'history/createBrowserHistory'

export const appHistory = createBrowserHistory()


ReactDOM.render(
    <Provider store={store}>
        <div className="vocabtivator_root">
            <React.StrictMode>
                <Router history={appHistory}>
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
