import { createContext, useContext, useEffect, useState } from 'react';
import { NewWorkspace, Workspace, WorkspaceContextData, WorkspaceProviderProps } from '../interfaces/workspaces';
import { api } from '../services/api';

const WorkspacesContext = createContext<WorkspaceContextData>({ } as WorkspaceContextData);
const data: Workspace[] = [
   {
      name: "G&T Controller",
      description: "Workspace voltado para um fim específico",
      countActivitys: 8,
   },
   {
      name: "Dell Leads - Front",
      description: "Workspace voltado para um fim específico",
      countActivitys: 12,
   },
   {
      name: "Atividades da Faculdade",
      description: "Gerenciamento das atividades da faculdade",
      countActivitys: 2,
   },
   {
      name: "Atividades 2",
      description: "Gerenciamento das atividades da faculdade",
      countActivitys: 2,
   },
]

export function WorkspacesProvider({ children } : WorkspaceProviderProps) {
   const [workspaces, setWorkspaces] = useState<Workspace[]>([]);

   useEffect(() => {
      fetchWorkspaces()
   }, [])

   async function fetchWorkspaces() {
      const res = await api.get('workspace/all')
      const workspaces = res.data;
      setWorkspaces(workspaces);
   }

   async function addNewWorkspace(data: NewWorkspace) {
      const newData = Object.assign(data, { countActivitys: 0 });
      await api.post('');
      setWorkspaces([...workspaces, newData]);
   }

   async function deleteWorkspaceById(id: Number) {
      await api.delete(`worskpace/${id}`);
      fetchWorkspaces();
   }

   return (
      <WorkspacesContext.Provider value={{ workspaces, addNewWorkspace, deleteWorkspaceById }}>
         { children }
      </WorkspacesContext.Provider>
   )
}

export function useWorkspaces() {
   const context = useContext(WorkspacesContext);
   return context;
}