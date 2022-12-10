import { Activity } from "./Activity";
import { ActivityEdit } from "./ActivityEdit";
import { ActivityCrud, ActivityReq } from "./ActivityCrud";
import { ActivityTag } from "./ActivityTag";

export interface ActivityContextData {
   activitys: Activity[];
   createActivity: (data: ActivityCrud) => Promise<void>;
   updateActivity: (data: ActivityCrud) => Promise<void>;
   deleteActivity: (id: number) => Promise<void>;
   updatePhase: (id: number, phase: string) => Promise<void>;
   createTask: (data: any) => Promise<void>;
   deleteTask: (id: number) => Promise<void>
   completeTasks: (taks: any[]) => Promise<void>;
   createTag: (tag: ActivityTag) => Promise<void>;
}