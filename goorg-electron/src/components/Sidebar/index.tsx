import classNames from "classnames";
import { CaretLeft, House, ShareNetwork, Stack } from "phosphor-react";
import { useContext } from "react";
import { SidebarContex } from "../../contexts/SidebarContext";
import { LogoDefault, LogoMinemized } from "../Logo";
import { SidebarItem } from "./SidebarItem";

export function Sidebar() {
   const { handleSidebarMinimezed, sidebarMinimizedIsActive } = useContext(SidebarContex)
   
   return (
      <div 
         className={`bg-white relative w-[270px] min-h-full flex flex-col justify-between px-6 py-5 gap-12 
         transition-all z-10 border-r border-r-gray-100 mdmax:-left-full mdmax:absolute 
         ${(sidebarMinimizedIsActive) ? 'w-24 content-center' : ''}`}
      >

         <div className='flex flex-col gap-12 fixed z-50'>
            {(!sidebarMinimizedIsActive) ? <LogoDefault /> : <LogoMinemized />}

            <ul className={`text-gray-300 font-medium flex flex-col gap-3 ${(sidebarMinimizedIsActive) ? 'items-start' : ''}`}>
               <SidebarItem 
                  name="inicio" 
                  link="/inicio" 
                  minemized={sidebarMinimizedIsActive}
               > 
                  <House size={20} />
               </SidebarItem>

               <SidebarItem 
                  name="atividades" 
                  link="/atividades" 
                  minemized={sidebarMinimizedIsActive} 
               >
                  <Stack size={20} />
               </SidebarItem>

               <SidebarItem 
                  name="workspaces" 
                  link="/workspaces" 
                  minemized={sidebarMinimizedIsActive}
               >
                  <ShareNetwork size={20} />
               </SidebarItem>
            </ul> 
         </div>
         
         <button
            className={classNames('p-2 rounded-full mdmax:hidden top-14 fixed shadow-md transition-all', {
               'bg-blue-dark text-white left-64' : !sidebarMinimizedIsActive,
               'bg-white text-blue-dark left-20' : sidebarMinimizedIsActive,
            })}
            onClick={handleSidebarMinimezed}
         >
            <CaretLeft size={20} className={`${(sidebarMinimizedIsActive) ? 'rotate-180' : ''}`}/>
         </button>
      </div>
   )
}