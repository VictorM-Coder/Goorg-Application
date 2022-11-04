import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { ShareNetwork, X } from 'phosphor-react';
import { useEffect, useRef } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';
import * as yup from 'yup';
import { WorkspaceModalProps, WorkspaceReq } from '../../@types/Workspace';

import { useWorkspaces } from '../../hooks';
import { api } from '../../services/api';
import { styleModal } from '../../utils';
import { Button } from '../Button';
import { FormControl, Input, Label, Textarea } from '../Form';

const WorkspaceSchema = yup.object({
   name: yup.string().required("O nome é obrigatório."),
}).required();

export function WorkspaceModal({ 
   isEditWorkspace, 
   workspaceId, 
   isOpenWorkspaceModal, 
   onCloseWorkspaceModal 
}: WorkspaceModalProps) {
      
   const isFirst = useRef(false);
   const { addNewWorkspace, editWorkspaceById } = useWorkspaces()

   const { 
      register, 
      handleSubmit, 
      reset, 
      setValue, 
      clearErrors, 
      formState: { errors } 
   } = useForm<WorkspaceReq>({ resolver : yupResolver(WorkspaceSchema) });

   const handleSubmitWorkspace: SubmitHandler<WorkspaceReq> = (data) => {
      if (!isEditWorkspace) {
         addNewWorkspace(data).then(() => { 
            onCloseWorkspaceModal();
            reset({ name: '', description: '' });
            clearErrors();
         });
      } else {
         editWorkspaceById(Number(workspaceId), data).then(() => {
            onCloseWorkspaceModal();
         });
      }  
   }

   function fetchDataWorkspace() {
      if (isEditWorkspace) {
         api.get(`workspace/${workspaceId}`)
         .then(res => {
            setValue('name', res.data.name);
            setValue('description', res.data.description);
         });
      }
   }

   useEffect(() => {
      if (isFirst.current && isEditWorkspace && isOpenWorkspaceModal) {
         fetchDataWorkspace();
      } 

      return () => { isFirst.current = true } 
   }, [isOpenWorkspaceModal])

   return (
      <Modal 
         open={isOpenWorkspaceModal}
         onClose={onCloseWorkspaceModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={styleModal}>
            <div className="p-8 flex flex-col gap-8 w-[500px]">
               <header className="text-blue-primary flex items-center justify-between">
                  <span className="flex items-center gap-1 font-semibold text-[15px]">
                     <ShareNetwork size={20} />
                     { (isEditWorkspace) ? 'Editar Workspace' : 'Novo Worskpace' }
                  </span>
                  <button onClick={() => { onCloseWorkspaceModal(), clearErrors(), reset() }}>
                     <X size={22} weight="bold"/>
                  </button>
               </header>

               <form className="flex flex-col gap-3" onSubmit={handleSubmit(handleSubmitWorkspace)}>
                  <FormControl>
                     <Label name="Nome" htmlFor="name"/>
                     <Input 
                        name="name" 
                        type="text" 
                        errorMensage={errors.name?.message}
                        register={register} 
                     />
                  </FormControl>

                  <FormControl>
                     <Label name="Descrição" htmlFor="description"/>
                     <Textarea 
                        name="description" 
                        register={register}
                     />
                  </FormControl>
               
                  <Button 
                    text={(!isEditWorkspace) ? 'Novo Workspace' : 'Editar Workspace'}
                    textColor='text-white'
                    bg='bg-blue-primary'
                    icon={ <ShareNetwork size={18} /> }
                  />
               </form>
            </div>
         </Box>
      </Modal>
   )
}