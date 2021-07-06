import { TestBed } from '@angular/core/testing';
import { of, throwError } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { ReportserviceService } from './reportservice.service';

describe('ReportserviceService', () => {
  let httpClientSpy : {post:jasmine.Spy}
  let service: ReportserviceService;

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['post'])
    service = new ReportserviceService(httpClientSpy as any)
  });

  fit('should return expected Report (HttpClient called once)', () => {
    const report = [{
      identificationT: '8', identificationS:'service', startDate:'01/01/01', finalDate:"31/31/31"
    }]
    httpClientSpy.post.and.returnValue(of(report));
    service.getById("8");
    expect(httpClientSpy.post.calls.count()).toBe(1, 'one call');
  });

  it('should return an error when the server returns a 404', () => {
    const errorResponse = new HttpErrorResponse({
      error: 'test 404 error',
      status: 404,
      statusText: 'Not Found'
    });

    httpClientSpy.post.and.returnValue(throwError(errorResponse));

    service.getById("dsdsadsadsads");

    expect(service.prueba).toBeUndefined();
  });
});
