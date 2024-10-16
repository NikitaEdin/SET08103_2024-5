package com.napier.sem;

/**
 * Class representing a City from CITY table in the database.
 */
public class City {

    /** Unique ID of the city. */
    public int ID;

    /** Name of the city. */
    public String Name;

    /** Country code the city belongs to. */
    public String CountryCode;

    /** District the city is located in. */
    public String District;

    /** Population of the city. */
    public int Population;

    /**
     * Constructs new City object with the provided data.
     *
     * @param ID         Unique ID of the city.
     * @param Name       Mame of the city.
     * @param CountryCode Country code the city belongs to.
     * @param District   District the city is located in.
     * @param Population Population of the city.
     */
    public City(int ID, String Name, String CountryCode, String District, int Population) {
        this.ID = ID;
        this.Name = Name;
        this.CountryCode = CountryCode;
        this.District = District;
        this.Population = Population;
    }

    /**
     * Returns a string representation of the City object.
     *
     * @return A string containing the city's information.
     */
    @Override
    public String toString() {
        return "City{" +
                "Name='" + Name + '\'' +
                ", Country='" + CountryCode + '\'' +
                ", District='" + District + '\'' +
                ", Population=" + Population +
                '}';
    }
}

