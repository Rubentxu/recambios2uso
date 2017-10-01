import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AtributoComponent } from './atributo.component';
import { AtributoDetailComponent } from './atributo-detail.component';
import { AtributoPopupComponent } from './atributo-dialog.component';
import { AtributoDeletePopupComponent } from './atributo-delete-dialog.component';

@Injectable()
export class AtributoResolvePagingParams implements Resolve<any> {

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

export const atributoRoute: Routes = [
    {
        path: 'atributo',
        component: AtributoComponent,
        resolve: {
            'pagingParams': AtributoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.atributo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'atributo/:id',
        component: AtributoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.atributo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const atributoPopupRoute: Routes = [
    {
        path: 'atributo-new',
        component: AtributoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.atributo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'atributo/:id/edit',
        component: AtributoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.atributo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'atributo/:id/delete',
        component: AtributoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'recambios2UsoApp.atributo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
