import { Calendar } from "phosphor-react";

export function Header() {
   const weekDays = ['Domingo', 'Segunda Feira', 'Terça Feira', 'Quarta Feira', 'Quinta Feira', 'Sexta Feira', 'Sabado']
   
   return (
      <header className="bg-white p-5 fixed left-0 top-0 right-0 z-[1] shadow-sm">
         <span className="text-gray-300 text-sm justify-end flex items-center gap-1">
            <Calendar size={20} />
            <span>{ new Intl.DateTimeFormat('pt-BR').format(new Date()) } -</span>   
            <span>{ weekDays[new Date().getDay()] }</span>
         </span>
      </header>
   )
}
