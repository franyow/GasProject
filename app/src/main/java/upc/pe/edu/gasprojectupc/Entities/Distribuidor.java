package upc.pe.edu.gasprojectupc.Entities;

public class Distribuidor {
    //private String date;
    private String description;


    private String name;
    private String image;
   // private Long phone;

    public Distribuidor() {
    }

    public Distribuidor(String description, String name, String image) {
        this.description = description;
        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }







    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
