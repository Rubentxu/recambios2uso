import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { Desguace } from './desguace.model';
import { DesguaceService } from './desguace.service';

@Component({
    selector: 'jhi-desguace-detail',
    templateUrl: './desguace-detail.component.html'
})
export class DesguaceDetailComponent implements OnInit, OnDestroy {

    desguace: Desguace;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private desguaceService: DesguaceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDesguaces();
    }

    load(id) {
        this.desguaceService.find(id).subscribe((desguace) => {
            this.desguace = desguace;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDesguaces() {
        this.eventSubscriber = this.eventManager.subscribe(
            'desguaceListModification',
            (response) => this.load(this.desguace.id)
        );
    }
}
