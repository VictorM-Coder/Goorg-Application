import { PlusCircle } from "phosphor-react";
import { useState } from "react";
import ilus_addWorkspace from '../../assets/add_workspace.svg'
import { WorkspaceModal } from "../Modal";

export function CardWorkspaceAdd() {
   const [isOpenWorkspaceModal, setIsOpenWorkspaceModal] = useState(false);
   const handleOpenWorkspaceModal = () => setIsOpenWorkspaceModal(true);
   const handleCloseWorkspaceModal = () => setIsOpenWorkspaceModal(false);

   return (
      <>
         <div 
            className='w-[300px] bg-blue-100 flex items-center justify-between 
            px-5 pt-2 rounded min-h-[172px]'
            role="button"
            onClick={handleOpenWorkspaceModal}
         >
            <div className='flex flex-col gap-3 text-blue-primary'>
               <span className='font-semibold'>Adcionar Workspace</span>
               <PlusCircle size={44}/>
            </div>
            <div className='self-end'>
               <img src={ilus_addWorkspace} alt="" className='w-full'/>
            </div>
         </div>
         
         <WorkspaceModal 
            isOpenWorkspaceModal={isOpenWorkspaceModal} 
            onCloseWorkspaceModal={handleCloseWorkspaceModal}
            isEditWorkspace={false}
         />
      </>
      
)
}