import { DotsThree, Pencil, Stack, Trash } from "phosphor-react";
import { useState, MouseEvent } from "react";
import { useNavigate } from "react-router-dom";
import { useWorkspaces } from "../../../hooks";
import { ActivityCreate } from "../../Activity/ActivityCrud/ActivityCreate";
import { DeleteConfirmModal } from "../../Modal";
import { PopoverItem, PopoverRoot } from "../../Popover";
import { WorkspaceUpdate } from "../WorkspaceCrud/WorkspaceUpdate";

interface WorkspaceOptionsProps {
   idWorkspace: string | undefined;
}

export function WorkspaceOptions({ idWorkspace }: WorkspaceOptionsProps) {
   const { deleteWorkspace: deleteWorkspaceById } = useWorkspaces();
   const navigate = useNavigate();
   
   const [isOpenNewAcitivityModal, setIsOpenNewActivityModal] = useState(false);
   const [isOpenWorkspaceModal, setIsOpenWorkspaceModal] = useState(false);
   const [isOpenConfirmDeleteModal, setIsOpenDeleteConfirmModal] = useState(false);
   const [elementRef, setElementRef] = useState<HTMLButtonElement | null>(null);

   const popoverIsOpen = Boolean(elementRef);
   const idRef = popoverIsOpen ? 'simple-popover' : undefined;

   const handleOpenPopover = (event: MouseEvent<HTMLButtonElement>) => setElementRef(event.currentTarget);
   const handleClosePopover = () => setElementRef(null);

   const handleOpenNewActivityModal = () => setIsOpenNewActivityModal(true);
   const handleCloseNewActivityModal = () => setIsOpenNewActivityModal(false);

   const handleOpenWorkspaceModal = () => setIsOpenWorkspaceModal(true);
   const handleCloseWorkspaceModal = () => setIsOpenWorkspaceModal(false)

   const handleOpenDeleteConfirmModal = () => setIsOpenDeleteConfirmModal(true);
   const handleCloseDeleteConfirmModal = () => setIsOpenDeleteConfirmModal(false);
   
   const handleDeleteWorkspace = () => { 
      deleteWorkspaceById(Number(idWorkspace)).then(() => navigate('/inicio'));
   }
   
   return (
      <div>
         <button 
            aria-labelledby={idRef}
            onClick={handleOpenPopover}
            className='bg-blue-primary text-white p-3 rounded-full fixed bottom-10 right-10 hover:bg-indigo-600 transition-colors'
         >
            <DotsThree size={24} weight="bold"/>
         </button>

         <ActivityCreate
            isOpenModal={isOpenNewAcitivityModal} 
            onCloseModal={handleCloseNewActivityModal}
         />

         <WorkspaceUpdate 
            idWorkspace={idWorkspace}
            isOpenModal={isOpenWorkspaceModal}
            onCloseModal={handleCloseWorkspaceModal}
         />

         <DeleteConfirmModal 
            isOpenDeleteConfirmModal={isOpenConfirmDeleteModal}
            onCloseDeleteConfirmModal={handleCloseDeleteConfirmModal}
            onDeleteElement={handleDeleteWorkspace}
         />

         <PopoverRoot 
            id={idRef}
            elementAnchor={elementRef}
            isOpen={popoverIsOpen}
            handleClose={handleClosePopover}
         >
            <PopoverItem name='Nova Atividade' onFunctionClick={handleOpenNewActivityModal}>
               <Stack size={18} />
            </PopoverItem>
            <PopoverItem name='Editar' onFunctionClick={handleOpenWorkspaceModal}>
               <Pencil size={18} />
            </PopoverItem>
            <PopoverItem name='Excluir' onFunctionClick={handleOpenDeleteConfirmModal}>
               <Trash size={18} />
            </PopoverItem>
         </PopoverRoot>
      </div>
   )
}