import { createContext, useContext, useEffect, useRef, useState } from "react";
import { Activity, ActivityContextData, ActivityEdit, ActivityReq } from "../@types/Activity";
import { api } from "../services/api";

export interface ActivityProviderProps {
   children: React.ReactNode;
}

export const ActivityContext = createContext<ActivityContextData>({ } as ActivityContextData);

export function ActivityProvider({ children }: ActivityProviderProps) {
   const isFirst = useRef(false);
   const [activitys, setActivitys] = useState<Activity[]>([]);

   useEffect(() => {
      fetchActivitys();
      // if (isFirst.current) { // comenta na hora do build
      //    fetchActivitys();
      // } 

      // return () => { isFirst.current = true }
   }, [])

   async function fetchActivitys() {
      const res = await api.get(`activity/all`);
      setActivitys(res.data)
   }

   async function deleteActivityById(id: number): Promise<void> {
      await api.delete(`activity/${id}`);
      fetchActivitys();
   }

   async function createActivity(data: ActivityReq): Promise<void> {
      await api.post('activity', data);
      fetchActivitys();
      //const { activity } = res.data;

      //setActivitys([...activitys, activity]);
   }

   async function updateActivity(data: ActivityEdit): Promise<void> {
      await api.put(`/activity`, data);
      fetchActivitys();
   }

   async function createTask(id: number, data: any): Promise<void> {
      await api.post(`/task/${id}`, data);
      fetchActivitys();
   }

   async function updatePhase(id: number, phase: string): Promise<void> {
      await api.put(`/activity/phase?phase=${phase}&idActivity=${id}`);
      fetchActivitys();
   }

   return (
      <ActivityContext.Provider value={{  
         activitys, 
         createActivity,
         updateActivity,
         deleteActivityById,   
         updatePhase,
         createTask
      }}>

         { children }
      </ActivityContext.Provider>
   )
}

export function useActivities() {
   const context = useContext(ActivityContext);
   return context;
}