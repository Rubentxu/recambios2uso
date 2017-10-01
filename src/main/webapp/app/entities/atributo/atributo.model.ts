import { BaseEntity } from './../../shared';

export class Atributo implements BaseEntity {
    constructor(
        public id?: string,
        public nombre?: string,
        public valor?: string,
    ) {
    }
}
