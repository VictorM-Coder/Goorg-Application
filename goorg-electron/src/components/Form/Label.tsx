interface LabelProps {
   name: string;
   htmlFor: string;
}

export function Label({ name, htmlFor }: LabelProps) {
   return (
      <label 
         className="text-[13px] font-medium"
         htmlFor={htmlFor}
      >
         {name}
      </label>
   )
}