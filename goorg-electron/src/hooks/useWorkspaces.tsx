import { createContext, useContext, useEffect, useState } from 'react';
import { Workspace, WorkspaceContextData, WorkspaceProviderProps, WorkspaceReq } from '../@types/Workspace';
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

   async function addNewWorkspace(data: WorkspaceReq): Promise<void> {
      const workspaceCreated = await api.post('workspace', data);
      setWorkspaces([...workspaces, workspaceCreated.data]);
   }

   async function deleteWorkspaceById(id: Number): Promise<void> {
      await api.delete(`worskpace/${id}`);
      fetchWorkspaces();
   }

   async function editWorkspaceById(id: Number, data: WorkspaceReq): Promise<void> {
      await api.put(`workspace/update/${id}`, data);
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