import * as yup from 'yup';

export const ActivitySchemaYup = yup.object({
   workspace: yup.string().required('O workspace é obrigatório'),
   title: yup.string().required('O nome é obrigatório.'),
   priority: yup.string().required('A prioridade é obrigatória.')
}).required();