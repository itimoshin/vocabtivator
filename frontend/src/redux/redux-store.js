import {applyMiddleware, combineReducers, compose, createStore} from "redux";
import thunk from 'redux-thunk';
import {hintReducer} from "./reducers/hints-reducer";
import {sentenceReducer} from "./reducers/sentence-reducer";
import {vocabReducer} from "./reducers/vocab-reducer";


const reducers = combineReducers({
    hints: hintReducer,
    sentence: sentenceReducer,
    vocab: vocabReducer
});

const middleware = applyMiddleware(thunk);
const createStoreWithMiddleware = compose(
    middleware
);
export const store = createStoreWithMiddleware(createStore)(reducers);
