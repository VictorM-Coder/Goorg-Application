export interface Activity {
   id: number;
   name: string;
   description: string;
   priority: string;
   status: string;
   workspaceName: string;
   idWorkspace?: number;
   date: Date;
   nameVisible?: boolean;
   minWidth?: string;
}