import { yupResolver } from '@hookform/resolvers/yup';
import { Box, Modal } from '@mui/material';
import { BookmarkSimple } from 'phosphor-react';
import { SubmitHandler, useForm } from 'react-hook-form';

import { ModalProps } from '../../@types/global/ModalProps';
import { Button } from '../../components/Button';
import { FormControl, Input, Label } from '../../components/Form';
import { HeaderModal } from '../../components/Modal';
import { styleModal } from '../../utils';
import { ActivityTagSchemaYup } from '../../validations';

import colorpicker from '../../assets/colorpicker.png'
import { useActivities } from '../../hooks';
import { dark } from '@mui/material/styles/createPalette';

interface ActivityTagModalFields {
   name: string;
   color: string;
}

export function ActivityTagCreate({ isOpenModal, onCloseModal }: ModalProps) {
   const { register, reset, handleSubmit, watch, clearErrors, formState: { errors } } = 
   useForm<ActivityTagModalFields>({ resolver: yupResolver(ActivityTagSchemaYup) })

   const { createTag } = useActivities();

   const handleSubmitData:SubmitHandler<ActivityTagModalFields> = (data) => {
      createTag({ 
         name: data.name,
         color: data.color.split('#')[1]
      })
      .then(() => { onCloseModal(), reset() })
   }

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
                  icon={<BookmarkSimple size={22} />}
                  handleFunctionCalback={() => { reset(), clearErrors(), onCloseModal()}}
               />

               <div className='px-8 py-4 pb-8 flex flex-col justify-center items-center gap-4'>
                  <form className='w-full flex flex-col gap-3' onSubmit={handleSubmit(handleSubmitData)}>
                     <FormControl>
                        <Label name="Nome" htmlFor="name"/>
                        <Input 
                           type="text" 
                           name="name" 
                           errorMensage={errors.name?.message}
                           register={register}
                        />
                     </FormControl>
                     <FormControl>
                        <Label name="Cor da Badge" htmlFor=""/>
                        <div className='flex items-center gap-2 relative'>
                           <label htmlFor="color" className='border border-gray-200 px-3 py-2 rounded'>
                              <img src={colorpicker} alt="" className='w-6' />
                           </label>
                           <input 
                              type="color" 
                              id='color' 
                              className='w-0 invisible absolute top-2 left-0 text-sm'
                              {...register('color')}
                           />
                           <div className='flex items-center justify-between border border-gray-200 w-full text-xs p-2 rounded'>
                              <span>Pré visualização</span>
                              <span 
                                 style={{ backgroundColor: watch('color')}} 
                                 className="py-1 px-3 rounded-full text-xs text-white"
                              >
                                 { watch('name') }
                              </span>
                           </div>
                        </div>     
                     </FormControl>
                     <Button
                        bg='bg-blue-400'
                        textColor='text-white'
                        text='Criar Nova TAG'
                        icon={<BookmarkSimple size={20} />}
                     />
                  </form>             
               </div>
            </div>
         </Box>
      </Modal>
   )
}