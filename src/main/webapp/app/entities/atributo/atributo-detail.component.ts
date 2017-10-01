import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Atributo } from './atributo.model';
import { AtributoService } from './atributo.service';

@Component({
    selector: 'jhi-atributo-detail',
    templateUrl: './atributo-detail.component.html'
})
export class AtributoDetailComponent implements OnInit, OnDestroy {

    atributo: Atributo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private atributoService: AtributoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAtributos();
    }

    load(id) {
        this.atributoService.find(id).subscribe((atributo) => {
            this.atributo = atributo;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAtributos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'atributoListModification',
            (response) => this.load(this.atributo.id)
        );
    }
}
