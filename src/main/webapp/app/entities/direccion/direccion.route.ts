import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DireccionComponent } from './direccion.component';
import { DireccionDetailComponent } from './direccion-detail.component';
import { DireccionPopupComponent } from './direccion-dialog.component';
import { DireccionDeletePopupComponent } from './direccion-delete-dialog.component';

@Injectable()
export class DireccionResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const direccionRoute: Routes = [
    {
        path: 'direccion',
        component: DireccionComponent,
        resolve: {
            'pagingParams': DireccionResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.direccion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'direccion/:id',
        component: DireccionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.direccion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const direccionPopupRoute: Routes = [
    {
        path: 'direccion-new',
        component: DireccionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.direccion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'direccion/:id/edit',
        component: DireccionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.direccion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'direccion/:id/delete',
        component: DireccionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.direccion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
