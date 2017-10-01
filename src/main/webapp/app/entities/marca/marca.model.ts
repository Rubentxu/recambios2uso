import { BaseEntity } from './../../shared';

export class Marca implements BaseEntity {
    constructor(
        public id?: string,
        public nombre?: string,
        public modelo?: string,
        public tipoMotor?: string,
        public descripcion?: string,
    ) {
    }
}
