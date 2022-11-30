import { ModalProps } from "../global/ModalProps";

export interface ActivityModalProps extends ModalProps {
   idActivity?: number;
   isSelectWorkspace?: boolean;
   phase?: 'IN_PROGRESS' | 'TO_DOING';
}