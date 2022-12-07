import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { Notepad } from 'phosphor-react';
import { SubmitHandler, useForm } from 'react-hook-form';

import { useActivities } from '../../../../../hooks';
import { styleModal } from '../../../../../utils';
import { Button } from '../../../../Button';
import { FormControl, Input, Label } from '../../../../Form';
import { HeaderModal } from '../../../../Modal';
import { TaskSchemaYup } from '../../../../../validations/TaskSchemaYup'

interface TaskModalFields {
   title: string;
}

interface TaskModalProps {
   idActivity: string;
   isOpenTaskModal: boolean; 
   onCloseTaskModal: () => void;
   isFromPomodoro?: boolean;
}

export function TaskCreate({ 
   idActivity,
   isOpenTaskModal,
   onCloseTaskModal,
   isFromPomodoro = false
}: TaskModalProps) {
 
   const { createTask } = useActivities();
   const { register, handleSubmit, formState: { errors } } = useForm<TaskModalFields>({ resolver: yupResolver(TaskSchemaYup)});

   const handleSubmitData: SubmitHandler<TaskModalFields> =  ({ title }) => {
      createTask({ 
         title, 
         activity: { id: idActivity },
         fromPomodoro: isFromPomodoro
      })
      .then(() => onCloseTaskModal());
   }

   return (
      <Modal 
         open={isOpenTaskModal}
         onClose={onCloseTaskModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={styleModal}>
            <div className="flex flex-col gap-8 w-[500px]">
               <HeaderModal 
                  title="Nova Tarefa"
                  textColor="text-blue-500"
                  icon={<Notepad size={22} weight="bold"/>}
                  handleFunctionCalback={onCloseTaskModal}
               />
               <form className="flex flex-col gap-3 px-8 pb-8" onSubmit={handleSubmit(handleSubmitData)}>
                  <FormControl>
                     <Label name="Nome" htmlFor="name"/>
                     <Input 
                        type="text" 
                        name="title" 
                        register={register}
                        errorMensage={errors.title?.message}
                     />
                  </FormControl>
                     
                  <Button
                     bg='bg-blue-500'
                     textColor='text-white'
                     text="Nova Tarefa"
                  />
               </form>
            </div>
         </Box>
      </Modal>
   )
}