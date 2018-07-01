package upc.pe.edu.gasprojectupc.Entities;

import java.io.Serializable;

public class Product implements Serializable{
    public String nombre;
    public String unitPrice;
    public String img;


    public Product() {
    }

    public Product(String nombre, String unitPrice, String img) {
        this.nombre = nombre;
        this.unitPrice = unitPrice;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
