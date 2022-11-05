import classNames from "classnames";

interface TagPhaseProps {
   name: string | undefined;
   size?: 'xs' | 'sm' | 'md';
}

export function TagPhase({ name, size }: TagPhaseProps) {
   return (
      <span className={classNames(`py-0.5 px-3 rounded-full font-medium text-${size}`, {
         'bg-blue-200 text-blue-600': name === 'TO_DO',
         'bg-orange-200 text-orange-600': name === 'IN_PROGRESS',
         'bg-green-200 text-green-600': name  === 'DONE',
      })}>
         {name?.toLowerCase()}
      </span>
   )
}