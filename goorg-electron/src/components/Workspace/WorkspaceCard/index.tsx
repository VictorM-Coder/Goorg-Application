import { DotsThree, Stack } from 'phosphor-react';
import { Link } from 'react-router-dom';
import { WorkspaceCardProps } from '../../../@types/Workspace/WorkspaceCard';

export function WorkspaceCard({ id, name, description, countActivities, color }: WorkspaceCardProps) {
   return (
      <div 
         className="bg-white shadow-sm border border-gray-100 px-6 py-5 w-[350px] min-h-[174px] rounded-md flex flex-col text-left gap-1"
      >
         <header className="flex items-center gap-2 justify-end text-gray-300">
            <DotsThree size={24} />
         </header>

         <div>
            <Link to={`/inicio/workspace/${id}`} className="flex-none hover:scale-105 transition-all">
               <span className={`text-sm font-medium`} style={{ color: color}}>
                  {name}
               </span>
            </Link>
            <p className="text-[0.8rem] text-gray-500 w-4/5 break-words">
               {description}
            </p>
         </div>

         <div className="flex items-end flex-1 gap-1 mt-4">
            <Stack size={18} />
            <span className="text-sm text-gray-700 font-medium">{countActivities}</span>   
         </div>
      </div>
   )
}