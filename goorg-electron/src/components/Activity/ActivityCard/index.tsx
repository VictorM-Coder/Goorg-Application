import { Calendar, DotsThree } from 'phosphor-react';
import { MouseEvent, useState } from 'react';
import { Link } from 'react-router-dom';
import { ActivityCardProps } from '../../../@types/Activity';
import { TagPhase, TagPriority } from '../Badges';
import { ActivityCardOptions } from './ActivityCardOptions';

export function ActivityCard({ 
   id,
   title,
   description,
   endDate,
   phase,
   priorityTag,
   workspaceId,
   minWidth = false }: ActivityCardProps) {
      
   const [elementRef, setElementRef] = useState<HTMLButtonElement | null>(null);
   const optionsIsOpen = Boolean(elementRef);

   const handleOpenOptions = (event: MouseEvent<HTMLButtonElement>) => setElementRef(event.currentTarget);
   const handleCloseOptions = () => setElementRef(null);

   return (
      <div className={`bg-white shadow-sm border border-gray-100 px-6 py-3 w-full
         ${(minWidth) ? 'min-w-[350px]' : ''} max-w-[350px] min-h-[174px] rounded-md flex flex-col`
      }>
         <header className="flex items-center gap-2 justify-end text-gray-300">
            <button onClick={handleOpenOptions}>
               <DotsThree size={24} />
            </button>
         </header>
         
         <div>
            <Link to={`/inicio/workspace/${workspaceId}/atividade/${id}`}>
               <span className={`text-sm font-medium`}>{title}</span>
            </Link>
         
            <div className="flex items-center gap-2 pt-1 pb-1">
               <TagPriority name={priorityTag?.name} size='xs'/>
               <TagPhase name={phase} size='xs'/>
            </div>

            <p className="text-[0.8rem] text-gray-500 w-4/5">{description}</p>
         </div>

         <div className="flex text-end items-end flex-1 mt-4 gap-1 text-gray-300 justify-self-end">
            <Calendar size={18} weight="fill" />
            <span className="text-xs text-gray-300 font-medium">{endDate?.toString()}</span>   
         </div>   

         <ActivityCardOptions 
            idActivity={id}
            phaseNow={phase.toLowerCase()}
            elementRef={elementRef}
            optionsIsOpen={optionsIsOpen}
            onCloseOptions={handleCloseOptions}    
         />
      </div>
   )
}