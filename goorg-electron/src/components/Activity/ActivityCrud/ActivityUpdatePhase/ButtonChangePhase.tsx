import classNames from "classnames";

interface ButtonChangePhaseProps {
   bgColor: string;
   name: string;
   isActive: string;
   handleFunctionCalback: (name: string) => void;
}

export function ButtonChangePhase({ bgColor, name, isActive, handleFunctionCalback }: ButtonChangePhaseProps) {
   return (
      <button 
         className={classNames(`w-28 ${bgColor} h-16 rounded font-medium border text-sm`, {
            'border-gray-700': isActive === name,
            'border-transparent': isActive != name
         })}
         onClick={() => handleFunctionCalback(name)}
      >
         {name}
      </button>
   )
}