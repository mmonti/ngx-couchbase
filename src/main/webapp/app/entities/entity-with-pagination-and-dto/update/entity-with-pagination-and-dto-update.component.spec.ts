jest.mock('@angular/router');

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EntityWithPaginationAndDTOService } from '../service/entity-with-pagination-and-dto.service';
import { EntityWithPaginationAndDTO } from '../entity-with-pagination-and-dto.model';

import { EntityWithPaginationAndDTOUpdateComponent } from './entity-with-pagination-and-dto-update.component';

describe('Component Tests', () => {
  describe('EntityWithPaginationAndDTO Management Update Component', () => {
    let comp: EntityWithPaginationAndDTOUpdateComponent;
    let fixture: ComponentFixture<EntityWithPaginationAndDTOUpdateComponent>;
    let service: EntityWithPaginationAndDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EntityWithPaginationAndDTOUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EntityWithPaginationAndDTOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EntityWithPaginationAndDTOUpdateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EntityWithPaginationAndDTOService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityWithPaginationAndDTO('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EntityWithPaginationAndDTO();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
