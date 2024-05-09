export interface Transaction {
    id: number;
    trxAmount: number;
    trxDate: Date;
    trxTime: string;
    description: string;
    accountNumber: number;
    customerId: number;
    [key: string]: any;
  }
  