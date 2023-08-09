import { TestBed } from '@angular/core/testing';

import { UtenteApiService } from './utente-api.service';

describe('UtenteApiService', () => {
  let service: UtenteApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UtenteApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
