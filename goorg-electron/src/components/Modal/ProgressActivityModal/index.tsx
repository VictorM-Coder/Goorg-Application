import { Box, Modal } from "@mui/material"
import { Kanban, X } from "phosphor-react"
import { useState } from "react";
import { ModalProps } from "../../../@types/global/ModalProps";
import { useActivities } from "../../../hooks";
import { styleModal } from "../../../utils"
import { Button } from "../../Button";
import { HeaderModal } from "../Header";
import { ButtonProgress } from "./ButtonProgress";

interface ProgressActivityModalProps extends ModalProps {
   idActivity: number;
   phaseNow: string;
}

export function ProgressActivityModal({ 
   idActivity, 
   phaseNow, 
   isOpenModal, 
   onCloseModal 
} : ProgressActivityModalProps) {
   
   const { editActivityById, activitys } = useActivities();
   const [progressActive, setProgressActive] = useState(phaseNow);
   const activitySearch = activitys.find(ac => ac.id === idActivity);
   
   const handleChangeProgressActive = (name: string) => setProgressActive(name)
   const handleEditPhaseActivity = () => {};
   
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
                  title="Alterar Estado"
                  textColor="text-green-500"
                  icon={<Kanban size={20}/>}
                  handleFunctionCalback={onCloseModal}
               />
               
               <div className='px-8 pb-8 flex flex-col gap-4'>
                  <span className="text-[13px] text-gray-700 font-medium">
                     Selecione o novo estado da atividade:
                  </span>

                  <div className="flex items-center justify-center gap-4 mb-4">
                     <ButtonProgress 
                        name="TO_DO" 
                        bgColor="bg-gray-200" 
                        isActive={progressActive}
                        handleFunctionCalback={handleChangeProgressActive} 
                     />
                     <ButtonProgress 
                        name="DONING" 
                        bgColor="bg-yellow-300" 
                        isActive={progressActive}
                        handleFunctionCalback={handleChangeProgressActive} 
                     />
                     <ButtonProgress 
                        name="DONE" 
                        bgColor="bg-green-400" 
                        isActive={progressActive}
                        handleFunctionCalback={handleChangeProgressActive} 
                     />
                  </div>

                  <Button
                     bg='bg-green-500'
                     textColor='text-white'
                     text='Alterar estado da atividade'
                     icon={<Kanban size={20}/>}
                     onClickFunction={handleEditPhaseActivity}
                  />
               </div> 
            </div>
         </Box>
      </Modal>
   )
}