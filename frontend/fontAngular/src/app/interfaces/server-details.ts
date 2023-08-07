import { Status } from "./status.enum";

export class ServerDetails {
   id!:number;
   physicalServer!:string;
   serverName!:string;
   ipAddress!:string;
   userName!:string;
    password!:string;
    statusServer!:Status;
    ramSize!:string;
}
