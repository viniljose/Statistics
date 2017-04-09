package transaction.web;


public class CreateWebRequest {

    private Double amount;
    private Long timestamp;

    public Double getAmount() {
        return amount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CreateWebRequest{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
