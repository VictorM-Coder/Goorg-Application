import { Box, Modal } from "@mui/material";
import { X } from "phosphor-react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { LogoMinemized } from "../../components/Logo";

const style = {
   position: 'absolute' as 'absolute',
   top: '50%',
   left: '50%',
   transform: 'translate(-50%, -50%)',
   bgcolor: 'white',
   outline: 'none',
   height: 'auto',
   width: 'auto',
   borderRadius: '4px'
}

interface InformNameModalProps {
   isOpenInformNameModal: boolean;
   onCloseInformNameModal: () => void;
}

export function InformNameModal({ isOpenInformNameModal, onCloseInformNameModal }: InformNameModalProps) {
   const navigate = useNavigate();
   const [nameUser, setNameUser] = useState('');

   function handleClick() {
      localStorage.setItem('user', nameUser);
      navigate('/inicio')
   }

   return (
      <Modal 
         open={isOpenInformNameModal}
         onClose={onCloseInformNameModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={style}>
            <div className="p-8 flex flex-col gap-4 w-[500px]">
               <header className="text-blue-dark-200 flex items-center justify-between">
                  <span className="flex items-center gap-2 font-semibold text-[15px]">
                     <LogoMinemized size={42}/>
                     Bem vindo ao goorg
                  </span>
                  <button onClick={() => onCloseInformNameModal()}>
                     <X size={22} weight="bold"/>
                  </button>
               </header>

               <form className="flex flex-col gap-3">
                  <div className="flex flex-col gap-1 text-gray-600">
                     <label className="text-[13px] font-medium">Nome</label>
                     <input 
                        type="text"
                        className="w-full px-3 py-2 rounded text-[13px] text-gray-500 border border-gray-300 placeholder:text-gray-300 focus:outline-gray-300 focus:bg-transparent"
                        placeholder="Informe seu nome"
                        onChange={(event) => setNameUser(event.target.value)}
                     />
                  </div>
                  <button 
                     type="submit"
                     onClick={handleClick}
                     className="w-full mt-2 bg-blue-dark-100 text-[13px] font-medium text-white p-3 rounded hover:bg-blue-dark-200 transition-colors">
                     Acessar
                  </button>
               </form>
            </div>
         </Box>
      </Modal>
   )
}