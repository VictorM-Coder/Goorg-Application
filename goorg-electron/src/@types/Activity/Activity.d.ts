export interface Activity {
   id: number;
   name: string;
   description: string;
   priorityTag: {
      name: string;
   },
   phase: string;
   endDate: string;
   workspace: {
      id: number;
      name: string;
   },
}