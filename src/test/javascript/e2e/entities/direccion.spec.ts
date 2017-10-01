import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Direccion e2e test', () => {

    let navBarPage: NavBarPage;
    let direccionDialogPage: DireccionDialogPage;
    let direccionComponentsPage: DireccionComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Direccions', () => {
        navBarPage.goToEntity('direccion');
        direccionComponentsPage = new DireccionComponentsPage();
        expect(direccionComponentsPage.getTitle()).toMatch(/recambios2UsoApp.direccion.home.title/);

    });

    it('should load create Direccion dialog', () => {
        direccionComponentsPage.clickOnCreateButton();
        direccionDialogPage = new DireccionDialogPage();
        expect(direccionDialogPage.getModalTitle()).toMatch(/recambios2UsoApp.direccion.home.createOrEditLabel/);
        direccionDialogPage.close();
    });

    it('should create and save Direccions', () => {
        direccionComponentsPage.clickOnCreateButton();
        direccionDialogPage.setCalleInput('calle');
        expect(direccionDialogPage.getCalleInput()).toMatch('calle');
        direccionDialogPage.setCodigoPostalInput('5');
        expect(direccionDialogPage.getCodigoPostalInput()).toMatch('5');
        direccionDialogPage.setCiudadInput('ciudad');
        expect(direccionDialogPage.getCiudadInput()).toMatch('ciudad');
        direccionDialogPage.setProvinciaInput('provincia');
        expect(direccionDialogPage.getProvinciaInput()).toMatch('provincia');
        direccionDialogPage.setNumeroInput('5');
        expect(direccionDialogPage.getNumeroInput()).toMatch('5');
        direccionDialogPage.save();
        expect(direccionDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class DireccionComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-direccion div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DireccionDialogPage {
    modalTitle = element(by.css('h4#myDireccionLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    calleInput = element(by.css('input#field_calle'));
    codigoPostalInput = element(by.css('input#field_codigoPostal'));
    ciudadInput = element(by.css('input#field_ciudad'));
    provinciaInput = element(by.css('input#field_provincia'));
    numeroInput = element(by.css('input#field_numero'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setCalleInput = function (calle) {
        this.calleInput.sendKeys(calle);
    }

    getCalleInput = function () {
        return this.calleInput.getAttribute('value');
    }

    setCodigoPostalInput = function (codigoPostal) {
        this.codigoPostalInput.sendKeys(codigoPostal);
    }

    getCodigoPostalInput = function () {
        return this.codigoPostalInput.getAttribute('value');
    }

    setCiudadInput = function (ciudad) {
        this.ciudadInput.sendKeys(ciudad);
    }

    getCiudadInput = function () {
        return this.ciudadInput.getAttribute('value');
    }

    setProvinciaInput = function (provincia) {
        this.provinciaInput.sendKeys(provincia);
    }

    getProvinciaInput = function () {
        return this.provinciaInput.getAttribute('value');
    }

    setNumeroInput = function (numero) {
        this.numeroInput.sendKeys(numero);
    }

    getNumeroInput = function () {
        return this.numeroInput.getAttribute('value');
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
