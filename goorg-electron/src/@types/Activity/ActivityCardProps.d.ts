import { Activity } from "./Activity";

export interface ActivityCardProps extends Activity {
   nameVisible?: boolean | true;
   minWidth?: boolean;
   link?: string;
}