import { ModalProps } from "../global/ModalProps";

export interface ActivityModalProps extends ModalProps {
   idActivity?: number;
   isSelectWorkspace?: boolean;
   phase?: string;
}