import { darken } from "polished";


interface TagPriorityProps {
   name: string | undefined;
   size?: 'xs' | 'sm' | 'md';
   color?: string;
}

export function TagPriority({ name, size, color }: TagPriorityProps) {
   return (
      <span 
         style={{ backgroundColor: `#${color}`, color: darken(0.4, `#${color}`) }}
         className={`py-0.5 px-3 rounded-full font-medium text-${size}`}>
         {name}
      </span> 
   )
}