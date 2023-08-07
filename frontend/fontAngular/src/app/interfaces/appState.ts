import { DataState } from "../enum/dataState.enum";

export interface AppState<T>{
    dataState:DataState;
    appData?:T;
    error?:string
}