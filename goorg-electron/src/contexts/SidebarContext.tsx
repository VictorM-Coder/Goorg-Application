import { createContext, useState } from "react";

interface SidebarContextData {
   sidebarMinimizedIsActive: boolean;
   handleSidebarMinimezed: () => void;
}

interface SidebarProviderProps {
   children: React.ReactNode;
}

export const SidebarContex = createContext<SidebarContextData>({} as SidebarContextData);

export function SidebarProvider({ children }: SidebarProviderProps) {
   const [sidebarMinimizedIsActive, setSidebarMinimizedIsActive] = useState(true);
   function handleSidebarMinimezed() {
      setSidebarMinimizedIsActive(!sidebarMinimizedIsActive);
   }

   return (
      <SidebarContex.Provider value={{ sidebarMinimizedIsActive, handleSidebarMinimezed}}>
        { children }
      </SidebarContex.Provider>
   )
}