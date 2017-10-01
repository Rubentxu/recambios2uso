import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Marca e2e test', () => {

    let navBarPage: NavBarPage;
    let marcaDialogPage: MarcaDialogPage;
    let marcaComponentsPage: MarcaComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Marcas', () => {
        navBarPage.goToEntity('marca');
        marcaComponentsPage = new MarcaComponentsPage();
        expect(marcaComponentsPage.getTitle()).toMatch(/recambios2UsoApp.marca.home.title/);

    });

    it('should load create Marca dialog', () => {
        marcaComponentsPage.clickOnCreateButton();
        marcaDialogPage = new MarcaDialogPage();
        expect(marcaDialogPage.getModalTitle()).toMatch(/recambios2UsoApp.marca.home.createOrEditLabel/);
        marcaDialogPage.close();
    });

    it('should create and save Marcas', () => {
        marcaComponentsPage.clickOnCreateButton();
        marcaDialogPage.setNombreInput('nombre');
        expect(marcaDialogPage.getNombreInput()).toMatch('nombre');
        marcaDialogPage.setModeloInput('modelo');
        expect(marcaDialogPage.getModeloInput()).toMatch('modelo');
        marcaDialogPage.setTipoMotorInput('tipoMotor');
        expect(marcaDialogPage.getTipoMotorInput()).toMatch('tipoMotor');
        marcaDialogPage.setDescripcionInput('descripcion');
        expect(marcaDialogPage.getDescripcionInput()).toMatch('descripcion');
        marcaDialogPage.save();
        expect(marcaDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class MarcaComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-marca div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class MarcaDialogPage {
    modalTitle = element(by.css('h4#myMarcaLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nombreInput = element(by.css('input#field_nombre'));
    modeloInput = element(by.css('input#field_modelo'));
    tipoMotorInput = element(by.css('input#field_tipoMotor'));
    descripcionInput = element(by.css('input#field_descripcion'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setNombreInput = function (nombre) {
        this.nombreInput.sendKeys(nombre);
    }

    getNombreInput = function () {
        return this.nombreInput.getAttribute('value');
    }

    setModeloInput = function (modelo) {
        this.modeloInput.sendKeys(modelo);
    }

    getModeloInput = function () {
        return this.modeloInput.getAttribute('value');
    }

    setTipoMotorInput = function (tipoMotor) {
        this.tipoMotorInput.sendKeys(tipoMotor);
    }

    getTipoMotorInput = function () {
        return this.tipoMotorInput.getAttribute('value');
    }

    setDescripcionInput = function (descripcion) {
        this.descripcionInput.sendKeys(descripcion);
    }

    getDescripcionInput = function () {
        return this.descripcionInput.getAttribute('value');
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
