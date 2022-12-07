import { createContext, useContext, useEffect, useState } from "react";
import { Activity, ActivityContextData, ActivityCrud, ActivityTag } from "../@types/Activity";
import { TasksComplete } from "../@types/Tasks/TasksComplete";
import { api } from "../services/api";

export interface ActivityProviderProps {
   children: React.ReactNode;
}

export const ActivityContext = createContext<ActivityContextData>({ } as ActivityContextData);

export function ActivityProvider({ children }: ActivityProviderProps) {
   const [activitys, setActivitys] = useState<Activity[]>([]);

   useEffect(() => {
      fetchActivitys();
   }, [])

   async function fetchActivitys() {
      const res = await api.get(`activity/all`);
      setActivitys(res.data)
   }

   async function deleteActivity(id: number): Promise<void> {
      await api.delete(`activity/${id}`);
      fetchActivitys();
   }

   async function createActivity(data: ActivityCrud): Promise<void> {
      await api.post('activity', data);
      fetchActivitys();
   }

   async function updateActivity(data: ActivityCrud): Promise<void> {
      await api.put(`activity`, data);
      fetchActivitys();
   }

   async function updatePhase(id: number, phase: string): Promise<void> {
      await api.put(`activity/phase?phase=${phase}&idActivity=${id}`);
      fetchActivitys();
   }

   async function createTask(data: any): Promise<void> {
      await api.post(`task`, data);
      fetchActivitys();
   }
   
   async function completeTasks(tasks: TasksComplete[]): Promise<void> {
      await api.put(`task/all`, tasks);
      fetchActivitys();
   }

   async function createTag(tag: ActivityTag) {
      await api.post(`priorityTag`, tag);
   }

   return (
      <ActivityContext.Provider value={{  
         activitys, 
         createActivity,
         updateActivity,
         deleteActivity, 
         updatePhase,
         createTask,
         completeTasks,
         createTag
      }}>
         { children }
      </ActivityContext.Provider>
   )
}

export function useActivities() {
   const context = useContext(ActivityContext);
   return context;
}