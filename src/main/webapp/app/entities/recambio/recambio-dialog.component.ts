import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Recambio } from './recambio.model';
import { RecambioPopupService } from './recambio-popup.service';
import { RecambioService } from './recambio.service';

@Component({
    selector: 'jhi-recambio-dialog',
    templateUrl: './recambio-dialog.component.html'
})
export class RecambioDialogComponent implements OnInit {

    recambio: Recambio;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private recambioService: RecambioService,
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
        if (this.recambio.id !== undefined) {
            this.subscribeToSaveResponse(
                this.recambioService.update(this.recambio));
        } else {
            this.subscribeToSaveResponse(
                this.recambioService.create(this.recambio));
        }
    }

    private subscribeToSaveResponse(result: Observable<Recambio>) {
        result.subscribe((res: Recambio) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Recambio) {
        this.eventManager.broadcast({ name: 'recambioListModification', content: 'OK'});
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
    selector: 'jhi-recambio-popup',
    template: ''
})
export class RecambioPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private recambioPopupService: RecambioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.recambioPopupService
                    .open(RecambioDialogComponent as Component, params['id']);
            } else {
                this.recambioPopupService
                    .open(RecambioDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
