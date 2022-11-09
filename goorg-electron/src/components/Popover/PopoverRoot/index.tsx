import './style.css';
import Popover from '@mui/material/Popover';

export interface PopoverSimpleProps {
   id: 'simple-popover' | undefined;
   isOpen: boolean;
   elementAnchor: HTMLButtonElement | null;
   children: React.ReactNode;
   handleClose: () => void;
}

export function PopoverRoot({
   id, 
   isOpen, 
   elementAnchor, 
   handleClose,
   children
}: PopoverSimpleProps) {

   return (
      <Popover id={id} open={isOpen} anchorEl={elementAnchor} onClose={handleClose}
      anchorOrigin={{ vertical: 'bottom', horizontal: 'left'}}
      >  
         <div className="flex flex-col items-start gap-4 p-4">
            { children }
         </div>
      </Popover>
   )
}