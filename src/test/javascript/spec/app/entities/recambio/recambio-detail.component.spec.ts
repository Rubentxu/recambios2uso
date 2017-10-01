/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Recambios2UsoTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { RecambioDetailComponent } from '../../../../../../main/webapp/app/entities/recambio/recambio-detail.component';
import { RecambioService } from '../../../../../../main/webapp/app/entities/recambio/recambio.service';
import { Recambio } from '../../../../../../main/webapp/app/entities/recambio/recambio.model';

describe('Component Tests', () => {

    describe('Recambio Management Detail Component', () => {
        let comp: RecambioDetailComponent;
        let fixture: ComponentFixture<RecambioDetailComponent>;
        let service: RecambioService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Recambios2UsoTestModule],
                declarations: [RecambioDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    RecambioService,
                    JhiEventManager
                ]
            }).overrideTemplate(RecambioDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(RecambioDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecambioService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Recambio('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.recambio).toEqual(jasmine.objectContaining({id: 'aaa'}));
            });
        });
    });

});
