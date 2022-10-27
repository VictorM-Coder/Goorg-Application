import { Activity, NewActivity } from ".";

export interface ActivityContextData {
   activitys: Activity[];
   fetchActivitysByWorkspace: (name: string | undefined) => Promise<Activity[]>;
   createActivity: (data: NewActivity) => Promise<void>;
   deleteActivityById: (id: number) => Promise<void>;
   finishActivityById: (id: number) => Promise<void>;
}