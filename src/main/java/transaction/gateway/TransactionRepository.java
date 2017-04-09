package transaction.gateway;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Query("Select t.amount FROM Transaction t WHERE t.createdDate >= :val")
    List<Double> findAllTransaction(@Param("val") Timestamp val);

}
