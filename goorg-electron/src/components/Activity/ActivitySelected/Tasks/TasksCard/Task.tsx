import { useState } from "react";
import { UseFormRegister } from "react-hook-form"
import { useActivities } from "../../../../../hooks";

interface TaskProps {
   id: number;
   title: string;
   complete: boolean;
   register: UseFormRegister<any>
}

export function Task({ register, id, title, complete }: TaskProps) {
   const [checked, setChecked] = useState(complete);
   const { completeTasks } = useActivities();

   async function handleClickTask() {
      if (complete) {
         await completeTasks([{ id, complete: false }]);
      }
         
      setChecked(!complete);
   }

   console.log(complete)

   return (
      <div className="bg-gray-100 p-4 rounded shadow relative after:content-[''] 
         after:w-[2px] after:h-3/4 after:rounded after:bg-cyan-500 after:absolute after:left-0 after:top-2"
      >
         <div className="flex items-center gap-2">
            <input 
               type="checkbox" 
               id={id.toString()} 
               checked={checked}
               onClick={handleClickTask}
               {...register(id.toString())}
               className="w-4 h-4 accent-cyan-300"
            />
            <span className="text-[13px] font-medium text-gray-800">
               { title }
            </span>
         </div>
      </div>
   )
}