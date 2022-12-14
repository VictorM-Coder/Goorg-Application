import { DotsThree, Trash } from "phosphor-react";
import { useState } from "react";
import { UseFormRegister } from "react-hook-form"
import { useActivities } from "../../../../../hooks";

interface TaskProps {
   id: number;
   title: string;
   status: boolean;
   register: UseFormRegister<any>
}

export function Task({ register, id, title, status }: TaskProps) {
   const [complete, setComplete] = useState(status);
   const { completeTasks, deleteTask } = useActivities();

   async function handleClickTask() {
      if (status) {
         await completeTasks([{ id, complete: false }]);
      }
         
      setComplete(!complete);
   }

   return (
      <div className="bg-gray-100 p-4 rounded shadow relative after:content-[''] 
         after:w-[2px] after:h-3/4 after:rounded after:bg-cyan-500 after:absolute after:left-0 after:top-2"
      >
         <div className="flex items-center justify-between">
            <div className="flex items-center gap-2">
               <input
                  type="checkbox"
                  id={id.toString()}
                  checked={complete}
                  onClick={handleClickTask}
                  {...register(id.toString())}
                  className="w-4 h-4 accent-cyan-300"
               />
               <span className="text-[13px] font-medium text-gray-800">
                  { title }
               </span>
            </div>
            <button className="text-orange-600" onClick={() => deleteTask(id).then(() => true)}>
               <Trash size={18}/>
            </button>
         </div>
      </div>
   )
}