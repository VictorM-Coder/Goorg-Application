import { Activity } from "./Activity";
import { ActivityReq } from "./ActivityReq";

export interface ActivityContextData {
   activitys: Activity[];
   createActivity: (data: ActivityReq) => Promise<void>;
   //editActivityById: (id: number, data: ActivityReq) => Promise<void>;
   deleteActivityById: (id: number) => Promise<void>;
   finishActivityById: (id: number) => Promise<void>;
}