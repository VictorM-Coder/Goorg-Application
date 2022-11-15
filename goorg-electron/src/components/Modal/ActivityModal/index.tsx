import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { Notepad } from 'phosphor-react';
import { useEffect, useRef } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';
import * as yup from 'yup';

import { ActivityModalProps } from '../../../@types/Activity';
import { useActivities } from '../../../hooks';
import { api } from '../../../services/api';
import { styleModal } from '../../../utils';
import { Button } from '../../Button';
import { FormControl, Input, Label, Select, Textarea } from '../../Form';
import { HeaderModal } from '../Header';

export const optionsSelect = [
   { name: "Importante", value: 3 },
   { name: "Relevante", value: 2 },
   { name: "Urgente", value: 1 },
]

interface ActivityModalFields {
   title: string;
   description: string;
   date: string;
   priority: number;
}

const ActivitySchema = yup.object({
   title: yup.string().required('O nome é obrigatório.'),
   priority: yup.string().required('A prioridade é obrigatória.')
}).required();

export function ActivityModal({ 
   idActivity,
   isOpenActivityModal, 
   onCloseActivityModal, 
   isEditActvity 
}: ActivityModalProps) {
   const { id }  = useParams();
   const { createActivity, updateActivity } = useActivities();
   
   const { 
      register, 
      handleSubmit,
      setValue,
      reset,
      clearErrors,
      formState: { errors } 
   } = useForm<ActivityModalFields>({ resolver: yupResolver(ActivitySchema) });

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
      }

      if (!isEditActvity) {
         const newActivity = Object.assign(activity, { phase: "TO_DO" })
         createActivity(newActivity).then(() => { onCloseActivityModal(), reset(), clearErrors() });
      } else {
         const activityEdit = Object.assign(activity, { id: idActivity })
         updateActivity(activityEdit).then(() => { onCloseActivityModal(), clearErrors() })
      }
   }

   async function fetchDataActivity() {
      const res = await api.get(`activity/${idActivity}`);
      setValue('title', res.data.title);
      setValue('description', res.data.description);
      setValue('date', res.data.endDate);
      setValue('priority', res.data.priorityTag.id);
   }

   useEffect(() => {
      // if (isFirst.current && isOpenActivityModal && isEditActvity) {
      //    fetchDataActivity(); 
      // } comenta no build

      if (isOpenActivityModal && isEditActvity) {
         fetchDataActivity(); 
      }

      // return () => { isFirst.current = true } comenta no build
   }, [isOpenActivityModal])

   return (
      <Modal 
         open={isOpenActivityModal}
         onClose={onCloseActivityModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={styleModal}>
            <div className="flex flex-col gap-8 w-[500px]">
               <HeaderModal 
                  title={ !isEditActvity ? 'Nova Atividade' : 'Editar Atividade' }
                  textColor="text-orange-500"
                  icon={<Notepad size={22} weight="bold"/>}
                  handleFunctionCalback={onCloseActivityModal}
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
                     text={ (!isEditActvity) ? 'Nova Atividade' : 'Editar Atividade' }
                  />
               </form>
            </div>
         </Box>
      </Modal>
   )
}