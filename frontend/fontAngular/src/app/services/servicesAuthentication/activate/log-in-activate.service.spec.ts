import { TestBed } from '@angular/core/testing';

import { LogInActivateService } from './log-in-activate.service';

describe('LogInActivateService', () => {
  let service: LogInActivateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LogInActivateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
