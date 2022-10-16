import { DotsThree, Stack } from 'phosphor-react';
import { Workspace } from '../../interfaces/workspaces/Workspace';

export function WorkspaceCard({ name, description, countActivitys, color }: Workspace) {
   return (
      <div className="bg-white shadow-sm border border-gray-100 px-6 py-5 rounded-md max-w-[365px] w-full flex flex-col text-left gap-1 flex-none">
         <header className="flex items-center gap-2 justify-end text-gray-300">
            <DotsThree size={24} />
         </header>

         <div>
            <span className={`text-sm font-semibold ${color}`}>{name}</span>
            <p className="text-[0.8rem] text-gray-500 w-4/5">{description}</p>
         </div>

         <div className="flex items-center gap-1 mt-4">
            <Stack size={18} />
            <span className="text-sm text-gray-700 font-medium">{countActivitys}</span>   
         </div>
      </div>
   )
}