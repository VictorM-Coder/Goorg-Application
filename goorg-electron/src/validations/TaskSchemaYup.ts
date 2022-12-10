import * as yup from 'yup';

export const TaskSchemaYup = yup.object({
   title: yup.string().required('O nome é obrigatório.'),
}).required();
