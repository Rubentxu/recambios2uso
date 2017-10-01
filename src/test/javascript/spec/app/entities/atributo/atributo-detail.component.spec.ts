/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { Recambios2UsoTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AtributoDetailComponent } from '../../../../../../main/webapp/app/entities/atributo/atributo-detail.component';
import { AtributoService } from '../../../../../../main/webapp/app/entities/atributo/atributo.service';
import { Atributo } from '../../../../../../main/webapp/app/entities/atributo/atributo.model';

describe('Component Tests', () => {

    describe('Atributo Management Detail Component', () => {
        let comp: AtributoDetailComponent;
        let fixture: ComponentFixture<AtributoDetailComponent>;
        let service: AtributoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [Recambios2UsoTestModule],
                declarations: [AtributoDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AtributoService,
                    JhiEventManager
                ]
            }).overrideTemplate(AtributoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AtributoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AtributoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Atributo('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.atributo).toEqual(jasmine.objectContaining({id: 'aaa'}));
            });
        });
    });

});
