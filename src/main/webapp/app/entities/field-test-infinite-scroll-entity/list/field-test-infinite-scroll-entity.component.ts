import { Component, OnInit } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFieldTestInfiniteScrollEntity } from '../field-test-infinite-scroll-entity.model';

import { ITEMS_PER_PAGE } from 'app/config/pagination.constants';
import { FieldTestInfiniteScrollEntityService } from '../service/field-test-infinite-scroll-entity.service';
import { FieldTestInfiniteScrollEntityDeleteDialogComponent } from '../delete/field-test-infinite-scroll-entity-delete-dialog.component';
import { DataUtils } from 'app/core/util/data-util.service';
import { ParseLinks } from 'app/core/util/parse-links.service';

@Component({
  selector: 'jhi-field-test-infinite-scroll-entity',
  templateUrl: './field-test-infinite-scroll-entity.component.html',
})
export class FieldTestInfiniteScrollEntityComponent implements OnInit {
  fieldTestInfiniteScrollEntities: IFieldTestInfiniteScrollEntity[];
  isLoading = false;
  itemsPerPage: number;
  links: { [key: string]: number };
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected fieldTestInfiniteScrollEntityService: FieldTestInfiniteScrollEntityService,
    protected dataUtils: DataUtils,
    protected modalService: NgbModal,
    protected parseLinks: ParseLinks
  ) {
    this.fieldTestInfiniteScrollEntities = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.isLoading = true;

    this.fieldTestInfiniteScrollEntityService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe(
        (res: HttpResponse<IFieldTestInfiniteScrollEntity[]>) => {
          this.isLoading = false;
          this.paginateFieldTestInfiniteScrollEntities(res.body, res.headers);
        },
        () => {
          this.isLoading = false;
        }
      );
  }

  reset(): void {
    this.page = 0;
    this.fieldTestInfiniteScrollEntities = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IFieldTestInfiniteScrollEntity): string {
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    return this.dataUtils.openFile(base64String, contentType);
  }

  delete(fieldTestInfiniteScrollEntity: IFieldTestInfiniteScrollEntity): void {
    const modalRef = this.modalService.open(FieldTestInfiniteScrollEntityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fieldTestInfiniteScrollEntity = fieldTestInfiniteScrollEntity;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.reset();
      }
    });
  }

  protected sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateFieldTestInfiniteScrollEntities(data: IFieldTestInfiniteScrollEntity[] | null, headers: HttpHeaders): void {
    this.links = this.parseLinks.parse(headers.get('link') ?? '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.fieldTestInfiniteScrollEntities.push(data[i]);
      }
    }
  }
}
