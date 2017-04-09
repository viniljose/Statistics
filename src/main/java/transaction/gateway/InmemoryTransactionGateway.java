package transaction.gateway;


import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

public class InmemoryTransactionGateway implements TransactionGateway<Transaction> {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction save(Transaction o) {
        return transactionRepository.save(o);
    }

    @Override
    public List<Double> findAllTransaction(Timestamp from) {
        return transactionRepository.findAllTransaction(from);
    }
}
