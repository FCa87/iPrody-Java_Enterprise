package iPrody.DTO;

import java.math.BigDecimal;

public class OrderDTO {

    private long id;
    private String ordername;
    private BigDecimal amount;
    private String currency;

    public OrderDTO() {
    }

    public OrderDTO(long id, String ordername, BigDecimal amount, String currency) {
        this.id = id;
        this.ordername = ordername;
        this.amount = amount;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public String getOrdername() {
        return ordername;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
