import { createContext, useContext, useEffect, useRef, useState } from "react";
import { Activity, ActivityContextData, NewActivity } from "../@types/Activity";
import { api } from "../services/api";

export interface ActivityProviderProps {
   children: React.ReactNode;
}

export const ActivityContext = createContext<ActivityContextData>({ } as ActivityContextData);

export function ActivityProvider({ children }: ActivityProviderProps) {
   const isFirst = useRef(false);
   const [activitys, setActivitys] = useState<Activity[]>([]);

   useEffect(() => {
      if (isFirst.current) {
         fetchActivitys().then((res) => true);
      } 

      return () => { isFirst.current = true }
   }, [])

   async function fetchActivitys() {
      const res = await api.get(`activity/all`);
      setActivitys(res.data.activities)
   }

   async function fetchActivitysByWorkspace(name: string | undefined): Promise<Activity[]> {
      const activitys = await api.get(`workspace/${name?.trim()}/activity`);
      return activitys.data.activities;
   }

   async function deleteActivityById(id: number): Promise<void> {
      await api.delete(`activity/${id}`);
      await fetchActivitys();
   }

   async function createActivity(data: NewActivity): Promise<void> {
      const res = await api.post('activity', data);
      const { activity } = res.data;

      setActivitys([...activitys, activity]);
   }

   async function finishActivityById(id: number): Promise<void> {
      await api.put(`activity/${id}`, { status: 'Concluida' });
      fetchActivitys();
   }

   return (
      <ActivityContext.Provider value={{  
         activitys, 
         fetchActivitysByWorkspace, 
         deleteActivityById, 
         createActivity,
         finishActivityById
      }}>

         { children }
      </ActivityContext.Provider>
   )
}

export function useActivities() {
   const context = useContext(ActivityContext);
   return context;
}