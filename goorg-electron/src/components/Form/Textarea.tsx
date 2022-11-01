import { UseFormRegister } from "react-hook-form";

interface TextareaProps {
   name: string;
   register: UseFormRegister<any>
}

export function Textarea({ name, register }: TextareaProps) {
   return (
      <textarea 
         className="w-full px-3 py-2 rounded text-[13px] text-gray-500
         bg-gray-200 border border-gray-200 outline-none focus:border-gray-300 focus:bg-transparent"
         {...register(name)}
      >
      </textarea>
   )
}