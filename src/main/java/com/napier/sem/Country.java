package com.napier.sem;

/**
 * Class representing a Country from the country table in the database
 */
public class Country {

    /** Code of the country (Primary Key) (3-character) */
    public String Code;

    /** Name of the country */
    public String Name;

    /** Continent the country belongs to */
    public String Continent;

    /** Region the country is located in */
    public String Region;

    /** Surface area of the country in square kilometers */
    public double SurfaceArea;

    /** Year the country gained independence, if available */
    public Integer IndepYear;

    /** Population of the country */
    public int Population;

    /** Life expectancy in the country (if available) */
    public Double LifeExpectancy;

    /** Gross National Product (GNP) of the country */
    public Double GNP;

    /** Previous Gross National Product (GNP) of the country (if available) */
    public Double GNPOld;

    /** Local name of the country */
    public String LocalName;

    /** Form of government in the country */
    public String GovernmentForm;

    /** Head of state (president, king, etc.) of the country (if available) */
    public String HeadOfState;

    /** ID of the capital city of the country (if available) */
    public Integer Capital;

    /** Country code (2-character alternative) */
    public String Code2;

    /**
     * Constructs a new Country object with the provided data.
     *
     * @param Code          Code of the country (3-character)
     * @param Name          Name of the country
     * @param Continent     Continent the country belongs to
     * @param Region        Region the country is located in
     * @param SurfaceArea   Surface area of the country
     * @param IndepYear     Year the country gained independence (nullable)
     * @param Population    Population of the country
     * @param LifeExpectancy Life expectancy in the country (nullable)
     * @param GNP           Gross National Product (nullable)
     * @param GNPOld        Previous GNP value (nullable)
     * @param LocalName     Local name of the country
     * @param GovernmentForm Form of government
     * @param HeadOfState   Head of state (nullable)
     * @param Capital       ID of the capital city (nullable)
     * @param Code2         Alternative country code (2-character)
     */
    public Country(String Code, String Name, String Continent, String Region, double SurfaceArea,
                   Integer IndepYear, int Population, Double LifeExpectancy, Double GNP,
                   Double GNPOld, String LocalName, String GovernmentForm, String HeadOfState,
                   Integer Capital, String Code2) {
        this.Code = Code;
        this.Name = Name;
        this.Continent = Continent;
        this.Region = Region;
        this.SurfaceArea = SurfaceArea;
        this.IndepYear = IndepYear;
        this.Population = Population;
        this.LifeExpectancy = LifeExpectancy;
        this.GNP = GNP;
        this.GNPOld = GNPOld;
        this.LocalName = LocalName;
        this.GovernmentForm = GovernmentForm;
        this.HeadOfState = HeadOfState;
        this.Capital = Capital;
        this.Code2 = Code2;
    }

    /**
     * Method to retrieve the country code of a Country object.
     * @return Country code of the object.
     */
    public String getCode() {
        return Code;
    }

    /**
     * Method to retrive the country name.
     * @return Name of the country.
     */
    public String getName() {
        return Name;
    }

    /**
     * Method to retrieve the total population of this country.
     * @return Number of population
     */
    public int getPopulation() { return Population;}

    /**
     * Returns a string representation of the Country object.
     *
     * @return A string containing the country's information.
     */
    @Override
    public String toString() {
        return "Country{" +
                "Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", Continent='" + Continent + '\'' +
                ", Region='" + Region + '\'' +
                ", Population=" + Population +
                ", Capital=" + Capital +
                '}';
    }
}
