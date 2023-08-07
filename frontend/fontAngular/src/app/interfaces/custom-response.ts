import { Server } from "./server";

export interface CustomResponse {
    message:string;
    developerMessage:string;
    httpStatus:string;
    timeStamp:Date;
    statusCode:number;
    reason:string;
    data:{Servers?:Server[],server?:Server};
}
