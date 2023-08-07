import { TestBed } from '@angular/core/testing';

import { ServerDetailsService } from './server-details.service';

describe('ServerDetailsService', () => {
  let service: ServerDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServerDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
