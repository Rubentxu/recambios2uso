import { BaseEntity } from './../../shared';

export class Direccion implements BaseEntity {
    constructor(
        public id?: string,
        public calle?: string,
        public codigoPostal?: number,
        public ciudad?: string,
        public provincia?: string,
        public numero?: number,
    ) {
    }
}
