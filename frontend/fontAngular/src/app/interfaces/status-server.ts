import { DataState } from "../enum/dataState.enum";

export interface StatusServere<T> {
  dataState:DataState;
  appData?:T;
  error?:string
}
