import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Recambios2UsoAtributoModule } from './atributo/atributo.module';
import { Recambios2UsoDesguaceModule } from './desguace/desguace.module';
import { Recambios2UsoDireccionModule } from './direccion/direccion.module';
import { Recambios2UsoMarcaModule } from './marca/marca.module';
import { Recambios2UsoRecambioModule } from './recambio/recambio.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        Recambios2UsoAtributoModule,
        Recambios2UsoDesguaceModule,
        Recambios2UsoDireccionModule,
        Recambios2UsoMarcaModule,
        Recambios2UsoRecambioModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Recambios2UsoEntityModule {}
