import { UseFormRegister } from "react-hook-form"

interface TaskProps {
   id: string;
   register: UseFormRegister<any>
}

export function Task({ register, id }: TaskProps) {
   return (
      <div className="bg-gray-100 p-4 rounded shadow relative :after:content-[''] 
         after:w-[2px] after:h-3/4 after:rounded after:bg-cyan-500 after:absolute after:left-0 after:top-2"
      >
         <div className="flex items-center gap-2">
            <input type="checkbox" id={id} className="w-4 h-4 accent-cyan-300" {...register(id)}/>
            <span className="text-[13px] font-medium text-gray-800">
               Definir planos de gestão de trafego
            </span>
         </div>
      </div>
   )
}