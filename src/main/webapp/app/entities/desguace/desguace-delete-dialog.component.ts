import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Desguace } from './desguace.model';
import { DesguacePopupService } from './desguace-popup.service';
import { DesguaceService } from './desguace.service';

@Component({
    selector: 'jhi-desguace-delete-dialog',
    templateUrl: './desguace-delete-dialog.component.html'
})
export class DesguaceDeleteDialogComponent {

    desguace: Desguace;

    constructor(
        private desguaceService: DesguaceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.desguaceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'desguaceListModification',
                content: 'Deleted an desguace'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-desguace-delete-popup',
    template: ''
})
export class DesguaceDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private desguacePopupService: DesguacePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.desguacePopupService
                .open(DesguaceDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
