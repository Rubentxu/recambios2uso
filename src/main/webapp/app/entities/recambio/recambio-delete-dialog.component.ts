import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Recambio } from './recambio.model';
import { RecambioPopupService } from './recambio-popup.service';
import { RecambioService } from './recambio.service';

@Component({
    selector: 'jhi-recambio-delete-dialog',
    templateUrl: './recambio-delete-dialog.component.html'
})
export class RecambioDeleteDialogComponent {

    recambio: Recambio;

    constructor(
        private recambioService: RecambioService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.recambioService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'recambioListModification',
                content: 'Deleted an recambio'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-recambio-delete-popup',
    template: ''
})
export class RecambioDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private recambioPopupService: RecambioPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.recambioPopupService
                .open(RecambioDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
