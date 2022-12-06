interface FormControllProps {
   children: React.ReactNode;
}

export function FormControl({ children }: FormControllProps) {
   return (
      <div className="flex flex-col gap-1 text-gray-600">
         { children }
      </div>
   )
}