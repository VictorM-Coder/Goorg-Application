import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { ShareNetwork, X } from 'phosphor-react';
import { useEffect } from 'react';
import { SubmitHandler, useForm } from 'react-hook-form';

import { WorkspaceCrud, WorkspaceModalProps } from '../../../@types/Workspace';
import { useWorkspaces } from '../../../hooks';
import { api } from '../../../services/api';
import { styleModal, WorkspaceSchemaYup } from '../../../utils';
import { Button } from '../../Button';
import { FormControl, Input, Label, Textarea } from '../../Form';
import { HeaderModal } from '../../Modal';

export function WorkspaceUpdate({ idWorkspace: workspaceId, isOpenModal,onCloseModal }: WorkspaceModalProps) {
   const { updateWorkspace } = useWorkspaces()

   const { register, handleSubmit, reset, setValue, clearErrors, formState: { errors } } = 
   useForm<WorkspaceCrud>({ resolver : yupResolver(WorkspaceSchemaYup) });

   const handleSubmitWorkspace: SubmitHandler<WorkspaceCrud> = (data) => {
      const workspacEdit = Object.assign(data, { id: workspaceId });
      updateWorkspace(workspacEdit).then(() => onCloseModal());
   }

   function fetchDataWorkspace() {
      api.get(`workspace/${workspaceId}`)
      .then(res => {
         setValue('name', res.data.name);
         setValue('description', res.data.description);
      });    
   }

   useEffect(() => {
      if (isOpenModal) {
         fetchDataWorkspace();
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
                  title='Nov Workspace'
                  textColor="text-blue-primary"
                  icon={<ShareNetwork size={20} />}
                  handleFunctionCalback={() => { onCloseModal(), clearErrors(), reset() }}
               />

               <form className="flex flex-col gap-3 px-8 pb-8" onSubmit={handleSubmit(handleSubmitWorkspace)}>
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
                    text='Editar Workspace'
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