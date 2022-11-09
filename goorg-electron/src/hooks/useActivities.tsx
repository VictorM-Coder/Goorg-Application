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

   async function editActivityById(id: number, data: ActivityEdit): Promise<void> {
      await api.put(`/activity/update/${id}`, data);
      fetchActivitys();
   }

   async function finishActivityById(id: number): Promise<void> {
      await api.put(`activity/${id}`, { status: 'Concluida' });
      fetchActivitys();
   }

   return (
      <ActivityContext.Provider value={{  
         activitys, 
         createActivity,
         editActivityById,
         deleteActivityById,   
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