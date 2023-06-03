import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../models/interfaces/company.interface';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
    private BASE_URL = 'http://localhost:8081/company';

    constructor(private http: HttpClient) { }


    registerCompany(company:Company):Observable<any>{
         return this.http.post(this.BASE_URL,company)
    }
}
