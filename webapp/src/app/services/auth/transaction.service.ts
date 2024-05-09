import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

    private baseUrl = 'http://localhost:8080';
  private apiUrl = 'api/v1/public/get-transactions'; // Endpoint to fetch transactions

  constructor(private http: HttpClient) { }

  getTransactions(page: number = 0, size: number = 10): Observable<any> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>(`${this.baseUrl}/${this.apiUrl}`, { params });
  }

  getTransactionsByDescription(description: string, page: number = 0, size: number = 10): Observable<any> {
    const params = new HttpParams()
      .set('description', description)
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>('api/v1/public/get-transactions/description', { params });
  }

  getTransactionsByAccountNumbers(accountNumbers: number[], page: number = 0, size: number = 10): Observable<any> {
    const params = new HttpParams()
      .set('accountNumber', accountNumbers.join(','))
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>('api/v1/public/get-transactions/accountNumbers', { params });
  }

  getTransactionsByCustomerIds(customerIds: number[], page: number = 0, size: number = 10): Observable<any> {
    const params = new HttpParams()
      .set('customerId', customerIds.join(','))
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<any>('api/v1/public/get-transactions/customerIds', { params });
  }

  // Implement updateDescription() 
}
