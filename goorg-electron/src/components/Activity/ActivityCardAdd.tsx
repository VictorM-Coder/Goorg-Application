import { PlusCircle } from "phosphor-react";
import { useState } from "react";
import ilus_addActivity from "../../assets/add_activity.svg"


export function ActivityAddCard() {
   const [isOpenWorkspaceModal, setIsOpenWorkspaceModal] = useState(false);
   const handleOpenWorkspaceModal = () => setIsOpenWorkspaceModal(true);
   const handleCloseWorkspaceModal = () => setIsOpenWorkspaceModal(false);

   return (
      <>
         <div 
            className='w-[350px] bg-orange-200 flex items-center justify-between 
            px-5 pt-2 rounded min-h-[172px]'
            role="button"
            onClick={handleOpenWorkspaceModal}
         >
            <div className='flex flex-col gap-3 text-orange-500'>
               <span className='font-semibold'>Adicionar Atividade</span>
               <PlusCircle size={44}/>
            </div>
            <div className='self-end'>
               <img src={ilus_addActivity} alt="" className='w-full'/>
            </div>
         </div>
      </>
   )
}