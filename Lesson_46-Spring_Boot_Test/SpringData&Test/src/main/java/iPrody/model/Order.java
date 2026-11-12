package iPrody.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ordername", nullable = false)
    private String ordername;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;


    public Order() {
    }

    public Order(long id, String ordername, BigDecimal amount, String currency) {
        this.id = id;
        this.ordername = ordername;
        this.amount = amount;
        this.currency = currency;
    }

    public Order(String ordername, BigDecimal amount, String currency) {
        this.ordername = ordername;
        this.amount = amount;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordername='" + ordername + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId() == order.getId() && Objects.equals(getOrdername(), order.getOrdername()) && Objects.equals(getAmount(), order.getAmount()) && Objects.equals(getCurrency(), order.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrdername(), getAmount(), getCurrency());
    }
}
