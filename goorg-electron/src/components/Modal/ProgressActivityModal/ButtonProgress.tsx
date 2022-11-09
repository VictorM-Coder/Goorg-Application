import classNames from "classnames";

interface ButtonProgressProps {
   bgColor: string;
   name: string;
   isActive: string;
   handleFunctionCalback: (name: string) => void;
}

export function ButtonProgress({ bgColor, name, isActive, handleFunctionCalback }: ButtonProgressProps) {
   return (
      <button 
         className={classNames(`w-28 ${bgColor} h-16 rounded font-medium border`, {
            'border-gray-700': isActive === name,
            'border-transparent': isActive != name
         })}
         onClick={() => handleFunctionCalback(name)}
      >
         {name}
      </button>
   )
}