import { createContext, useContext, useEffect, useRef, useState } from 'react';
import { Workspace, WorkspaceContextData, WorkspaceProviderProps } from '../interfaces/workspaces';

const WorkspacesContext = createContext<WorkspaceContextData>({ } as WorkspaceContextData);
const data: Workspace[] = [
   // {
   //    name: "G&T Controller",
   //    description: "Workspace voltado para um fim específico",
   //    countActivitys: 8,
   //    color: "text-red-ligth" 
   // },
   // {
   // name: "Dell Leads - Front",
   // description: "Workspace voltado para um fim específico",
   // countActivitys: 12,
   // color: "text-blue-primary"
   // },
   // {
   // name: "Atividades da Faculdade",
   // description: "Gerenciamento das atividades da faculdade",
   // countActivitys: 2,
   // color: "text-yellow-400"
   // },
   // {
   //    name: "Atividades 2",
   //    description: "Gerenciamento das atividades da faculdade",
   //    countActivitys: 2,
   //    color: "text-yellow-400"
   // },
]

export function WorkspacesProvider({ children } : WorkspaceProviderProps) {
   const isFirst = useRef(false);
   const [workspaces, setWorkspaces] = useState<Workspace[]>([]);

   useEffect(() => {
      if (isFirst.current) {
         setWorkspaces(data);
      } 

      return () => { isFirst.current = true }
   }, [])

   return (
      <WorkspacesContext.Provider value={{ workspaces }}>
         { children }
      </WorkspacesContext.Provider>
   )
}

export function useWorkspaces() {
   const context = useContext(WorkspacesContext);
   return context;
}