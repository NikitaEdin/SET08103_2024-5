package com.napier.sem;

/**
 * Class representing the population breakdown within a country/area into urban and rural population.
 */
public class PopulationBreakdown {

    /** The scope of the breakdown - Country/Continent/Region */
    private final String title;
    /** The name of the actual breakdown scope, the actual name of the country/continent/region */
    private final String name;
    /** Total population of the country/continent/region */
    private final long totalPopulation;
    /** Total population in urban areas of the country/continent/region */
    private final long urbanPopulation;
    /** Total population in rural areas of the country/continent/region */
    private final long ruralPopulation;

    /**
     * Constructs a new PopulationBreakdown object, to hold the country/continent/region and the corresponding population by type
     * @param title The type of scope, used for print purposes (Country/continent/region)
     * @param name The name of the country/continent/region
     * @param totalPopulation Total population number of the given country/continent/region
     * @param urbanPopulation Total population number in urban areas
     * @param ruralPopulation Total population number of rural areas
     */
    public PopulationBreakdown(String title, String name, long totalPopulation, long urbanPopulation, long ruralPopulation) {
        this.title = title;
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.urbanPopulation = urbanPopulation;
        this.ruralPopulation = ruralPopulation;
    }

    /**
     * Gets a visual summary of the population breakdown
     * @return returns a string containing the name and the different population breakdowns
     */
    @Override
    public String toString() {
        return (title + ": " + name +
                ", totalPopulation: " + totalPopulation +
                ", urbanPopulation: " + urbanPopulation + " (" + ( totalPopulation > 0 ? String.format("%.2f", ((double)urbanPopulation /  (double)totalPopulation) * 100) : 0)+ "%)" +
                ", ruralPopulation: " + ruralPopulation + " (" + (totalPopulation > 0 ? String.format("%.2f", ((double)ruralPopulation /  (double)totalPopulation) * 100) : 0)+ "%)");
    }

    /**
     * Get the name of type the breakdown represents (Country, Region, Continent)
     * @return returns the type of the scope of the language
     */
    public String getTitle() { return title; }

    /**
     * Get the name of the country/region/continent
     * @return returns the name of the country/region/continent
     */
    public String getName() { return name; }

    /**
     * Get total population
     * @return returns value of total population
     */
    public long getTotalPopulation() { return totalPopulation < 0 ? 0 : totalPopulation; }

    /**
     * Get total urban population
     * @return returns value of total population in urban areas
     */
    public long getUrbanPopulation() { return urbanPopulation < 0 ? 0 : urbanPopulation; }

    /**
     * Get total rural population
     * @return returns value of total population in rural
     */
    public long getRuralPopulation() { return ruralPopulation < 0 ? 0 : ruralPopulation; }
}
