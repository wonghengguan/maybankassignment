import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { TransactionListComponent } from './transaction-list/transaction-list.component';

export const routes: Routes = [
    {
        path: '',
        component: LoginComponent,
        title: 'Login',
    },
    {
        path: 'login',
        component: LoginComponent,
        title: 'Login',
    },
    {
        path: 'transactions',
        component: TransactionListComponent,
        title: 'Transactions',
    },
    { path: '', redirectTo: '/login', pathMatch: 'full' }, 
    
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }