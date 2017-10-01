import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { DesguaceComponent } from './desguace.component';
import { DesguaceDetailComponent } from './desguace-detail.component';
import { DesguacePopupComponent } from './desguace-dialog.component';
import { DesguaceDeletePopupComponent } from './desguace-delete-dialog.component';

@Injectable()
export class DesguaceResolvePagingParams implements Resolve<any> {

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

export const desguaceRoute: Routes = [
    {
        path: 'desguace',
        component: DesguaceComponent,
        resolve: {
            'pagingParams': DesguaceResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.desguace.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'desguace/:id',
        component: DesguaceDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.desguace.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const desguacePopupRoute: Routes = [
    {
        path: 'desguace-new',
        component: DesguacePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.desguace.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'desguace/:id/edit',
        component: DesguacePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.desguace.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'desguace/:id/delete',
        component: DesguaceDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.desguace.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
