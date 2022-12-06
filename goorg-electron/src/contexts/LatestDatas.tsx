import { createContext, useState } from "react";
import { Activity } from "../@types/Activity";
import { Workspace } from "../@types/Workspace";

interface LatestDatasProviderProps {
   children: React.ReactNode;
}

interface LatestDatasContextData {
   latestActivities: Activity[];
   latestWorkspaces: Workspace[];
   addActivityRecents: (activity: Activity) => Promise<void>;
   addWorkspaceRecents: (workspace: Workspace) => Promise<void>;
}

export const LatestDatasContext = createContext<LatestDatasContextData>({ } as LatestDatasContextData);

export function LatestDatasProvider({ children }: LatestDatasProviderProps) {
   const [latestActivities, setLatestActivities] = useState<Activity[]>([]);
   const [latestWorkspaces, setLatestWorkspaces] = useState<Workspace[]>([]);

   async function addActivityRecents(activity: Activity) {
      if (latestActivities.length >= 10) setLatestActivities([activity, ...latestActivities.slice(0, latestActivities.length - 1)]);
      else setLatestActivities([activity, ...latestActivities]);
   }

   async function addWorkspaceRecents(workspace: Workspace) {
      if (latestWorkspaces.length >= 10) setLatestWorkspaces([workspace, ...latestWorkspaces.slice(0, latestWorkspaces.length - 1)]);
      else setLatestWorkspaces([workspace, ...latestWorkspaces]);
   }

   return (
      <LatestDatasContext.Provider value={{
         latestActivities,
         latestWorkspaces,
         addActivityRecents,
         addWorkspaceRecents
      }}>
         { children }
      </LatestDatasContext.Provider>
   )
}