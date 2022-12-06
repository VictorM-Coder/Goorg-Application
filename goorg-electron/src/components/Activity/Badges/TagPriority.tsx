import classNames from "classnames";

interface TagPriorityProps {
   name: string | undefined;
   size?: 'xs' | 'sm' | 'md';
}

export function TagPriority({ name, size }: TagPriorityProps) {
   return (
      <span className={classNames(`py-0.5 px-3 rounded-full font-medium text-${size}`, {
         'bg-red-200 text-red-500': name === 'Urgente',
         'bg-yellow-200 text-yellow-500': name === 'Relevante',
         'bg-blue-200 text-blue-600': name === 'Importante',
      })}>
         {name}
      </span> 
   )
}