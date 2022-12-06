import { Box, Modal } from '@mui/material';
import { File } from 'phosphor-react';

import { ModalProps } from '../../../../@types/global/ModalProps';
import iluDeleteModal from '../../../../assets/ilu-complete-tasks.svg';
import { styleModal } from '../../../../utils';
import { Button } from '../../../Button';
import { HeaderModal } from '../../../Modal';

interface CompleteTasksProps extends ModalProps {
   onConfirmComplete: () => void;
}

export function CompleteTasks({ isOpenModal, onCloseModal, onConfirmComplete }: CompleteTasksProps) {
   return (
      <Modal 
         open={isOpenModal}
         onClose={onCloseModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={styleModal}>
            <div className="flex flex-col w-[550px]">
               <HeaderModal 
                  title='Marcar tarefas como concluídas'
                  textColor="text-blue-400"
                  icon={<File size={22} weight="fill" />}
                  handleFunctionCalback={onCloseModal}
               />

               <div className='px-8 pb-8 flex flex-col justify-center items-center gap-4'>
                  <div className='flex flex-col items-center'>
                     <img src={iluDeleteModal} alt="" className='w-1/3 mb-3'/>
                     <span className='text-blue-400 text-center font-medium'>
                        Tem certeza que deseja marcar essas tarefas como concluídas?
                     </span>
                  </div>
                  
                  <div className='flex items-center gap-4'>
                     <Button
                        bg='bg-blue-400'
                        textColor='text-white'
                        text='Confirmar'
                        onClickFunction={onConfirmComplete}
                     />
                     <Button
                        bg='bg-slate-200'
                        textColor='text-gray-900'
                        text='Cancelar'
                        onClickFunction={onCloseModal}
                     />
                  </div>
               </div>
            </div>
         </Box>
      </Modal>
   )
}