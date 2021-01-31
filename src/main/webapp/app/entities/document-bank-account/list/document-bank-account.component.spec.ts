import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { DocumentBankAccountService } from '../service/document-bank-account.service';
import { DocumentBankAccount } from '../document-bank-account.model';

import { DocumentBankAccountComponent } from './document-bank-account.component';

describe('Component Tests', () => {
  describe('DocumentBankAccount Management Component', () => {
    let comp: DocumentBankAccountComponent;
    let fixture: ComponentFixture<DocumentBankAccountComponent>;
    let service: DocumentBankAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [DocumentBankAccountComponent],
      })
        .overrideTemplate(DocumentBankAccountComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DocumentBankAccountComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(DocumentBankAccountService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DocumentBankAccount('123')],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.documentBankAccounts?.[0]).toEqual(jasmine.objectContaining({ id: '123' }));
    });
  });
});
