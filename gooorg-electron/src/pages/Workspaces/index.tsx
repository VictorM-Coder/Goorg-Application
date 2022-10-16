import { Sidebar } from "../../components/Sidebar";

export function Workspaces() {
   return (
      <div className="flex h-screen">
         <Sidebar />
         <div className="flex-1 min-h-screen">
            <div className="rounded">
               <header className="bg-white p-5 fixed left-0 top-0 right-0 z-0 shadow-sm">
               </header>
            </div>
         </div>
      </div>
   )
}