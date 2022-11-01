import { createContext, useContext, useEffect, useState } from 'react';
import { NewWorkspace, Workspace, WorkspaceContextData, WorkspaceProviderProps } from '../@types/Workspace';
import { api } from '../services/api';

const WorkspacesContext = createContext<WorkspaceContextData>({ } as WorkspaceContextData);

export function WorkspacesProvider({ children } : WorkspaceProviderProps) {
   const [workspaces, setWorkspaces] = useState<Workspace[]>([]);

   useEffect(() => {
      fetchWorkspaces();
   }, [])

   async function fetchWorkspaces() {
      const res = await api.get('workspace/all')
      const workspaces = res.data;
      setWorkspaces(workspaces);
   }

   async function addNewWorkspace(data: NewWorkspace): Promise<void> {
      await api.post('workspace', data);
      const newData = Object.assign(data, { countActivities: 0 });
      //setWorkspaces([...workspaces, newData]);
   }

   async function deleteWorkspaceById(id: Number): Promise<void> {
      await api.delete(`worskpace/${id}`);
      fetchWorkspaces();
   }

   async function editWorkspaceById(id: Number, data: NewWorkspace) {
      await api.put(`workspace/${id}`, data);
      fetchWorkspaces();
   }

   return (
      <WorkspacesContext.Provider value={{ 
         workspaces, 
         addNewWorkspace, 
         editWorkspaceById, 
         deleteWorkspaceById 
      }}>

         { children }
      </WorkspacesContext.Provider>
   )
}

export function useWorkspaces() {
   const context = useContext(WorkspacesContext);
   return context;
}