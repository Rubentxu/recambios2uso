import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Recambios2UsoSharedModule } from '../../shared';
import {
    AtributoService,
    AtributoPopupService,
    AtributoComponent,
    AtributoDetailComponent,
    AtributoDialogComponent,
    AtributoPopupComponent,
    AtributoDeletePopupComponent,
    AtributoDeleteDialogComponent,
    atributoRoute,
    atributoPopupRoute,
    AtributoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...atributoRoute,
    ...atributoPopupRoute,
];

@NgModule({
    imports: [
        Recambios2UsoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AtributoComponent,
        AtributoDetailComponent,
        AtributoDialogComponent,
        AtributoDeleteDialogComponent,
        AtributoPopupComponent,
        AtributoDeletePopupComponent,
    ],
    entryComponents: [
        AtributoComponent,
        AtributoDialogComponent,
        AtributoPopupComponent,
        AtributoDeleteDialogComponent,
        AtributoDeletePopupComponent,
    ],
    providers: [
        AtributoService,
        AtributoPopupService,
        AtributoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Recambios2UsoAtributoModule {}
