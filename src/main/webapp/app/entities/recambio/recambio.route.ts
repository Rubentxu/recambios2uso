import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { RecambioComponent } from './recambio.component';
import { RecambioDetailComponent } from './recambio-detail.component';
import { RecambioPopupComponent } from './recambio-dialog.component';
import { RecambioDeletePopupComponent } from './recambio-delete-dialog.component';

@Injectable()
export class RecambioResolvePagingParams implements Resolve<any> {

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

export const recambioRoute: Routes = [
    {
        path: 'recambio',
        component: RecambioComponent,
        resolve: {
            'pagingParams': RecambioResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.recambio.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'recambio/:id',
        component: RecambioDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.recambio.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const recambioPopupRoute: Routes = [
    {
        path: 'recambio-new',
        component: RecambioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.recambio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'recambio/:id/edit',
        component: RecambioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.recambio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'recambio/:id/delete',
        component: RecambioDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.recambio.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
