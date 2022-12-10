import { X } from "phosphor-react"

interface HeaderModalProps {
   title: string;
   textColor: string;
   icon: React.ReactNode;
   handleFunctionCalback: () => void;
}

export function HeaderModal({ title, textColor, icon, handleFunctionCalback } : HeaderModalProps) {
   return (
      <header className={`${textColor} flex items-center justify-between px-8 pt-6 pb-4 border-b border-b-gray-100`}>
         <span className="flex items-center gap-1 font-medium text-[15px]">
            {icon}
            {title}
         </span>
         <button onClick={handleFunctionCalback}>
            <X size={22} weight="bold"/>
         </button>
      </header>
   )
}