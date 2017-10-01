/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Recambios2UsoTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { DesguaceDetailComponent } from '../../../../../../main/webapp/app/entities/desguace/desguace-detail.component';
import { DesguaceService } from '../../../../../../main/webapp/app/entities/desguace/desguace.service';
import { Desguace } from '../../../../../../main/webapp/app/entities/desguace/desguace.model';

describe('Component Tests', () => {

    describe('Desguace Management Detail Component', () => {
        let comp: DesguaceDetailComponent;
        let fixture: ComponentFixture<DesguaceDetailComponent>;
        let service: DesguaceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Recambios2UsoTestModule],
                declarations: [DesguaceDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    DesguaceService,
                    JhiEventManager
                ]
            }).overrideTemplate(DesguaceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DesguaceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DesguaceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Desguace('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.desguace).toEqual(jasmine.objectContaining({id: 'aaa'}));
            });
        });
    });

});
