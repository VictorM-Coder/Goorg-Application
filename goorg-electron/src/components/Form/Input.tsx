import classNames from "classnames";
import { UseFormRegister } from "react-hook-form";

interface InputProps {
   type: string;
   name: string;
   errorMensage?: string | undefined;
   register: UseFormRegister<any>
}

export function Input({ type, name, errorMensage = '', register }: InputProps) {
   return (
      <>
        <input 
            type={type}
            className={classNames('border w-full px-3 py-2 rounded text-[13px] text-gray-500 outline-none focus:bg-transparent', {
               'border-red-400 bg-transparent': !!errorMensage,
               'border-gray-200 bg-gray-200 focus:border-gray-300': !(!!errorMensage)
            })}
            {...register(name)}
         />
         <span className="text-xs text-red-500">{errorMensage}</span>
      </>
      
   )
}