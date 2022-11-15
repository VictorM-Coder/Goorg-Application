export interface ActivityEdit {
   title: string;
   description: string;
   priorityTag: {
      id: number;
   },
   workspace: {
      id: Number;
   }
   endDate: string;
}