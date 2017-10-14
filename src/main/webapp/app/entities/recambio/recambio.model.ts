import { BaseEntity } from './../../shared';

export const enum Tipo {
    'FRENADO',
    'FILTROS',
    'REFRIGERACION',
    'SUSPENSION',
    'DIRECCION',
    'EMBRAGUE',
    'TRANSMISION',
    'DISTRIBUCION',
    'ESCAPE',
    'OTRO'
}

export class Recambio implements BaseEntity {
    constructor(
        public id?: string,
        public referencia?: string,
        public nombre?: string,
        public tipo?: Tipo,
        public descripcion?: string,
        public precio?: number,
        public reclamoPublicitario?: string,
        public disponibilidad?: boolean,
        public exposicion?: boolean,
    ) {
        this.disponibilidad = false;
        this.exposicion = false;
    }
}
