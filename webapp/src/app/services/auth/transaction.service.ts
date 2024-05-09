import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private baseUrl = 'http://localhost:8080/api';
  private versionUrl = 'v1'; 
  private getUrl = 'public/get-transactions';
  private updateUrl = 'private/batch-update-description';

  constructor(private http: HttpClient) { }

  getAllTransactions(criteria: any, pageable: any): Observable<any> {
    const params = new HttpParams()
    .set('page', pageable.page)
    .set('size', pageable.size);

    return this.http.post<any>(`${this.baseUrl}/${this.versionUrl}/${this.getUrl}`, criteria, { params });
}
  
  batchUpdateDescription(transactions: any[], username: string, password: string): Observable<any> {
    const basicAuth = btoa('user:password');
    const headers = new HttpHeaders({ Authorization: `Basic ${basicAuth}` });
    
    return this.http.put(`${this.baseUrl}/${this.versionUrl}/${this.updateUrl}`, transactions, { headers });
  }
}
