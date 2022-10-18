interface LogoMimemizedProps {
  size?: number;
}

export function LogoMinemized({ size = 0 }: LogoMimemizedProps ){
   return (
     <svg
       xmlns="http://www.w3.org/2000/svg"
       width={(size) ? size : '51'}
       height={(size) ? size : '46'}
       fill="none"
       viewBox="0 0 59 54"
     >
       <path
         fill="#2F738E"
         d="M50.98 22.432c-.576-1.056-1.368-1.86-2.376-2.412-1.008-.552-2.184-.828-3.528-.828-1.488 0-2.808.336-3.96 1.008-1.152.672-2.052 1.632-2.7 2.88-.648 1.248-.972 2.688-.972 4.32 0 1.68.324 3.144.972 4.392.672 1.248 1.596 2.208 2.772 2.88 1.176.672 2.544 1.008 4.104 1.008 1.92 0 3.492-.504 4.716-1.512 1.224-1.032 2.028-2.46 2.412-4.284h-8.64v-3.852h13.608v4.392c-.336 1.752-1.056 3.372-2.16 4.86-1.104 1.488-2.532 2.688-4.284 3.6-1.728.888-3.672 1.332-5.832 1.332-2.424 0-4.62-.54-6.588-1.62a12.214 12.214 0 01-4.608-4.572c-1.104-1.944-1.656-4.152-1.656-6.624 0-2.472.552-4.68 1.656-6.624 1.128-1.968 2.664-3.492 4.608-4.572 1.968-1.104 4.152-1.656 6.552-1.656 2.832 0 5.292.696 7.38 2.088 2.088 1.368 3.528 3.3 4.32 5.796H50.98z"
       ></path>
       <path stroke="#88B0BF" strokeWidth="1.3" d="M18 24.35L28 24.35"></path>
       <path stroke="#88B0BF" strokeWidth="1.3" d="M0 28.35L28 28.35"></path>
       <path stroke="#88B0BF" strokeWidth="1.3" d="M7 32.35L28 32.35"></path>
     </svg>
   );
 }
 