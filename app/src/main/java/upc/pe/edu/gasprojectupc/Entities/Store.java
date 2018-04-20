package upc.pe.edu.gasprojectupc.Entities;

import java.io.Serializable;

/**
 * Created by wes_o on 19/04/2018.
 */

public class Store implements Serializable{
    private String nombre;
    private int foto;
    private String description;
    private int imgDetail;
    private String shortDescription;

    public Store(String nombre, int foto, String description, int imgDetail, String shortDescription) {
        this.nombre = nombre;
        this.foto = foto;
        this.description = description;
        this.imgDetail = imgDetail;
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgDetail() {
        return imgDetail;
    }

    public void setImgDetail(int imgDetail) {
        this.imgDetail = imgDetail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
