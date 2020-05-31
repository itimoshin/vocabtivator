export interface AjaxState<T> {
    isFetching: boolean;
    lastUpdate: Date;
    data: T;
}

export type ReducerMethod<STATE_TYPE> = (state: STATE_TYPE, action: AbstractAction<any, any>) => STATE_TYPE

export abstract class AbstractAction<T_DATA, T_TYPE_NAME extends string> {
    protected abstract getType(): T_TYPE_NAME;

    readonly type = this.getType();

    public constructor(readonly data?: T_DATA) {
    }

    toObject() {
        return Object.assign({}, this)
    }
}

export abstract class TypedReducer<STATE_TYPE, ACTIONS_TYPE extends string> {
    reduce(state: STATE_TYPE, action: AbstractAction<any, ACTIONS_TYPE>) {
        let reducerMethod = this.factory().get(action.type);
        return reducerMethod ? reducerMethod(state, action) : state;
    }

    abstract factory(): Map<ACTIONS_TYPE, ReducerMethod<STATE_TYPE>>
}
