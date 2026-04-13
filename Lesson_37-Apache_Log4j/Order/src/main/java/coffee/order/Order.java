
package coffee.order;


class Order {
    
    private int orderID;
    private String name;

    public Order(int orderID, String name) {
        this.orderID = orderID;
        this.name = name;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getName() {
        return name;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", name=" + name + '}';
    }
    
}
