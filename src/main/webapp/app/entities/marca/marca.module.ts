import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Recambios2UsoSharedModule } from '../../shared';
import {
    MarcaService,
    MarcaPopupService,
    MarcaComponent,
    MarcaDetailComponent,
    MarcaDialogComponent,
    MarcaPopupComponent,
    MarcaDeletePopupComponent,
    MarcaDeleteDialogComponent,
    marcaRoute,
    marcaPopupRoute,
    MarcaResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...marcaRoute,
    ...marcaPopupRoute,
];

@NgModule({
    imports: [
        Recambios2UsoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        MarcaComponent,
        MarcaDetailComponent,
        MarcaDialogComponent,
        MarcaDeleteDialogComponent,
        MarcaPopupComponent,
        MarcaDeletePopupComponent,
    ],
    entryComponents: [
        MarcaComponent,
        MarcaDialogComponent,
        MarcaPopupComponent,
        MarcaDeleteDialogComponent,
        MarcaDeletePopupComponent,
    ],
    providers: [
        MarcaService,
        MarcaPopupService,
        MarcaResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Recambios2UsoMarcaModule {}
