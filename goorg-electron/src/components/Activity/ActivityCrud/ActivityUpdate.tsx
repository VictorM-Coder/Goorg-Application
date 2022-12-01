import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { Notepad } from 'phosphor-react';
import { useEffect, useState } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';

import { ActivityModalFields, ActivityModalProps, ActvityOptionsSelect } from '../../../@types/Activity';
import { useActivities } from '../../../hooks';
import { api } from '../../../services/api';
import { styleModal } from '../../../utils';
import { ActivitySchemaYup } from '../../../validations';
import { Button } from '../../Button';
import { FormControl, Input, Label, Select, Textarea } from '../../Form';
import { HeaderModal } from '../../Modal';

export function ActivityUpdate({ idActivity, isOpenModal, onCloseModal }: ActivityModalProps) {
   const [prioritiesTags, setPrioritiesTags] = useState<ActvityOptionsSelect[]>([]);

   const { id }  = useParams();
   const { updateActivity } = useActivities();
   
   const { register, handleSubmit, setValue, clearErrors, formState: { errors } } = 
   useForm<ActivityModalFields>({ resolver: yupResolver(ActivitySchemaYup) });

   const handleSubmitData: SubmitHandler<ActivityModalFields> = ({ title, date, description, priority }) => {
      const activity = { 
         id: idActivity,
         title, description, 
         priorityTag: { id: Number(priority) }, 
         endDate: date, 
         workspace: { id: Number(id) },
      }

      updateActivity(activity).then(() => { onCloseModal(), clearErrors() })     
   }

   async function fetchDataActivity() {
      const res = await api.get(`activity/${idActivity}`);
      setValue('title', res.data.title);
      setValue('description', res.data.description);
      setValue('date', res.data.endDate);
      setValue('priority', res.data.priorityTag.id);
   }

   function getTagsPriority() {
      api.get("priorityTag/all").then(res => setPrioritiesTags(res.data));
   }

   useEffect(() => {
      if (isOpenModal) {
         fetchDataActivity(); 
         getTagsPriority();
      }
   }, [isOpenModal])

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
                  title='Editar Atividade'
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
                        options={prioritiesTags} 
                        errorMessage={errors.priority?.message}
                        register={register}
                     />
                  </FormControl>

                  <Button 
                     bg='bg-orange-500'
                     textColor='text-white'
                     text='Editar Atividade'
                     icon={<Notepad size={20} />}
                  />
               </form>
            </div>
         </Box>
      </Modal>
   )
}