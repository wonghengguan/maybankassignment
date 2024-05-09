import { ApplicationModule, Component, OnInit } from '@angular/core';
import { Transaction } from './transaction.model';
import { TransactionService } from '../services/auth/transaction.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
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
  searchForm: FormGroup;

  constructor(private transactionService: TransactionService, private fb: FormBuilder) {
    this.searchForm = this.fb.group({
      accountNumber: [''],
      customerId: [''],
      description: ['']
    });
  }

  ngOnInit(): void {
    this.getTransactions();
  }

  getTransactions(): void {
    const { accountNumber, customerId, description } = this.searchForm.value;
    const pageable = { page: this.currentPage, size: 10 };
    const searchParams: any = {};
    if (accountNumber) {
      const accountNumbersArray = accountNumber.split(',').map((num: string) => num.trim());
      searchParams.accountNumber = accountNumbersArray;
    }
    if (customerId) {
      const customerIdsArray = customerId.split(',').map((id: string) => id.trim());
      searchParams.customerId = customerIdsArray;
    }
    if (description) {
      searchParams.description = description;
    }

    this.transactionService.getAllTransactions(searchParams, pageable)
      .subscribe(response => {
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

  batchUpdate(): void {
    this.transactionService.batchUpdateDescription(this.transactions, 'user', 'password')
      .subscribe({
        next: () => {
          this.getTransactions();
        },
        error: error => {
          console.error('Batch update failed:', error);
        }
      });
  }

  search(): void {
    this.currentPage = 0; // Reset page when performing a new search
    this.getTransactions();
  }
}
