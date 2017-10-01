import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Recambio e2e test', () => {

    let navBarPage: NavBarPage;
    let recambioDialogPage: RecambioDialogPage;
    let recambioComponentsPage: RecambioComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Recambios', () => {
        navBarPage.goToEntity('recambio');
        recambioComponentsPage = new RecambioComponentsPage();
        expect(recambioComponentsPage.getTitle()).toMatch(/recambios2UsoApp.recambio.home.title/);

    });

    it('should load create Recambio dialog', () => {
        recambioComponentsPage.clickOnCreateButton();
        recambioDialogPage = new RecambioDialogPage();
        expect(recambioDialogPage.getModalTitle()).toMatch(/recambios2UsoApp.recambio.home.createOrEditLabel/);
        recambioDialogPage.close();
    });

    it('should create and save Recambios', () => {
        recambioComponentsPage.clickOnCreateButton();
        recambioDialogPage.setReferenciaInput('referencia');
        expect(recambioDialogPage.getReferenciaInput()).toMatch('referencia');
        recambioDialogPage.setNombreInput('nombre');
        expect(recambioDialogPage.getNombreInput()).toMatch('nombre');
        recambioDialogPage.tipoSelectLastOption();
        recambioDialogPage.setDescripcionInput('descripcion');
        expect(recambioDialogPage.getDescripcionInput()).toMatch('descripcion');
        recambioDialogPage.setPrecioInput('5');
        expect(recambioDialogPage.getPrecioInput()).toMatch('5');
        recambioDialogPage.setReclamoPublicitarioInput('reclamoPublicitario');
        expect(recambioDialogPage.getReclamoPublicitarioInput()).toMatch('reclamoPublicitario');
        recambioDialogPage.getDisponibilidadInput().isSelected().then(function (selected) {
            if (selected) {
                recambioDialogPage.getDisponibilidadInput().click();
                expect(recambioDialogPage.getDisponibilidadInput().isSelected()).toBeFalsy();
            } else {
                recambioDialogPage.getDisponibilidadInput().click();
                expect(recambioDialogPage.getDisponibilidadInput().isSelected()).toBeTruthy();
            }
        });
        recambioDialogPage.getExposicionInput().isSelected().then(function (selected) {
            if (selected) {
                recambioDialogPage.getExposicionInput().click();
                expect(recambioDialogPage.getExposicionInput().isSelected()).toBeFalsy();
            } else {
                recambioDialogPage.getExposicionInput().click();
                expect(recambioDialogPage.getExposicionInput().isSelected()).toBeTruthy();
            }
        });
        recambioDialogPage.save();
        expect(recambioDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class RecambioComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-recambio div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class RecambioDialogPage {
    modalTitle = element(by.css('h4#myRecambioLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    referenciaInput = element(by.css('input#field_referencia'));
    nombreInput = element(by.css('input#field_nombre'));
    tipoSelect = element(by.css('select#field_tipo'));
    descripcionInput = element(by.css('input#field_descripcion'));
    precioInput = element(by.css('input#field_precio'));
    reclamoPublicitarioInput = element(by.css('input#field_reclamoPublicitario'));
    disponibilidadInput = element(by.css('input#field_disponibilidad'));
    exposicionInput = element(by.css('input#field_exposicion'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setReferenciaInput = function (referencia) {
        this.referenciaInput.sendKeys(referencia);
    }

    getReferenciaInput = function () {
        return this.referenciaInput.getAttribute('value');
    }

    setNombreInput = function (nombre) {
        this.nombreInput.sendKeys(nombre);
    }

    getNombreInput = function () {
        return this.nombreInput.getAttribute('value');
    }

    setTipoSelect = function (tipo) {
        this.tipoSelect.sendKeys(tipo);
    }

    getTipoSelect = function () {
        return this.tipoSelect.element(by.css('option:checked')).getText();
    }

    tipoSelectLastOption = function () {
        this.tipoSelect.all(by.tagName('option')).last().click();
    }
    setDescripcionInput = function (descripcion) {
        this.descripcionInput.sendKeys(descripcion);
    }

    getDescripcionInput = function () {
        return this.descripcionInput.getAttribute('value');
    }

    setPrecioInput = function (precio) {
        this.precioInput.sendKeys(precio);
    }

    getPrecioInput = function () {
        return this.precioInput.getAttribute('value');
    }

    setReclamoPublicitarioInput = function (reclamoPublicitario) {
        this.reclamoPublicitarioInput.sendKeys(reclamoPublicitario);
    }

    getReclamoPublicitarioInput = function () {
        return this.reclamoPublicitarioInput.getAttribute('value');
    }

    getDisponibilidadInput = function () {
        return this.disponibilidadInput;
    }
    getExposicionInput = function () {
        return this.exposicionInput;
    }
    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
