import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { ShareNetwork } from 'phosphor-react';
import { SubmitHandler, useForm } from 'react-hook-form';

import { WorkspaceCrud, WorkspaceModalProps } from '../../../@types/Workspace';
import { useWorkspaces } from '../../../hooks';
import { styleModal, WorkspaceSchemaYup } from '../../../utils';
import { Button } from '../../Button';
import { FormControl, Input, Label, Textarea } from '../../Form';
import { HeaderModal } from '../../Modal';

export function WorkspaceCreate({ isOpenModal,onCloseModal}: WorkspaceModalProps) {
   const { reateWorkspace: addNewWorkspace } = useWorkspaces()

   const { register, handleSubmit, reset, clearErrors, formState: { errors } } = 
   useForm<WorkspaceCrud>({ resolver : yupResolver(WorkspaceSchemaYup) });

   const handleSubmitWorkspace: SubmitHandler<WorkspaceCrud> = (data) => {
      addNewWorkspace(data).then(() => { 
         onCloseModal();
         reset({ name: '', description: '' });
         clearErrors();
      });
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
                    text='Novo Workspace'
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