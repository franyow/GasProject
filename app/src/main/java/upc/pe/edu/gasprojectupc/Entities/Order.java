package upc.pe.edu.gasprojectupc.Entities;

public class Order {
    String date;
    String idCustomer;
    String idOrder;
    String idProduct;
    String price;
    String quantity;

    public Order() {
    }

    public Order(String date, String idCustomer, String idOrder, String idProduct, String price, String quantity) {
        this.date = date;
        this.idCustomer = idCustomer;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}