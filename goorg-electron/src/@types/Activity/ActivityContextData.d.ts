import { Activity } from "./Activity";
import { ActivityEdit } from "./ActivityEdit";
import { ActivityCrud, ActivityReq } from "./ActivityCrud";

export interface ActivityContextData {
   activitys: Activity[];
   createActivity: (data: ActivityCrud) => Promise<void>;
   createTask: (id: number, data: any) => Promise<void>;
   updateActivity: (data: ActivityCrud) => Promise<void>;
   deleteActivity: (id: number) => Promise<void>;
   updatePhase: (id: number, phase: string) => Promise<void>;
}