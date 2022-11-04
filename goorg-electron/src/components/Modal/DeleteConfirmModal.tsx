import { Box, Modal } from '@mui/material';
import { Trash, X } from 'phosphor-react';

import iluDeleteModal from '../../assets/ilu_delete_modal.svg';
import { styleModal } from '../../utils';
import { Button } from '../Button';

interface DeleteConfirmModalProps {
   isOpenDeleteConfirmModal: boolean;
   onDeleteElement: () => void;
   onCloseDeleteConfirmModal: () => void;
}

export function DeleteConfirmModal({ 
   isOpenDeleteConfirmModal, 
   onCloseDeleteConfirmModal,
   onDeleteElement
}: DeleteConfirmModalProps) {

   return (
      <Modal 
         open={isOpenDeleteConfirmModal}
         onClose={onCloseDeleteConfirmModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={styleModal}>
            <div className="flex flex-col gap-8 w-[500px]">
               <header className="px-8 pt-6 pb-4 text-red-500 flex items-center justify-between border border-b-gray-100">
                  <span className="flex items-center gap-1 font-semibold text-[15px]">
                     <Trash size={18} weight="bold"/>
                     Excluir
                  </span>
                  <button onClick={onCloseDeleteConfirmModal}>
                     <X size={22} weight="bold"/>
                  </button>
               </header>

               <div className='px-8 pb-8 flex flex-col justify-center items-center gap-4'>
                  <div className=''>
                     <img src={iluDeleteModal} alt="" className='w-2/3 mx-auto mb-3'/>
                     <span className='text-red-500 font-medium'>Tem certeza que deseja excluir este item?</span>
                  </div>
                  
                  <div className='flex items-center gap-4'>
                     <Button
                        bg='bg-red-500'
                        textColor='text-white'
                        text='Confirmar'
                        onClickFunction={onDeleteElement}
                     />
                     <Button
                        bg='bg-slate-200'
                        textColor='text-gray-900'
                        text='Cancelar'
                        onClickFunction={onCloseDeleteConfirmModal}
                     />
                  </div>
               </div>
            </div>
         </Box>
      </Modal>
   )
}