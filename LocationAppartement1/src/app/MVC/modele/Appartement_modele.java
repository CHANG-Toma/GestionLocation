package app.MVC.modele;

public class Appartement_modele {
    private int id;
    private String name;
    private int bedrooms;
    private int bathrooms;
    private int sqft;
    private boolean available;

    // Getters and setters

    public Appartement_modele(String dataAppartement)
    {
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getBedrooms()
    {
        return bedrooms;
    }

    public int getBathrooms()
    {
        return bathrooms;
    }

    public boolean rent()
    {
        if (available) {
            available = false;
            return true;
        } else {
            return false;
        }
    }
}
