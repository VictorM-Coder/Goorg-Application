import classNames from "classnames";

interface TagPhaseProps {
   name: string | undefined;
   size?: 'xs' | 'sm' | 'md';
}

export function TagPhase({ name, size }: TagPhaseProps) {
   return (
      <span className={classNames(`py-0.5 px-3 rounded-full font-medium text-${size}`, {
         'bg-gray-200 text-gray-600': name === 'TO_DO',
         'bg-orange-200 text-orange-600': name === 'DOING',
         'bg-green-200 text-green-500': name  === 'DONE',
      })}>
         {name?.toLowerCase()}
      </span>
   )
}