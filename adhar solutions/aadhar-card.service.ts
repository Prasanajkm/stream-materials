import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AadharCard } from './aadhar-card';

@Injectable()
export class AadharCardService {

  //Inject HttpClient object to the constructor
  constructor(private httpClient: HttpClient) { }

  /*This method consumes the post method exposed at the
   URI http://localhost:8765/AadharAPI/getAadhar to retrieve the aadhar card details by passing Aadharcard object
   which in turn returns the Aadharcard object wrapped inside Observable object*/
  getAadhar(data: AadharCard) : Observable<AadharCard> {
    return this.httpClient.post<AadharCard>('http://localhost:8765/AadharAPI/getAadhar',data);
    
  }

   /*This method consumes the post method exposed at the
   URI http://localhost:8765/AadharAPI/updatePhoneNumber to update the aadhar card details by passing Aadharcard object
   which in turn returns the Aadharcard object wrapped inside Observable object*/
  updatePhoneNumber(data: AadharCard): Observable<AadharCard> {
    return this.httpClient.post<AadharCard>('http://localhost:8765/AadharAPI/updatePhoneNumber',data);
    
  }

  
}
