import { TestBed } from '@angular/core/testing';

import { UserAccService } from './user-acc.service';

describe('UserAccService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserAccService = TestBed.get(UserAccService);
    expect(service).toBeTruthy();
  });
});
