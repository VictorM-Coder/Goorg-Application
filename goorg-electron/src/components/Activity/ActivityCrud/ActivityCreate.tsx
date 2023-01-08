import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { Notepad } from 'phosphor-react';
import { useEffect, useState } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';
import { ActivityModalFields, ActivityModalProps, ActvityOptionsSelect } from '../../../@types/Activity';
import { useActivities, useWorkspaces } from '../../../hooks';
import { api } from '../../../services/api';
import { styleModal } from '../../../utils';
import { ActivitySchemaYup } from '../../../validations';
import { Button } from '../../Button';
import { FormControl, Input, Label, Select, Textarea } from '../../Form';
import { HeaderModal } from '../../Modal';

export function ActivityCreate({ isOpenModal, onCloseModal, isSelectWorkspace, phase }: ActivityModalProps) {
   const [prioritiesTags, setPrioritiesTags] = useState<ActvityOptionsSelect[]>([]);

   const { id }  = useParams();
   const { createActivity } = useActivities();
   const { workspaces } = useWorkspaces();
   
   const { register, handleSubmit, reset, clearErrors, setValue, formState: { errors } } = 
   useForm<ActivityModalFields>({ resolver: yupResolver(ActivitySchemaYup) });

   const handleSubmitData: SubmitHandler<ActivityModalFields> = ({ title, date, description, priority, workspace }) => {
      const activity = { 
         title, description, 
         priorityTag: { id: Number(priority) }, 
         endDate: date, 
         workspace: { id: (isSelectWorkspace) ? Number(workspace) : Number(id)},
         phase: phase ? phase : "TO_DO"
      }

      createActivity(activity).then(() => { onCloseModal(), reset(), clearErrors() });
   }

   function getTagsPriority() {
      api.get("priorityTag/all").then(res => setPrioritiesTags(res.data));
   }

   useEffect(() => {
      if (isOpenModal) {
         if (!isSelectWorkspace) setValue('workspace', 0);
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
                  title='Nova Atividade'
                  textColor="text-orange-500"
                  icon={<Notepad size={22} weight="bold"/>}
                  handleFunctionCalback={onCloseModal}
               />
               <form className="flex flex-col gap-3 px-8 pb-8" onSubmit={handleSubmit(handleSubmitData)}>
                  {
                     isSelectWorkspace ?
                     <FormControl>
                        <Label name="Workspace" htmlFor="workspace"/>
                        <Select 
                           name="workspace" 
                           options={workspaces.map(w => { return { name: w.name, id: w.id } } )} 
                           errorMessage={errors.workspace?.message}
                           register={register}
                        />
                     </FormControl>
                     : <></>
                  }
                  
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
                     text='Nova Atividade'
                     icon={<Notepad size={20}/>}
                  />
               </form>
            </div>
         </Box>
      </Modal>
   )
}