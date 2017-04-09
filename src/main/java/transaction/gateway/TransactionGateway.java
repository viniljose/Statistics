package transaction.gateway;


import java.sql.Timestamp;
import java.util.List;

public interface TransactionGateway<Transaction> {
    Transaction save(Transaction transaction);
    List<Double> findAllTransaction(Timestamp from);
}
