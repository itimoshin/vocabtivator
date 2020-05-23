export type ReducerMethod<STATE_TYPE> = (state: STATE_TYPE, action: TypedActionData<any, any>) => STATE_TYPE

export abstract class TypedActionData<T_DATA, T_TYPE_NAME extends string> {
    protected abstract getType(): T_TYPE_NAME;

    readonly type = this.getType();

    public constructor(readonly data: T_DATA) {
    }
}

export abstract class TypedReducer<STATE_TYPE, ACTIONS_TYPE extends string> {
    reduce(state: STATE_TYPE, action: TypedActionData<STATE_TYPE, ACTIONS_TYPE>) {
        let reducerMethod = this.factory().get(action.type);
        return reducerMethod ? reducerMethod(state, action) : state;
    }

    abstract factory(): Map<ACTIONS_TYPE, ReducerMethod<STATE_TYPE>>
}
