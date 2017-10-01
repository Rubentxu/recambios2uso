import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Desguace } from './desguace.model';
import { DesguacePopupService } from './desguace-popup.service';
import { DesguaceService } from './desguace.service';

@Component({
    selector: 'jhi-desguace-dialog',
    templateUrl: './desguace-dialog.component.html'
})
export class DesguaceDialogComponent implements OnInit {

    desguace: Desguace;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private desguaceService: DesguaceService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.desguace.id !== undefined) {
            this.subscribeToSaveResponse(
                this.desguaceService.update(this.desguace));
        } else {
            this.subscribeToSaveResponse(
                this.desguaceService.create(this.desguace));
        }
    }

    private subscribeToSaveResponse(result: Observable<Desguace>) {
        result.subscribe((res: Desguace) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Desguace) {
        this.eventManager.broadcast({ name: 'desguaceListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-desguace-popup',
    template: ''
})
export class DesguacePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private desguacePopupService: DesguacePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.desguacePopupService
                    .open(DesguaceDialogComponent as Component, params['id']);
            } else {
                this.desguacePopupService
                    .open(DesguaceDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
