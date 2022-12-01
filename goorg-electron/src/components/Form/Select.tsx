import classNames from "classnames";
import { UseFormRegister } from "react-hook-form"

interface SelectProps {
   name: string;
   errorMessage?: string | undefined;
   register: UseFormRegister<any>
   options: {
      id: number;
      name: string;
   }[]
}

export function Select({ name, options, errorMessage, register }: SelectProps) {
   return (
      <>
         <select 
            className={classNames('border w-full px-3 py-2 rounded text-[13px] text-gray-500 outline-none focus:bg-transparent', {
               'border-red-400 bg-transparent': !!errorMessage,
               'border-gray-200 bg-gray-200 focus:border-gray-300': !(!!errorMessage)
            })}
            {...register(name)}         
         >
            <option value="">Selecione</option>
            { options.map(option => <option key={option.id} value={option.id}>{option.name}</option>) }
         </select>
         <span className="text-xs text-red-500">{errorMessage}</span>
      </>
   )
}