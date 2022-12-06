import { FileText } from "phosphor-react";
import { Button } from "../../../Button";

export function CardNotes() {
   return (
      <div className="h-[200px] w-1/2 bg-blue-200 rounded flex flex-col">
         <header className="pt-4 pb-3 px-4 flex items-center gap-1 text-blue-900">
            <FileText size={20} />
            <span className="text-sm font-semibold">Anotações</span>
         </header>
         <div className="flex flex-col justify-between flex-1">
            <div className="p-4 flex flex-col gap-3 max-h-[100px] overflow-y-auto scrollbar">
               <span className="bg-white p-3 rounded text-xs">contente</span>
               <span className="bg-white p-3 rounded text-xs">contente</span>
               <span className="bg-white p-3 rounded text-xs">contente</span>
            </div>
            <Button 
               bg="bg-blue-900"
               text="Adicionar anotação"
               textColor="text-white"
            />
         </div>
      </div>
   )
}