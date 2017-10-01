import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Atributo e2e test', () => {

    let navBarPage: NavBarPage;
    let atributoDialogPage: AtributoDialogPage;
    let atributoComponentsPage: AtributoComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Atributos', () => {
        navBarPage.goToEntity('atributo');
        atributoComponentsPage = new AtributoComponentsPage();
        expect(atributoComponentsPage.getTitle()).toMatch(/recambios2UsoApp.atributo.home.title/);

    });

    it('should load create Atributo dialog', () => {
        atributoComponentsPage.clickOnCreateButton();
        atributoDialogPage = new AtributoDialogPage();
        expect(atributoDialogPage.getModalTitle()).toMatch(/recambios2UsoApp.atributo.home.createOrEditLabel/);
        atributoDialogPage.close();
    });

    it('should create and save Atributos', () => {
        atributoComponentsPage.clickOnCreateButton();
        atributoDialogPage.setNombreInput('nombre');
        expect(atributoDialogPage.getNombreInput()).toMatch('nombre');
        atributoDialogPage.setValorInput('valor');
        expect(atributoDialogPage.getValorInput()).toMatch('valor');
        atributoDialogPage.save();
        expect(atributoDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class AtributoComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-atributo div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class AtributoDialogPage {
    modalTitle = element(by.css('h4#myAtributoLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    nombreInput = element(by.css('input#field_nombre'));
    valorInput = element(by.css('input#field_valor'));

    getModalTitle() {
        return this.modalTitle.getAttribute('jhiTranslate');
    }

    setNombreInput = function (nombre) {
        this.nombreInput.sendKeys(nombre);
    }

    getNombreInput = function () {
        return this.nombreInput.getAttribute('value');
    }

    setValorInput = function (valor) {
        this.valorInput.sendKeys(valor);
    }

    getValorInput = function () {
        return this.valorInput.getAttribute('value');
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
