import * as yup from 'yup';

export const ActivityTagSchemaYup = yup.object({
   name: yup.string().required('O nome é obrigatório.'),
}).required();
