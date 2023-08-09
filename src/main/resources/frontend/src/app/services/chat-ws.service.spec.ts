import { TestBed } from '@angular/core/testing';

import { ChatWsService } from './chat-ws.service';

describe('ChatWsService', () => {
  let service: ChatWsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChatWsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
