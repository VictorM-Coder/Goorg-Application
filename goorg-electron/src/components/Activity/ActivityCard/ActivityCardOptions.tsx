import { Pencil, Stack, Trash } from "phosphor-react";
import { useState } from "react";
import { useActivities } from "../../../hooks";
import { DeleteConfirmModal } from "../../Modal";
import { PopoverItem, PopoverRoot } from "../../Popover";
import { ActivityUpdate } from "../ActivityCrud/ActivityUpdate";
import { ActivityUpdatePhase } from "../ActivityCrud/ActivityUpdatePhase";

interface ActivityCardOptionsProps {
   idActivity: number;
   phaseNow: string;
   onCloseOptions: () => void;
   optionsIsOpen: boolean;
   elementRef: HTMLButtonElement | null;
}

export function ActivityCardOptions({ 
   idActivity, 
   phaseNow, 
   onCloseOptions, 
   optionsIsOpen, 
   elementRef 
}: ActivityCardOptionsProps){

   const [isOpenConfirmDeleteModal, setIsOpenDeleteConfirmModal] = useState(false);
   const [isOpenActivityModal, setIsOpenActivityModal] = useState(false);
   const [isOpenProgressActivityModal, setIsOpenProgressActivityModal] = useState(false);
   
   const { deleteActivity: deleteActivityById } = useActivities();
   const idRef = optionsIsOpen ? 'simple-popover' : undefined;
   
   const handleOpenActivityModal = () => setIsOpenActivityModal(true);
   const handleCloseActivityModal = () => setIsOpenActivityModal(false);

   const handleOpenDeleteConfirmModal = () => setIsOpenDeleteConfirmModal(true);
   const handleCloseDeleteConfirmModal = () => setIsOpenDeleteConfirmModal(false);

   const handleOpenProgressActivityModal = () => setIsOpenProgressActivityModal(true);
   const handleCloseProgressActivityModal = () => setIsOpenProgressActivityModal(false);
   
   return (
      <> 
         <ActivityUpdate
            idActivity={idActivity}
            isOpenModal={isOpenActivityModal}
            onCloseModal={handleCloseActivityModal}
         /> 

         <ActivityUpdatePhase
            idActivity={idActivity}
            phaseNow={phaseNow.toUpperCase()}
            isOpenModal={isOpenProgressActivityModal}
            onCloseModal={handleCloseProgressActivityModal}
         />

         <DeleteConfirmModal 
            isOpenDeleteConfirmModal={isOpenConfirmDeleteModal}
            onCloseDeleteConfirmModal={handleCloseDeleteConfirmModal}
            onDeleteElement={() => deleteActivityById(idActivity)}
         />

         <PopoverRoot 
            id={idRef}
            isOpen={optionsIsOpen}
            elementAnchor={elementRef}
            handleClose={onCloseOptions}
         >
            <PopoverItem name='Alterar Estado' onFunctionClick={handleOpenProgressActivityModal}>
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