import { ArrowLeft } from "phosphor-react";
import { useNavigate } from "react-router-dom";

interface BackpageProps {
   title: string;
}

export function Backpage({ title }: BackpageProps) {
   const navigate = useNavigate();
   function handleNavigateForPage() {
      navigate(-1);
   }

   return (
      <button 
         className="flex items-center justify-center gap-2 mb-2 text-[13px] font-medium
          text-blue-700 bg-white px-5 py-[2px] rounded-full border border-blue-400
          hover:bg-blue-600 hover:text-white transition-colors"
         onClick={handleNavigateForPage}
      >
         <ArrowLeft size={18} />
         { title }
      </button>
   )
}