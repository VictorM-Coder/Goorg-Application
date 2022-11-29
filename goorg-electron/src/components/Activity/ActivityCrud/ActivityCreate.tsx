import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { Notepad } from 'phosphor-react';
import { SubmitHandler, useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';

import { ActivityModalFields, ActivityModalProps } from '../../../@types/Activity';
import { useActivities } from '../../../hooks';
import { styleModal } from '../../../utils';
import { ActivitySchemaYup } from '../../../validations';
import { Button } from '../../Button';
import { FormControl, Input, Label, Select, Textarea } from '../../Form';
import { HeaderModal } from '../../Modal';

export const optionsSelect = [
   { name: "Importante", value: 3 },
   { name: "Relevante", value: 2 },
   { name: "Urgente", value: 1 },
]

export function ActivityCreate({ isOpenModal, onCloseModal }: ActivityModalProps) {
   const { id }  = useParams();
   const { createActivity } = useActivities();
   
   const { register, handleSubmit, reset, clearErrors, formState: { errors } } = 
   useForm<ActivityModalFields>({ resolver: yupResolver(ActivitySchemaYup) });

   const handleSubmitData: SubmitHandler<ActivityModalFields> = ({ title, date, description, priority }) => {
      const activity = { 
         title, description, 
         priorityTag: { 
            id: Number(priority), 
         }, 
         endDate: date, 
         workspace: {
            id: Number(id),
         },
         phase: "TO_DO"
      }

      createActivity(activity).then(() => { onCloseModal(), reset(), clearErrors() });
   }

   return (
      <Modal 
         open={isOpenModal}
         onClose={onCloseModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={styleModal}>
            <div className="flex flex-col gap-8 w-[500px]">
               <HeaderModal 
                  title='Nova Atividade'
                  textColor="text-orange-500"
                  icon={<Notepad size={22} weight="bold"/>}
                  handleFunctionCalback={onCloseModal}
               />
               <form className="flex flex-col gap-3 px-8 pb-8" onSubmit={handleSubmit(handleSubmitData)}>
                  <FormControl>
                     <Label name="Nome" htmlFor="name"/>
                     <Input 
                        type="text" 
                        name="title" 
                        errorMensage={errors.title?.message}
                        register={register}
                     />
                  </FormControl>
                     
                  <FormControl>
                     <Label name="Descrição" htmlFor="description"/>
                     <Textarea name="description" register={register}/>
                  </FormControl>

                  <FormControl>
                     <Label name="Data de Término" htmlFor="date"/>
                     <Input 
                        type="date" 
                        name="date"                
                        register={register} 
                     />
                  </FormControl>

                  <FormControl>
                     <Label name="Prioridade" htmlFor="priority"/>
                     <Select 
                        name="priority" 
                        options={optionsSelect} 
                        errorMessage={errors.priority?.message}
                        register={register}
                     />
                  </FormControl>

                  <Button 
                     bg='bg-orange-500'
                     textColor='text-white'
                     text='Nova Atividade'
                  />
               </form>
            </div>
         </Box>
      </Modal>
   )
}