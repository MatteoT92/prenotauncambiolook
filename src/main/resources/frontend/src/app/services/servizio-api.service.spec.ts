import { TestBed } from '@angular/core/testing';

import { ServizioApiService } from './servizio-api.service';

describe('ServizioApiService', () => {
  let service: ServizioApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServizioApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
