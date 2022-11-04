export interface Activity {
   id: number;
   title: string;
   description: string;
   priorityTag: {
      name: string;
   },
   phase: string;
   endDate: string;
   workspaceId: number;
}