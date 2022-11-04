export interface ActivityEdit {
   title: string;
   description: string;
   priorityTag: {
      name: string;
   },
   endDate: Date;
}