import { Calendar } from "phosphor-react";
import { Sidebar } from "../../components/Sidebar";
import { WorkspacesRecents } from "./WorkspacesRecents";

export function Home() {
   const weekDays = ['Domingo', 'Segunda Feira', 'Terça Feira', 'Quarta Feira', 'Quinta Feira', 'Sexta Feira', 'Sabado']
   const user = localStorage.getItem('user');
 
   return (
      <div className="flex h-screen">
         <Sidebar />

         <div className="flex flex-1 flex-col min-h-screen">
            <div className="rounded">
               <header className="bg-white p-5 fixed left-0 top-0 right-0 z-0 shadow-sm">
                  <span className="text-gray-300 text-sm justify-end flex items-center gap-1">
                     <Calendar size={20} />
                     <span>{ new Intl.DateTimeFormat('pt-BR').format(new Date()) } -</span>
                     <span>{ weekDays[new Date().getDay()] }</span>
                  </span>
               </header>
            </div>
            
            <div className="m-5 flex flex-col gap-6 mt-20">
               <div className="bg-white rounded flex overflow-hidden px-10 py-14">
                  <div>
                     <h2 className="text-xl font-semibold text-gray-700 mb-[2px]">
                        Seja Bem vindo, <span className="text-blue-primary">{user}</span>
                     </h2>
                     <p className="text-sm text-gray-300 w-4/5">
                        Tenha acesso abaixo a opções rápidas e diversificadas,
                        como seus workspaces, atividades importantes etc.
                     </p>
                  </div>
               </div>

               <WorkspacesRecents />
            </div>
         </div>

      </div>
   )
}