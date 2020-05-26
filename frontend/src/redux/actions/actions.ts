import {AbstractAction} from "../types";

export const genericAction = (actionData: AbstractAction<any, any>) => dispatch => {
    dispatch(actionData.toObject());
};
