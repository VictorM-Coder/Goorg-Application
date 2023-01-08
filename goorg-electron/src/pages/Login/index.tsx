import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { LogoLogin } from "../../components/Logo";
import { InformNameModal } from "./InformNameModal";

export function Login() {
   const [isOpenInformNameModal, setIsOpenInformNameModal] = useState(false);
   const navigate = useNavigate();

   function handleCloseInformNameModal() {
      setIsOpenInformNameModal(false);
   }

   function handleOpenInformNameModal() {
      setIsOpenInformNameModal(true);
   }

   return (
      <div className="min-h-screen bg-loginHero bg-center bg-cover bg-no-repeat">
         <header className="flex items-center px-10 py-6">
            <LogoLogin />
         </header>
         <div className="flex flex-col items-start justify-center gap-5 px-10 py-6 h-[65vh]">
            <div>
               <h1 className="text-[2.5rem] font-semibold text-white">Antes de tudo, organize-se.</h1>
               <p className="text-gray-200 text-lg max-w-[90%]">
                  Uma ferramenta para todos aqueles que
                  valorizam uma boa organização.
               </p>
            </div>
            <button 
               onClick={() => navigate('inicio')}
               className="text-xs font-medium uppercase px-6 py-3 rounded-full text-white bg-blue-dark-100"
            >
               Acessar
            </button>
         </div>

         <InformNameModal 
           isOpenInformNameModal={isOpenInformNameModal}
           onCloseInformNameModal={handleCloseInformNameModal}
         />
      </div>
   )
}