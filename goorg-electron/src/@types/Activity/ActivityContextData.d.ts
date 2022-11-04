import { Activity } from "./Activity";
import { ActivityEdit } from "./ActivityEdit";
import { ActivityReq } from "./ActivityReq";

export interface ActivityContextData {
   activitys: Activity[];
   createActivity: (data: ActivityReq) => Promise<void>;
   editActivityById: (id: number, data: ActivityEdit) => Promise<void>;
   deleteActivityById: (id: number) => Promise<void>;
   finishActivityById: (id: number) => Promise<void>;
}