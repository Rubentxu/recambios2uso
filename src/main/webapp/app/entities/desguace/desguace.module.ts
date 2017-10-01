import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Recambios2UsoSharedModule } from '../../shared';
import {
    DesguaceService,
    DesguacePopupService,
    DesguaceComponent,
    DesguaceDetailComponent,
    DesguaceDialogComponent,
    DesguacePopupComponent,
    DesguaceDeletePopupComponent,
    DesguaceDeleteDialogComponent,
    desguaceRoute,
    desguacePopupRoute,
    DesguaceResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...desguaceRoute,
    ...desguacePopupRoute,
];

@NgModule({
    imports: [
        Recambios2UsoSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        DesguaceComponent,
        DesguaceDetailComponent,
        DesguaceDialogComponent,
        DesguaceDeleteDialogComponent,
        DesguacePopupComponent,
        DesguaceDeletePopupComponent,
    ],
    entryComponents: [
        DesguaceComponent,
        DesguaceDialogComponent,
        DesguacePopupComponent,
        DesguaceDeleteDialogComponent,
        DesguaceDeletePopupComponent,
    ],
    providers: [
        DesguaceService,
        DesguacePopupService,
        DesguaceResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Recambios2UsoDesguaceModule {}
