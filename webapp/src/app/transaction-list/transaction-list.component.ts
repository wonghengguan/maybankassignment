import { ApplicationModule, Component, OnInit } from '@angular/core';
import { Transaction } from './transaction.model';
import { TransactionService } from '../services/auth/transaction.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-transaction-list',
  standalone: true,
  imports: [CommonModule, ApplicationModule, FormsModule, ReactiveFormsModule, MatFormFieldModule, MatInputModule],
  templateUrl: './transaction-list.component.html',
  styleUrl: './transaction-list.component.css'
})
export class TransactionListComponent implements OnInit {
  transactions: Transaction[] = [];
  currentPage: number = 0;
  totalPages: number = 0;
  searchForm: FormGroup = this.fb.group({
    accountNumber: [''],
    customerId: [''],
    description: ['']
  });

  constructor(private transactionService: TransactionService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.getTransactions();
  }

  getTransactions(): void {
    this.transactionService.getTransactions(this.currentPage).subscribe(response => {
      this.transactions = response.content;
      this.totalPages = response.totalPages;
    });
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.getTransactions();
    }
  }

  prevPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.getTransactions();
    }
  }


  save(transaction: Transaction): void {
    // this.transactionService.updateTransaction(transaction).subscribe(() => {
    //   // Update the transaction in the UI
    // });
  }

  batchUpdate(): void {
    // Implement batch update logic here
  }

  search(): void {
    this.transactionService.getTransactions(this.currentPage).subscribe(response => {
      this.transactions = response.content;
      this.totalPages = response.totalPages;
    });
  }
}
