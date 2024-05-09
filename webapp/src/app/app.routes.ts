import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { TransactionListComponent } from './transaction-list/transaction-list.component';

export const routes: Routes = [
    {
        path: '',
        component: LoginComponent,
        title: 'login.title',
    },
    {
        path: 'login',
        component: LoginComponent,
        title: 'login.title',
    },
    {
        path: 'transactions',
        component: TransactionListComponent,
        title: 'transaction-list.title',
    },
    { path: '', redirectTo: '/login', pathMatch: 'full' }, 
    
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule { }