import { TestBed } from '@angular/core/testing';

import { OrdineApiService } from './ordine-api.service';

describe('OrdineApiService', () => {
  let service: OrdineApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrdineApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
