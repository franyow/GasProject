package upc.pe.edu.gasprojectupc.Entities;

public class ProductsIn {
    String nombre;
    String unitPrice;

    public ProductsIn() {
    }

    public ProductsIn(String nombre, String unitPrice) {
        this.nombre = nombre;
        this.unitPrice = unitPrice;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
