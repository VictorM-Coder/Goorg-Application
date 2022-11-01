import { Header } from "../../components/Header";
import { WorkspacesRecents } from "./WorkspacesRecents";

export function Home() { 
   return (
      <div >
         <Header />
         
         <div className="m-5 flex flex-col gap-6 mt-20">
            <div className="bg-white rounded flex overflow-hidden px-10 py-14 ">
               <div>
                  <h2 className="text-xl font-semibold text-gray-700 mb-[2px]">
                     Seja Bem vindo, <span className="text-blue-primary">Daniel Almeida</span>
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
   )
}