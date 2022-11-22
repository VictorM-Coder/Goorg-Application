import { LinearProgress } from "@mui/material";

interface ProgressBarProps {
   valueCurrent: number;
   valueBuffer: number;
}

export function ProgressBar({ valueCurrent, valueBuffer }: ProgressBarProps) {
   return (
      <>
        <LinearProgress 
            value={valueCurrent}
            variant="buffer"
            valueBuffer={valueBuffer}
            className="rounded-full py-1"
         />
      </>
   )
}