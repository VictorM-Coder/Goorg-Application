import { PlusCircle } from 'phosphor-react';
import { useState } from 'react';

import ilus_addActivity from '../../assets/add_activity.svg';
import { ActivityCreate } from './ActivityCrud/ActivityCreate';

export function ActivityAddCard() {
   const [isOpenActivityModal, setIsOpenActivityModal] = useState(false);
   const handleOpenActivityModal = () => setIsOpenActivityModal(true);
   const handleCloseActivityModal = () => setIsOpenActivityModal(false);

   return (
      <>
         <div 
            className='w-[350px] bg-orange-200 flex items-center justify-between 
            px-5 pt-2 rounded min-h-[172px]'
            role="button"
            onClick={handleOpenActivityModal}
         >
            <div className='flex flex-col gap-3 text-orange-500'>
               <span className='font-semibold'>Adicionar Atividade</span>
               <PlusCircle size={44}/>
            </div>
            <div className='self-end'>
               <img src={ilus_addActivity} alt="" className='w-full'/>
            </div>
         </div>

         <ActivityCreate 
            isOpenModal={isOpenActivityModal} 
            onCloseModal={handleCloseActivityModal}
            isSelectWorkspace={true}
         />
      </>
   )
}