import classNames from 'classnames';
import { Calendar, DotsThree } from 'phosphor-react';
import { MouseEvent, useState } from 'react';
import { ActivityCardProps } from '../../../@types/Activity';
import { ActivityCardOptions } from './ActivityCardOptions';

export function ActivityCard({ 
   id,
   title,
   description,
   endDate,
   phase,
   priorityTag,
   workspaceId,
   nameVisible,
   minWidth = false }: ActivityCardProps) {
      
   const [elementRef, setElementRef] = useState<HTMLButtonElement | null>(null);
   const optionsIsOpen = Boolean(elementRef);

   const handleOpenOptions = (event: MouseEvent<HTMLButtonElement>) => setElementRef(event.currentTarget);
   const handleCloseOptions = () => setElementRef(null);

   return (
      <div className={`bg-white shadow-sm border border-gray-100 px-6 py-3 w-full
         ${(minWidth) ? 'min-w-[320px]' : ''} max-w-[365px] min-h-[174px] rounded-md flex flex-col`
      }>
         <header className="flex items-center gap-2 justify-end text-gray-300">
            <button onClick={handleOpenOptions}>
               <DotsThree size={24} />
            </button>
         </header>
         
         <div>
            <span className={`text-sm font-semibold`}>{title}</span>
            <div className="flex items-center gap-2 text-xs pt-1 pb-1">
               <span className={classNames('py-0.5 px-3 rounded-full font-medium', {
                  'bg-red-200 text-red-500': priorityTag.name === 'Urgente',
                  'bg-yellow-200 text-yellow-600': priorityTag.name === 'Relevante',
                  'bg-blue-200 text-blue-600': priorityTag.name === 'Importante',
               })}>
                  {priorityTag.name}
               </span> 

               <span className={classNames('py-0.5 px-3 rounded-full font-medium', {
                  'bg-orange-200 text-orange-500': phase === 'TO_DO',
                  'bg-green-200 text-green-600': phase  === 'DONE',
               })}>
                  {phase.toLowerCase()}
               </span>
            </div>
            <p className="text-[0.8rem] text-gray-500 w-4/5">{description}</p>
         </div>

         <div className="flex text-end items-end mt-4 gap-1 text-gray-300 justify-self-end">
            <Calendar size={18} weight="fill" />
            <span className="text-xs text-gray-300 font-medium">{endDate.toString()}</span>   
         </div>   

         <ActivityCardOptions 
            idActivity={id}
            elementRef={elementRef}
            optionsIsOpen={optionsIsOpen}
            onCloseOptions={handleCloseOptions}    
         />
      </div>
   )
}