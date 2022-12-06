import * as yup from 'yup';

export const WorkspaceSchemaYup = yup.object({
   name: yup.string().required("O nome é obrigatório."),
}).required();
