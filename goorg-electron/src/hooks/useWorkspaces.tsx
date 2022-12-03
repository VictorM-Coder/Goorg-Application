import { createContext, useContext, useEffect, useState } from 'react';
import { Workspace, WorkspaceContextData, WorkspaceProviderProps } from '../@types/Workspace';
import { WorkspaceCrud } from '../@types/Workspace/WorkspaceCrud';
import { api } from '../services/api';
import { useLatestDatas } from './useLatestDatas';

const WorkspacesContext = createContext<WorkspaceContextData>({ } as WorkspaceContextData);

export function WorkspacesProvider({ children } : WorkspaceProviderProps) {
   const [workspaces, setWorkspaces] = useState<Workspace[]>([]);
   const { addWorkspaceRecents } = useLatestDatas();

   useEffect(() => {
      fetchWorkspaces();
   }, [])

   async function fetchWorkspaces() {
      const res = await api.get('workspace/all')
      const workspaces = res.data;
      setWorkspaces(workspaces);
   }

   async function addNewWorkspace(data: WorkspaceCrud): Promise<void> {
      const workspace = await api.post('workspace', data);
      setWorkspaces([...workspaces, workspace.data]);

   }

   async function deleteWorkspaceById(id: Number): Promise<void> {
      await api.delete(`workspace/${id}`);
      fetchWorkspaces();
   }

   async function updateWorkspace(data: WorkspaceCrud): Promise<void> {
      await api.put(`workspace`, data);
      fetchWorkspaces();
   }

   return (
      <WorkspacesContext.Provider value={{ 
         workspaces, 
         reateWorkspace: addNewWorkspace, 
         updateWorkspace, 
         deleteWorkspace: deleteWorkspaceById 
      }}>

         { children }
      </WorkspacesContext.Provider>
   )
}

export function useWorkspaces() {
   const context = useContext(WorkspacesContext);
   return context;
}