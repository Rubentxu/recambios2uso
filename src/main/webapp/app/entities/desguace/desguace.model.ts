import { BaseEntity } from './../../shared';

export class Desguace implements BaseEntity {
    constructor(
        public id?: string,
        public nombre?: string,
        public email?: string,
        public telefono?: string,
        public movil?: string,
    ) {
    }
}
