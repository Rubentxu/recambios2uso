import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Desguace e2e test', () => {

    let navBarPage: NavBarPage;
    let desguaceDialogPage: DesguaceDialogPage;
    let desguaceComponentsPage: DesguaceComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Desguaces', () => {
        navBarPage.goToEntity('desguace');
        desguaceComponentsPage = new DesguaceComponentsPage();
        expect(desguaceComponentsPage.getTitle()).toMatch(/recambios2UsoApp.desguace.home.title/);

    });

    it('should load create Desguace dialog', () => {
        desguaceComponentsPage.clickOnCreateButton();
        desguaceDialogPage = new DesguaceDialogPage();
        expect(desguaceDialogPage.getModalTitle()).toMatch(/recambios2UsoApp.desguace.home.createOrEditLabel/);
        desguaceDialogPage.close();
    });

    it('should create and save Desguaces', () => {
        desguaceComponentsPage.clickOnCreateButton();
        desguaceDialogPage.setNombreInput('nombre');
        expect(desguaceDialogPage.getNombreInput()).toMatch('nombre');
        desguaceDialogPage.setEmailInput('email');
        expect(desguaceDialogPage.getEmailInput()).toMatch('email');
        desguaceDialogPage.setTelefonoInput('telefono');
        expect(desguaceDialogPage.getTelefonoInput()).toMatch('telefono');
        desguaceDialogPage.setMovilInput('movil');
        expect(desguaceDialogPage.getMovilInput()).toMatch('movil');
        desguaceDialogPage.save();
        expect(desguaceDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class DesguaceComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-desguace div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class DesguaceDialogPage {
    modalTitle = element(by.css('h4#myDesguaceLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nombreInput = element(by.css('input#field_nombre'));
    emailInput = element(by.css('input#field_email'));
    telefonoInput = element(by.css('input#field_telefono'));
    movilInput = element(by.css('input#field_movil'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setNombreInput = function (nombre) {
        this.nombreInput.sendKeys(nombre);
    }

    getNombreInput = function () {
        return this.nombreInput.getAttribute('value');
    }

    setEmailInput = function (email) {
        this.emailInput.sendKeys(email);
    }

    getEmailInput = function () {
        return this.emailInput.getAttribute('value');
    }

    setTelefonoInput = function (telefono) {
        this.telefonoInput.sendKeys(telefono);
    }

    getTelefonoInput = function () {
        return this.telefonoInput.getAttribute('value');
    }

    setMovilInput = function (movil) {
        this.movilInput.sendKeys(movil);
    }

    getMovilInput = function () {
        return this.movilInput.getAttribute('value');
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
