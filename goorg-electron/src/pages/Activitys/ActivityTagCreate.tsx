import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { IdentificationCard } from 'phosphor-react';
import { useForm } from 'react-hook-form';

import { ModalProps } from '../../@types/global/ModalProps';
import { Button } from '../../components/Button';
import { FormControl, Input, Label } from '../../components/Form';
import { HeaderModal } from '../../components/Modal';
import { styleModal } from '../../utils';
import { ActivityTagSchemaYup } from '../../validations';

import colorpicker from '../../assets/colorpicker.png'

interface ActivityTagModalFields {
   name: string;
}

export function ActivityTagCreate({ isOpenModal, onCloseModal }: ModalProps) {
   const { register, handleSubmit, formState: { errors } } = 
   useForm<ActivityTagModalFields>({ resolver: yupResolver(ActivityTagSchemaYup) })

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
                  title='Nova TAG'
                  textColor="text-blue-400"
                  icon={<IdentificationCard size={24} weight="fill" />}
                  handleFunctionCalback={onCloseModal}
               />

               <div className='px-8 py-4 pb-8 flex flex-col justify-center items-center gap-4'>
                  <form className='w-full flex flex-col gap-3'>
                     <FormControl>
                        <Label name="Nome" htmlFor="name"/>
                        <Input 
                           type="text" 
                           name="title" 
                           errorMensage={errors.name?.message}
                           register={register}
                        />
                     </FormControl>
                     <FormControl>
                        <div className='flex items-center gap-2 relative'>
                           <label htmlFor="color" className='border border-gray-200 px-3 py-2 rounded'>
                              <img src={colorpicker} alt="" className='w-6' />
                           </label>
                           <input type="color" id='color' name='color' className='w-0 invisible absolute top-2 left-0'/>
                           <span className='border border-gray-200 w-full text-xs p-3 rounded'>
                              Pré visualização
                           </span>
                        </div>     
                     </FormControl>
                  </form>
                  <Button
                     bg='bg-blue-400'
                     textColor='text-white'
                     text='Criar Nova TAG'
                     onClickFunction={onCloseModal}
                  />
               </div>
            </div>
         </Box>
      </Modal>
   )
}