import { TestBed } from '@angular/core/testing';

import { NotificaitionService } from './notificaition.service';

describe('NotificaitionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NotificaitionService = TestBed.get(NotificaitionService);
    expect(service).toBeTruthy();
  });
});
