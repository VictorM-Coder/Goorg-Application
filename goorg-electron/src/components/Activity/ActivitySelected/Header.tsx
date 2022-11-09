import { Notepad } from "phosphor-react";
import { TagPhase, TagPriority } from "../Badges";

interface HeaderProps {
   title: string | undefined;
   description: string | undefined;
   priorityTagName: string | undefined;
   phaseName: string | undefined;
   startDate: string | undefined;
   endDate: string | undefined;
}

export function HeaderActivitySelected({ title, description, priorityTagName, phaseName, startDate, endDate }: HeaderProps) {
   return (
      <div className="bg-white rounded overflow-hidden h-full shadow-sm w-full flex flex-col">
         <header className="px-10 pt-6 pb-5 flex flex-col gap-2 border-b-[1px] border-b-gray-200">
            <span className="text-gray-700 font-medium flex items-center gap-1">
               <Notepad size={20} weight="bold" />
               { title }
            </span>
            <div className="flex items-center gap-2">
               <TagPriority name={priorityTagName} size='xs'/>
               <TagPhase name={phaseName} size='xs'/>
            </div>
         </header>
         <div className="px-10 py-6 flex justify-start gap-4">
            <div 
               className="flex flex-col bg-white shadow justify-center 
               w-1/3 h-36 p-6 rounded relative after:content-[''] after:w-[6px] 
               after:h-4/5 after:bg-blue-dark-100 after:absolute after:left-0 
               after:top-4"
            >
               <span className="text-sm font-medium">Descrição</span>
               <span className="text-xs text-gray-500">{ description }</span>
            </div>
            <div className="w-1/3 flex flex-col gap-2">  
               <div 
                  className="h-1/2 bg-gray-50 flex flex-col p-4 relative after:content-[''] 
                  after:w-[4px] after:h-1/3 after:bg-cyan-400 after:absolute after:left-0 
                  after:top-4 rounded"
               >
                  <span className="text-sm font-medium">
                     Data ínicio
                  </span>
                  <span className="text-xs text-gray-500 font-medium">{startDate}</span>
               </div>
               <div 
                  className="h-1/2 bg-gray-50 flex flex-col p-4 relative after:content-[''] 
                  after:w-[4px] after:h-1/3 after:bg-red-400 after:absolute after:left-0 
                  after:top-4 rounded"
               >
                  <span className="text-sm font-medium">
                     Data Fim
                  </span>
                  <span className="text-xs text-gray-500 font-medium">{endDate}</span>
               </div>
            </div>  
         </div>
      </div>
   )
}