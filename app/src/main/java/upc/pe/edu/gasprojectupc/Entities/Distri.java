package upc.pe.edu.gasprojectupc.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Distri implements Serializable    {

    String date;
    String description;
    String idSupplier;
    String image;
    String latitude;
    String longitude;
    String name;
    String phone;
    ArrayList<Product> products;

    public Distri() {
    }

    public Distri(String date, String description, String idSupplier, String image, String latitude, String longitude, String name, String phone, ArrayList<Product> products) {
        this.date = date;
        this.description = description;
        this.idSupplier = idSupplier;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phone = phone;
        this.products = products;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}



