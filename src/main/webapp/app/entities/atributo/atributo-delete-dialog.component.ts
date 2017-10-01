import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Atributo } from './atributo.model';
import { AtributoPopupService } from './atributo-popup.service';
import { AtributoService } from './atributo.service';

@Component({
    selector: 'jhi-atributo-delete-dialog',
    templateUrl: './atributo-delete-dialog.component.html'
})
export class AtributoDeleteDialogComponent {

    atributo: Atributo;

    constructor(
        private atributoService: AtributoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.atributoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'atributoListModification',
                content: 'Deleted an atributo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-atributo-delete-popup',
    template: ''
})
export class AtributoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private atributoPopupService: AtributoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.atributoPopupService
                .open(AtributoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
