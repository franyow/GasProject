package upc.pe.edu.gasprojectupc.Entities;

import java.io.Serializable;

public class Customer implements Serializable {
    private String birthDate;
    private String date;
    private String idCustomer;
    private String name;
    private String latitude;
    private String longitude;
    private String urlPhoto;


    public Customer(String birthDate, String date, String name, String idCustomer, String latitude, String longitude, String urlPhoto) {
        this.birthDate = birthDate;
        this.date = date;
        this.name = name;
        this.idCustomer = idCustomer;
        this.latitude = latitude;
        this.longitude = longitude;
        this.urlPhoto = urlPhoto;
    }

    public Customer() {
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
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
}
