import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { Notepad, X } from 'phosphor-react';
import { useEffect, useRef } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';
import * as yup from 'yup';

import { ActivityModalProps } from '../../@types/Activity';
import { useActivities } from '../../hooks';
import { api } from '../../services/api';
import { styleModal } from '../../utils';
import { Button } from '../Button';
import { FormControl, Input, Label, Select, Textarea } from '../Form';

export const optionsSelect = [
   { name: "Importante", value: "Importante" },
   { name: "Relevante", value: "Relevante" },
   { name: "Urgente", value: "Urgente" },
]

interface ActivityModalFields {
   title: string;
   description: string;
   date: string;
   priority: string;
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
 
   const isFirst = useRef(false);
   const { id }  = useParams();
   const { createActivity, editActivityById } = useActivities();
   
   const { 
      register, 
      handleSubmit,
      setValue,
      reset,
      clearErrors,
      formState: { errors } 
   } = useForm<ActivityModalFields>({ resolver: yupResolver(ActivitySchema) });

   const handleSubmitData: SubmitHandler<ActivityModalFields> = ({ title, date, description, priority }) => {
      const dateEnd = new Date(date);
      const activity = { title, description, priorityTag: { name: priority }, endDate: dateEnd, workspace: {
         id: Number(id),
      }}

      if (!isEditActvity) {
         const newActivity = Object.assign(activity, {
            phase: "TO_DO",
         })
         createActivity(newActivity).then(() => { onCloseActivityModal(), reset(), clearErrors() });
      } else {
         editActivityById(Number(idActivity), activity).then(() => { onCloseActivityModal(), clearErrors() })
      }
   }

   async function fetchDataActivity() {
      const res = await api.get(`activity/${idActivity}`);
      setValue('title', res.data.title);
      setValue('description', res.data.description);
      setValue('date', res.data.endDate.toString());
      setValue('priority', res.data.priorityTag.name);
   }

   useEffect(() => {
      if (isFirst.current && isOpenActivityModal && isEditActvity) {
         fetchDataActivity();
      } 

      return () => { isFirst.current = true } 
   }, [isOpenActivityModal])

   return (
      <Modal 
         open={isOpenActivityModal}
         onClose={onCloseActivityModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={styleModal}>
            <div className="p-8 flex flex-col gap-8 w-[500px]">
               <header className="text-orange-400 flex items-center justify-between">
                  <span className="flex items-center gap-1 font-medium text-[15px]">
                     <Notepad size={22} weight="bold"/>
                     { !isEditActvity ? 'Nova Atividade' : 'Editar Atividade' }
                  </span>
                  <button onClick={() => { onCloseActivityModal(), clearErrors() }}>
                     <X size={22} weight="bold"/>
                  </button>
               </header>

               <form className="flex flex-col gap-3" onSubmit={handleSubmit(handleSubmitData)}>
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
                     bg='bg-orange-400'
                     textColor='text-white'
                     text={ (!isEditActvity) ? 'Nova Atividade' : 'Editar Atividade' }
                  />
               </form>
            </div>
         </Box>
      </Modal>
   )
}