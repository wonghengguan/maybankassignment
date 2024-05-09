import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from '../../transaction-list/transaction.model';

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
    let params = new HttpParams();
    Object.keys(criteria).forEach(key => {
      params = params.set(key, criteria[key]);
    });
    params = params.set('page', pageable.page);
    params = params.set('size', pageable.size);
    return this.http.post<any>(`${this.baseUrl}/${this.versionUrl}/${this.getUrl}`, criteria, { params });
}
  
  batchUpdateDescription(transactions: any[], username: string, password: string): Observable<any> {
    const basicAuth = btoa('user:password');
    const headers = new HttpHeaders({ Authorization: `Basic ${basicAuth}` });
    
    return this.http.put(`${this.baseUrl}/${this.versionUrl}/${this.updateUrl}`, transactions, { headers });
  }
}
