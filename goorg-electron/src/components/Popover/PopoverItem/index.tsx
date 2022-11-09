interface PopoverItemProps {
   name: string;
   children: React.ReactNode;
   onFunctionClick: () => void;
}

export function PopoverItem({ name, children, onFunctionClick }: PopoverItemProps) {
   return (
      <div 
         role="button" 
         className="flex items-center gap-2 text-gray-500 hover:text-gray-300 transition-colors"
         onClick={onFunctionClick}
      >
         { children }
         <span className="text-sm">{name}</span>
      </div>
   )
}