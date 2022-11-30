import { DotsThree, Pencil, Stack } from "phosphor-react";
import { useState, MouseEvent } from "react";
import { ActivityCreate } from "../../components/Activity";
import { PopoverItem, PopoverRoot } from "../../components/Popover";
import { ActivityTagCreate } from "./ActivityTagCreate";


export function ActivityOptions() {
   const [elementRef, setElementRef] = useState<HTMLButtonElement | null>(null);
   const [isOpenAcitivityModal, setIsOpenActivityModal] = useState(false);
   const [isOpenAcitivityTagModal, setIsOpenActivityTagModal] = useState(false);

   const popoverIsOpen = Boolean(elementRef);
   const idRef = popoverIsOpen ? 'simple-popover' : undefined;

   const handleOpenPopover = (event: MouseEvent<HTMLButtonElement>) => setElementRef(event.currentTarget);
   const handleClosePopover = () => setElementRef(null);

   const handleOpenActivityModal = () => setIsOpenActivityModal(true);
   const handleCloseActivityModal = () => setIsOpenActivityModal(false);

   const handleOpenActivityTagModal = () => setIsOpenActivityTagModal(true);
   const handleCloseActivityTagModal = () => setIsOpenActivityTagModal(false);
   
   return (
      <div>
         <button 
            aria-labelledby={idRef}
            onClick={handleOpenPopover}
            className='bg-blue-primary text-white p-3 rounded-full fixed bottom-10 right-10 hover:bg-indigo-600 transition-colors'
         >
            <DotsThree size={24} weight="bold"/>
         </button>

         <ActivityCreate
            isOpenModal={isOpenAcitivityModal} 
            onCloseModal={handleCloseActivityModal}
            isSelectWorkspace={true}
         />

         <ActivityTagCreate 
            isOpenModal={isOpenAcitivityTagModal}
            onCloseModal={handleCloseActivityTagModal}
         />

         <PopoverRoot 
            id={idRef}
            elementAnchor={elementRef}
            isOpen={popoverIsOpen}
            handleClose={handleClosePopover}
         >
            <PopoverItem name='Nova Atividade' onFunctionClick={handleOpenActivityModal}>
               <Stack size={18} />
            </PopoverItem>
            <PopoverItem name='Criar Tag' onFunctionClick={handleOpenActivityTagModal}>
               <Pencil size={18} />
            </PopoverItem>
         </PopoverRoot>
      </div>
   )
}