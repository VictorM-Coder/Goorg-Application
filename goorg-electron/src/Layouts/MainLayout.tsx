import { ReactJSXElement } from "@emotion/react/types/jsx-namespace";
import { useContext } from "react";
import { Sidebar } from "../components/Sidebar";
import { SidebarContex } from "../contexts/SidebarContext";

interface MainLayoutProps {
   page: ReactJSXElement;
}

export function MainLayout({ page }: MainLayoutProps) {
   const { sidebarMinimizedIsActive } = useContext(SidebarContex);
   
   return (
      <div className="flex min-h-screen">
         <Sidebar />
         <div 
            className={`${(sidebarMinimizedIsActive) ? "w-[calc(100vw-110px)]" : "w-[calc(100vw-270px)]"} 
            flex flex-col transition-all mdmax:w-[calc(100vw-16px)]`}
         >
            { page }
         </div>
      </div>
   )
}