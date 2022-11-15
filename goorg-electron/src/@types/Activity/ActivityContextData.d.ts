import { Activity } from "./Activity";
import { ActivityEdit } from "./ActivityEdit";
import { ActivityReq } from "./ActivityReq";

export interface ActivityContextData {
   activitys: Activity[];
   createActivity: (data: ActivityReq) => Promise<void>;
   createTask: (id: number, data: any) => Promise<void>;
   updateActivity: (data: ActivityEdit) => Promise<void>;
   deleteActivityById: (id: number) => Promise<void>;
   updatePhase: (id: number, phase: string) => Promise<void>;
}