export interface Activity {
   id: number;
   title: string;
   description: string;
   tasks: {
      id: number;
      title: string;
      status: boolean;
   }[]
   priorityTag: {
      name: string;
      color: string;
   },
   phase: string;
   endDate: string;
   startDate: string;
   workspaceId: number;
}