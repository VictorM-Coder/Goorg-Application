import { Pencil, Stack, Trash } from "phosphor-react";
import { useState } from "react";
import { useActivities } from "../../../hooks";
import { ActivityModal, DeleteConfirmModal } from "../../Modal";
import { PopoverItem, PopoverRoot } from "../../Popover";

interface ActivityCardOptionsProps {
   idActivity: number;
   onCloseOptions: () => void;
   optionsIsOpen: boolean;
   elementRef: HTMLButtonElement | null;
}

export function ActivityCardOptions({ idActivity, onCloseOptions, optionsIsOpen, elementRef }: ActivityCardOptionsProps) {
   const [isOpenConfirmDeleteModal, setIsOpenDeleteConfirmModal] = useState(false);
   const [isOpenActivityModal, setIsOpenActivityModal] = useState(false);
   
   const { deleteActivityById, finishActivityById } = useActivities();
   const idRef = optionsIsOpen ? 'simple-popover' : undefined;

   const handleFinishActivity = () => {
      finishActivityById(idActivity).then(() => onCloseOptions());
   }

   const handleOpenActivityModal = () => setIsOpenActivityModal(true);
   const handleCloseActivityModal = () => setIsOpenActivityModal(false);

   const handleOpenDeleteConfirmModal = () => setIsOpenDeleteConfirmModal(true);
   const handleCloseDeleteConfirmModal = () => setIsOpenDeleteConfirmModal(false);
   
   return (
      <> 
         <DeleteConfirmModal 
            isOpenDeleteConfirmModal={isOpenConfirmDeleteModal}
            onCloseDeleteConfirmModal={handleCloseDeleteConfirmModal}
            onDeleteElement={() => deleteActivityById(idActivity)}
         />

         <ActivityModal 
            idActivity={idActivity}
            isEditActvity={true}
            isOpenActivityModal={isOpenActivityModal}
            onCloseActivityModal={handleCloseActivityModal}
         /> 
         
         <PopoverRoot 
            id={idRef}
            isOpen={optionsIsOpen}
            elementAnchor={elementRef}
            handleClose={onCloseOptions}
         >
            <PopoverItem name='Finalizar Atividade' onFunctionClick={handleFinishActivity}>
               <Stack size={18} />
            </PopoverItem>
            <PopoverItem name='Editar' onFunctionClick={handleOpenActivityModal}>
               <Pencil size={18} />
            </PopoverItem>
            <PopoverItem name='Excluir' onFunctionClick={handleOpenDeleteConfirmModal}>
               <Trash size={18} />
            </PopoverItem>
         </PopoverRoot>
      </>
   )
}