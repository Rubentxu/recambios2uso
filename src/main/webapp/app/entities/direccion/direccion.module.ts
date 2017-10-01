import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Recambios2UsoSharedModule } from '../../shared';
import {
    DireccionService,
    DireccionPopupService,
    DireccionComponent,
    DireccionDetailComponent,
    DireccionDialogComponent,
    DireccionPopupComponent,
    DireccionDeletePopupComponent,
    DireccionDeleteDialogComponent,
    direccionRoute,
    direccionPopupRoute,
    DireccionResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...direccionRoute,
    ...direccionPopupRoute,
];

@NgModule({
    imports: [
        Recambios2UsoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DireccionComponent,
        DireccionDetailComponent,
        DireccionDialogComponent,
        DireccionDeleteDialogComponent,
        DireccionPopupComponent,
        DireccionDeletePopupComponent,
    ],
    entryComponents: [
        DireccionComponent,
        DireccionDialogComponent,
        DireccionPopupComponent,
        DireccionDeleteDialogComponent,
        DireccionDeletePopupComponent,
    ],
    providers: [
        DireccionService,
        DireccionPopupService,
        DireccionResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Recambios2UsoDireccionModule {}
