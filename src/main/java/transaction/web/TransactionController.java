package transaction.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import transaction.gateway.Transaction;
import transaction.gateway.TransactionGateway;

import java.lang.invoke.MethodHandles;
import java.sql.Timestamp;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@RestController
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    TransactionGateway transactionGateway;

    @RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createTransaction(@RequestBody final CreateWebRequest createWebRequest) {
        LOGGER.info("Request:" + createWebRequest);
        Transaction transaction = new Transaction();
        transaction.setAmount(createWebRequest.getAmount());
        transaction.setTimestamp(createWebRequest.getTimestamp());
        transaction.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        transaction.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        transactionGateway.save(transaction);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Summary> statistics() {
        Summary summary = new Summary();
        Long curTime = System.currentTimeMillis() - (60 * 1000);
        Timestamp time = new Timestamp(curTime);
        List<Double> trans = transactionGateway.findAllTransaction(time);
        LOGGER.info("Transaction List" + trans);

        if (trans.isEmpty())
            return new ResponseEntity(HttpStatus.OK);

        DoubleSummaryStatistics stats = trans.stream()
                .mapToDouble((x) -> x)
                .summaryStatistics();
        summary.setSum(stats.getSum());
        summary.setAvg(stats.getAverage());
        summary.setMax(stats.getMax());
        summary.setMin(stats.getMin());
        summary.setCount(stats.getCount());

        return new ResponseEntity<Summary>(summary, HttpStatus.OK);
    }

}
