import { createContext, useContext, useEffect, useRef, useState } from "react";
import { Activity, ActivityContextData, ActivityReq } from "../@types/Activity";
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
         fetchActivitys();
      } 

      return () => { isFirst.current = true }
   }, [])

   async function fetchActivitys() {
      const res = await api.get(`activity/all`);
      setActivitys(res.data.activities)
   }

   async function deleteActivityById(id: number): Promise<void> {
      await api.delete(`activity/${id}`);
      await fetchActivitys();
   }

   async function createActivity(data: ActivityReq): Promise<void> {
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