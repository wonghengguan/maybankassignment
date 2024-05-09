package com.wonghengguan.maybank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.wonghengguan.maybank.model.Transaction;
import com.wonghengguan.maybank.repo.TransactionRepo;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DataInitializeService {

    @Autowired
    private TransactionRepo transactionRepo;

    public void insertSampleData() {
        try {
            // Load the data.txt file from resources
            File file = ResourceUtils.getFile("classpath:dataSource.txt");
            
            // Read all lines from the file
            List<String> lines = Files.readAllLines(file.toPath());

            // Start from index 1 assuming index 0 is always title
            for (int i = 1 ; i<lines.size(); i++) {
                String[] parts = lines.get(i).split("\\|");

                String accountNumber = parts[0];
                String trxAmt = parts[1];
                String description = parts[2];
                String trxDate = parts[3];
                String trxTime = parts[4];
                String customerId = parts[5];

                Long accountNumberLong = Long.parseLong(accountNumber);
                Long customerIdLong = Long.parseLong(customerId);
                BigDecimal trxAmount = new BigDecimal(trxAmt);
                Date date = null;
                Time time = null;

                try{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    date = dateFormat.parse(trxDate);

                    // Convert time string to java.sql.Time
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    Date parsedTime = timeFormat.parse(trxTime);
                    time = new Time(parsedTime.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Transaction newTransaction = new Transaction();
                newTransaction.setAccountNumber(Long.valueOf(accountNumberLong));
                newTransaction.setCustomerId(Long.valueOf(customerIdLong));
                newTransaction.setTrxAmount(trxAmount);
                newTransaction.setTrxDate(date);
                newTransaction.setTrxTime(time);
                newTransaction.setDescription(description);
                
                transactionRepo.save(newTransaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
