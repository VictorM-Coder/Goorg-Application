import { DotsThree, Pencil, Stack, Trash } from 'phosphor-react';
import { MouseEvent, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

import { useWorkspaces } from '../../hooks/useWorkspaces';
import { Header } from '../Header';
import { WorkspaceModal } from '../Modal';
import { PopoverItem, PopoverRoot } from '../Popover';
import { Sidebar } from '../Sidebar';

export function WorkspaceSelected() {
   const [isOpenWorkspaceModal, setIsOpenWorkspaceModal] = useState(false);
   const [elementRef, setElementRef] = useState<HTMLButtonElement | null>(null);
   
   const { workspaces, deleteWorkspaceById } = useWorkspaces();
   const { id } = useParams();
   const navigate = useNavigate();

   const workSelected = workspaces.find(workspace => workspace.id.toString() === id);
   const popoverIsOpen = Boolean(elementRef);
   const idRef = popoverIsOpen ? 'simple-popover' : undefined;

   const handleOpenWorkspaceModal = () => setIsOpenWorkspaceModal(true);
   const handleCloseWorkspaceModal = () => setIsOpenWorkspaceModal(false)
   const handleOpenPopover = (event: MouseEvent<HTMLButtonElement>) => setElementRef(event.currentTarget);
   const handleClosePopover = () => setElementRef(null);

   const handleDeleteWorkspace = () => {
      deleteWorkspaceById(Number(id))
      .then(() => navigate('/'));
   }

   return (
      <div className="flex h-screen">
         <Sidebar />

         <div className="flex flex-1 flex-col rounded">
            <Header />

            <div className="m-5 flex flex-col gap-6 mt-24">
               <div className="flex flex-col bg-white rounded overflow-hidden px-10 py-10  shadow-sm">
                  <span className={`text-blue-600 flex items-center gap-1 font-medium`}>
                     { workSelected?.name }
                  </span>
                  <span className="text-[13px] text-gray-500">
                     { workSelected?.description }
                  </span>
                  <span className="text-[13px] mt-3 py-0.5 px-3 self-start rounded-full font-medium bg-blue-200 text-blue-600">
                     { workSelected?.countActivities } Atividades
                  </span>
               </div>
            </div>
         </div>

         <button 
            aria-labelledby={idRef}
            onClick={handleOpenPopover}
            className='bg-blue-primary text-white p-3 rounded-full fixed bottom-10 right-10 hover:bg-indigo-600 transition-colors'
         >
            <DotsThree size={24} weight="bold"/>
         </button>

         <WorkspaceModal 
            isEditWorkspace={true}
            workspaceId={id}
            isOpenWorkspaceModal={isOpenWorkspaceModal}
            onCloseWorkspaceModal={handleCloseWorkspaceModal}
         />

         <PopoverRoot 
            id={idRef}
            elementAnchor={elementRef}
            isOpen={popoverIsOpen}
            handleClose={handleClosePopover}
         >
            <PopoverItem name='Nova Atividade' onFunctionClick={() => true}>
               <Stack size={18} />
            </PopoverItem>
            <PopoverItem name='Editar' onFunctionClick={handleOpenWorkspaceModal}>
               <Pencil size={18} />
            </PopoverItem>
            <PopoverItem name='Excluir' onFunctionClick={handleDeleteWorkspace}>
               <Trash size={18} />
            </PopoverItem>
         </PopoverRoot>
      </div>
   )
}