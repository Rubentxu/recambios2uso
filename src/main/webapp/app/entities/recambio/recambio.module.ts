import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Recambios2UsoSharedModule } from '../../shared';
import {
    RecambioService,
    RecambioPopupService,
    RecambioComponent,
    RecambioDetailComponent,
    RecambioDialogComponent,
    RecambioPopupComponent,
    RecambioDeletePopupComponent,
    RecambioDeleteDialogComponent,
    recambioRoute,
    recambioPopupRoute,
    RecambioResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...recambioRoute,
    ...recambioPopupRoute,
];

@NgModule({
    imports: [
        Recambios2UsoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        RecambioComponent,
        RecambioDetailComponent,
        RecambioDialogComponent,
        RecambioDeleteDialogComponent,
        RecambioPopupComponent,
        RecambioDeletePopupComponent,
    ],
    entryComponents: [
        RecambioComponent,
        RecambioDialogComponent,
        RecambioPopupComponent,
        RecambioDeleteDialogComponent,
        RecambioDeletePopupComponent,
    ],
    providers: [
        RecambioService,
        RecambioPopupService,
        RecambioResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Recambios2UsoRecambioModule {}
