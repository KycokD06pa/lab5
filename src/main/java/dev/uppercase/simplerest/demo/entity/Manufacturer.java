package dev.uppercase.simplerest.demo.entity;


import dev.uppercase.simplerest.demo.dao.ManufacturerDao;

public class Manufacturer{

    private int id;
    private String name_of_manufacturer;
    private String country;
    public Manufacturer(){

    }

    public Manufacturer(int id, String name_of_manufacturer, String country) {
        this.id = id;
        this.name_of_manufacturer = name_of_manufacturer;
        this.country = country;
    }

    public Manufacturer(String name_of_manufacturer, String country) {
        this.name_of_manufacturer = name_of_manufacturer;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_of_manufacturer() {
        return name_of_manufacturer;
    }

    public void setName_of_manufacturer(String name_of_manufacturer) {
        this.name_of_manufacturer = name_of_manufacturer;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manufacturer:" +
                "\nid: " + id +
                ",\nname_of_manufacturer: '" + name_of_manufacturer + '\'' +
                ",\ncountry: '" + country+"\n";
    }
}
