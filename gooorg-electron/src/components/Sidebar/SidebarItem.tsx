import { Link } from "react-router-dom";

interface SidebarItemProps {
   name: string;
   minemized: boolean;
   children: React.ReactNode
   link: string
}

export function SidebarItem({ children, name, minemized, link }: SidebarItemProps) {
   const urlCurrent = window.location.href.split("/");
   const url = urlCurrent[urlCurrent.length - 1];

   return (
      <li className={`p-3 ${(name == url) ? 'bg-gray-400 rounded-full text-gray-600' : '' }`} >
         <Link 
            to={link} 
            className={`text-sm flex items-center overflow-hidden transition-all ${(minemized) ? 'gap-0 ' : 'gap-2'}`}
         >
            { children }
            <span className={`${(minemized) ? 'w-0' : ''} transition-all`}>
               {name}
            </span>
         </Link>
      </li>
   )
}