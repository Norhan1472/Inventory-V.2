import { TestBed } from '@angular/core/testing';

import { RouteActivateService } from './route-activate.service';

describe('RouteActivateService', () => {
  let service: RouteActivateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RouteActivateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
