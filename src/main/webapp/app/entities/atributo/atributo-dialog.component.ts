import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Atributo } from './atributo.model';
import { AtributoPopupService } from './atributo-popup.service';
import { AtributoService } from './atributo.service';

@Component({
    selector: 'jhi-atributo-dialog',
    templateUrl: './atributo-dialog.component.html'
})
export class AtributoDialogComponent implements OnInit {

    atributo: Atributo;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private atributoService: AtributoService,
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
        if (this.atributo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.atributoService.update(this.atributo));
        } else {
            this.subscribeToSaveResponse(
                this.atributoService.create(this.atributo));
        }
    }

    private subscribeToSaveResponse(result: Observable<Atributo>) {
        result.subscribe((res: Atributo) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: Atributo) {
        this.eventManager.broadcast({ name: 'atributoListModification', content: 'OK'});
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
    selector: 'jhi-atributo-popup',
    template: ''
})
export class AtributoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private atributoPopupService: AtributoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.atributoPopupService
                    .open(AtributoDialogComponent as Component, params['id']);
            } else {
                this.atributoPopupService
                    .open(AtributoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
