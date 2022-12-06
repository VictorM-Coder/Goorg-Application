interface ButtonProps  {
   text: string;
   textColor: string;
   bg: string;
   icon?: React.ReactNode;
   onClickFunction?: () => void;
}

export function Button({ text, bg, textColor, icon, onClickFunction }: ButtonProps) {
   return (
      <button 
         className={`${ bg } ${ textColor } w-full mt-2 text-[13px] font-medium 
         px-10 py-3 rounded flex items-center justify-center relative overflow-hidden`}
         onClick={onClickFunction}
      > 
        <span className="absolute left-0 top-0 p-3">{ icon }</span>
        <span className="tex">{ text }</span>
      </button>
   )
}