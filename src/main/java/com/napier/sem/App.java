package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is class is to run the program and print the reports that are needed.
 */
@SpringBootApplication
@RestController
public class App {

    /**
     * Main app method to initialise the application.
     * @param args launching parameters
     */
    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        if (args.length < 1) {
            connect("localhost:33060", 10000);
        } else {
            connect(args[0], Integer.parseInt(args[1]));
        }

        // Run the spring application
        SpringApplication.run(App.class, args);

        // ######## REPORTS BEGIN HERE ######## ///
        generateAllReports(a);

        // Disconnect from database before termination
       // a.disconnect();
    }
    public static void generateAllReports(App a){
        // World Reports
        System.out.println("\nreport_PopulationDESC: ");
        print_Items(a.report_PopulationDESC());
        System.out.println("\nreport_PopulationByContinentDESC: ");
        print_Items(a.report_PopulationByContinentDESC("Asia"));
        // report_CountriesByRegionDESC
        System.out.println("\nreport_CountriesByRegionDESC: ");
        print_Items(a.report_CountriesByRegionDESC("South America"));

        // TopN Reports
        System.out.println("\nreport_TopN_PopulatedCountries: ");
        print_Items(a.report_TopN_PopulatedCountries(3));
        System.out.println("\nreport_TopN_PopulatedCountriesByContinent: ");
        print_Items(a.report_TopN_PopulatedCountriesByContinent("North America", 4));
        System.out.println("\nreport_TopN_PopulatedCountriesByRegion: ");
        print_Items(a.report_TopN_PopulatedCountriesByRegion("Central Africa", 5));
        System.out.println("\nreport_TopN_PopulatedCities: ");
        print_Items(a.report_TopN_PopulatedCities(3));
        System.out.println("\nreport_TopN_PopulatedCitiesByContinent: ");
        print_Items(a.report_TopN_PopulatedCitiesByContinent("North America", 5));
        System.out.println("\nreport_TopN_PopulatedCitiesByRegion: ");
        print_Items(a.report_TopN_PopulatedCitiesByRegion("Central America", 4));
        System.out.println("\nreport_TopN_PopulatedCitiesByCountry: ");
        print_Items(a.report_TopN_PopulatedCitiesByCountry("United States", 2));
        System.out.println("\nreport_TopN_PopulatedCitiesByDistrict: ");
        print_Items(a.report_TopN_PopulatedCitiesByDistrict("Western", 6));

        // City Reports
        System.out.println("\nreport_CitiesInWorldDESC: ");
        print_Items(a.report_CitiesInWorldDESC());
        System.out.println("\nreport_CitiesInContinentDESC: ");
        print_Items(a.report_CitiesInContinentDESC("Africa"));
        System.out.println("\nreport_CitiesInRegionDESC: ");
        print_Items(a.report_CitiesInRegionDESC("Central America"));
        System.out.println("\nreport_CitiesInCountryDESC: ");
        print_Items(a.report_CitiesInCountryDESC("Angola"));
        System.out.println("\nreport_CitiesInDistrictDESC: ");
        print_Items(a.report_CitiesInDistrictDESC("Scotland"));
        System.out.println("\nreport_CapitalCitiesInWorldDESC: ");
        print_Items_Capitals(a.report_CapitalCitiesInWorldDESC());
        System.out.println("\nreport_CapitalCitiesInContinentDESC: ");
        print_Items_Capitals(a.report_CapitalCitiesInContinentDESC("North America"));
        System.out.println("\nreport_CapitalCitiesInRegionDESC: ");
        print_Items_Capitals(a.report_CapitalCitiesInRegionDESC("Western Europe"));

        // TopN - CapitalCities
        System.out.println("\nreport_TopN_PopulatedCapitalCitiesInWorld:");
        print_Items(a.report_TopN_PopulatedCapitalCitiesInWorld(3));
        System.out.println("\nreport_TopN_PopulatedCapitalCitiesInContinent:");
        print_Items(a.report_TopN_PopulatedCapitalCitiesInContinent(3, "Asia"));
        System.out.println("\nreport_TopN_PopulatedCapitalCitiesInRegion:");
        print_Items(a.report_TopN_PopulatedCapitalCitiesInRegion(3,"Central America"));

        // Breakdown
        System.out.println("\nreport_PopulationBreakdown_AllContinents:");
        print_Items(a.report_PopulationBreakdown_AllContinents());
        System.out.println("\nreport_PopulationBreakdown_AllRegions:");
        print_Items(a.report_PopulationBreakdown_AllRegions());
        System.out.println("\nreport_PopulationBreakdown_AllCountries:");
        print_Items(a.report_PopulationBreakdown_AllCountries());

        // TotalPopulation
        System.out.println("\nreport_TotalPopulation_World:");
        long world = a.report_TotalPopulation_World();
        System.out.println(world);
        System.out.println("\nreport_TotalPopulation_Continent:");
        System.out.println(a.report_TotalPopulation_Continent("Asia"));
        System.out.println("\nreport_TotalPopulation_Region:");
        System.out.println(a.report_TotalPopulation_Region("Central America"));
        System.out.println("\nreport_TotalPopulation_Country:");
        System.out.println(a.report_TotalPopulation_Country("Germany"));
        System.out.println("\nreport_TotalPopulation_District:");
        System.out.println(a.report_TotalPopulation_District("Western"));
        System.out.println("\nreport_TotalPopulation_City:");
        System.out.println(a.report_TotalPopulation_City("London"));
        System.out.println("\nreport_WorldLanguagesBreakdown:");
        print_Items(a.report_WorldLanguagesBreakdown(world < 1 ? 6000000000L : world));
    }

    ///////////////////// REPORTS /////////////////////
//<editor-fold desc="SQL Reports">


    /**
     * All the countries in the world organised by largest population to smallest
     * @return Return a list of Country type in DESC order
     */
    @RequestMapping("report_PopulationDESC")
    public List<Country> report_PopulationDESC() {
        String query = "SELECT * FROM country ORDER BY population DESC";
        return getReport_Country(query);
    }

    /**
     * All the countries in a continent organised by largest population to smallest
     * @param continent Continent name to filter by
     * @return  Return a list of Country type in DESC order
     */
    @RequestMapping("report_PopulationByContinentDESC")
    public List<Country> report_PopulationByContinentDESC(@RequestParam(value = "continent") String continent) {
        if(continent != null && !continent.isEmpty()){
            String query = "SELECT * FROM country WHERE continent = '" + continent + "' ORDER BY population DESC";
            return getReport_Country(query);
        }
        else{
            System.out.println("Invalid continent");
            return null;
        }

    }

    /**
     * All the countries in a region organised by largest population to smallest
     * @param region Region name to filter by
     * @return Returns a list of Country type in DESC order
     */
    @RequestMapping("report_CountriesByRegionDESC")
    public List<Country> report_CountriesByRegionDESC(@RequestParam(value = "region") String region) {
        if (region !=null && !region.isEmpty()){
            String query = "SELECT * FROM country WHERE region = '"+ region +"' ORDER BY population DESC";
            return getReport_Country(query);
        }
        System.out.println("Invalid region");
        return null;
    }


    /**
     * The top N populated countries in the world where N is provided by the user
     * @param N Number of top populated countries to retrieve
     * @return A list of the top N populated countries, sorted in descending order by population
     */
    @RequestMapping("report_TopN_PopulatedCountries")
    public List<Country> report_TopN_PopulatedCountries(@RequestParam(value = "N") int N){
        if (N < 1) return null;
        return getReport_Country("SELECT * FROM country ORDER BY population DESC LIMIT "+ N);
    }

    /**
     * The top N populated countries in a continent where N is provided by the user
     * @param continent Name of the continent to filter the countries by
     * @param N The number of top populated countries to retrieve for the specified continent
     * @return List of top N populated countries in the specified continent
     */
    @RequestMapping("report_TopN_PopulatedCountriesByContinent")
    public List<Country> report_TopN_PopulatedCountriesByContinent(@RequestParam(value = "continent") String continent, @RequestParam(value = "N") int N) {
        if( N < 1 || continent == null || continent.isEmpty()) return null;
        String query = "SELECT * FROM country WHERE continent = '"+ continent +"' ORDER BY population DESC LIMIT " + N;
        return getReport_Country(query);
    }

    /**
     * The top N populated countries in a region where N is provided by the user
     * @param region Name of the region to filter the countries by
     * @param N Number of top populated countries to retrieve for the specified region
     * @return A list of top N populated countries in the specified region
     */
    @RequestMapping("report_TopN_PopulatedCountriesByRegion")
    public List<Country> report_TopN_PopulatedCountriesByRegion(@RequestParam(value = "region") String region,@RequestParam(value = "N") int N) {
        if( N < 1 || region == null || region.isEmpty()) return null;
        String query = "SELECT * FROM country WHERE region = '" + region + "' ORDER BY population DESC LIMIT "+ N;
        return getReport_Country(query);
    }

    /**
     * The top N populated cities in the world where N is provided by the user.
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities, sorted in descending order by population
     */
    @RequestMapping("report_TopN_PopulatedCities")
    public List<City> report_TopN_PopulatedCities(@RequestParam(value = "N") int N) {
        if (N < 1) return null;
        String query = "SELECT * FROM city ORDER BY population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * The top N populated cities in a continent where N is provided by the user.
     * @param continent Name of the continent to filter the cities by
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities in the specified continent, sorted in descending order by population
     */
    @RequestMapping("report_TopN_PopulatedCitiesByContinent")
    public List<City> report_TopN_PopulatedCitiesByContinent(@RequestParam(value = "continent") String continent,@RequestParam(value = "N") int N) {
        if (N < 1 || continent == null || continent.isEmpty()) return null;
        String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continent + "' ORDER BY city.Population DESC LIMIT " + N;
        return getReport_City(query);
    }


    /**
     * The top N populated cities in a region where N is provided by the user.
     * @param region Name of the region to filter the cities by
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities in specified region, sorted in descending order by population
     */
    @RequestMapping("report_TopN_PopulatedCitiesByRegion")
    public List<City> report_TopN_PopulatedCitiesByRegion(@RequestParam(value = "region") String region,@RequestParam(value = "N") int N) {
        if (N < 1 || region == null || region.isEmpty()) return null;
        String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + region + "' ORDER BY city.Population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * The top N populated cities in a country where N is provided by the user.
     * @param country Name of the country to filter the cities by
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities in specified country, sorted in descending order by population
     */
    @RequestMapping("report_TopN_PopulatedCitiesByCountry")
    public List<City> report_TopN_PopulatedCitiesByCountry(@RequestParam(value = "country") String country,@RequestParam(value = "N") int N) {
        if (N < 1 || country == null || country.isEmpty()) return null;
        String query = "SELECT city.* FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Name = '" + country + "' ORDER BY city.Population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * The top N populated cities in a district where N is provided by the user.
     * @param district Name of district to filter the cities by
     * @param N Number of top populated cities to retrieve
     * @return List of top N populated cities in specified district, sorted in descending order by population
     */
    @RequestMapping("report_TopN_PopulatedCitiesByDistrict")
    public List<City> report_TopN_PopulatedCitiesByDistrict(@RequestParam(value = "district") String district,@RequestParam(value = "N") int N) {
        if (N < 1 || district == null || district.isEmpty()) return null;
        String query = "SELECT * FROM city WHERE District = '" + district + "' ORDER BY Population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * All the cities in the world organised by largest population to smallest.
     * @return List of all cities in the world, sorted in descending order by population.
     */
    @RequestMapping("report_CitiesInWorldDESC")
    public List<City> report_CitiesInWorldDESC() {
        String query = "SELECT * FROM city ORDER BY population DESC";
        return getReport_City(query);
    }

    /**
     * All the cities in a continent organised by largest population to smallest.
     * @param continent Name of continent to filter the cities by.
     * @return List of cities in specified continent, sorted by descending order by population.
     */
    @RequestMapping("report_CitiesInContinentDESC")
    public List<City> report_CitiesInContinentDESC(@RequestParam(value = "continent") String continent) {
        if(continent == null || continent.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continent + "' ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the cities in a region organised by largest population to smallest.
     * @param region Name of region to filter the cities by.
     * @return List of cities in specified region, sorted by descending order by population.
     */
    @RequestMapping("report_CitiesInRegionDESC")
    public List<City> report_CitiesInRegionDESC(@RequestParam(value = "region") String region) {
        if(region == null || region.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + region + "' ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the cities in a country organised by largest population to smallest.
     * @param country Name of country to filter the cities by.
     * @return List of cities in the specified country, sorted by descending order by population.
     */
    @RequestMapping("report_CitiesInCountryDESC")
    public List<City> report_CitiesInCountryDESC(@RequestParam(value = "country") String country) {
        if(country == null || country.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Name = '" + country + "' ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the cities in a district organised by largest population to smallest.
     * @param district Name of district to filter the cities by.
     * @return List of cities in the specified country, sorted by descending order by population.
     */
    @RequestMapping("report_CitiesInDistrictDESC")
    public List<City> report_CitiesInDistrictDESC(@RequestParam(value = "district") String district) {
        if(district == null || district.isEmpty()) return null;
        String query = "SELECT * FROM city WHERE District = '" + district + "' ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the capital cities in the world organised by largest population to smallest.
     * @return List of all capital cities in the world, sorted by descending order by population.
     */
    @RequestMapping("report_CapitalCitiesInWorldDESC")
    public List<City> report_CapitalCitiesInWorldDESC() {
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Capital = city.ID ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the capital cities in a continent organised by largest population to smallest.
     * @param continent Name of district to filter the cities by.
     * @return List of capital cities in the specified continent, sorted by descending order by population.
     */
    @RequestMapping("report_CapitalCitiesInContinentDESC")
    public List<City> report_CapitalCitiesInContinentDESC(@RequestParam(value = "continent") String continent) {
        if(continent == null || continent.isEmpty()) return null;
        String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Continent = '" + continent + "' AND country.Capital = city.ID ORDER BY city.population DESC";
        return getReport_City(query);
    }

    /**
     * All the capital cities in a region organised by largest to smallest.
     * @param region Name of region to filter the cities by.
     * @return List of capital cities in the specified region, sorted by descending order by population.
     */
    @RequestMapping("report_CapitalCitiesInRegionDESC")
    public List<City> report_CapitalCitiesInRegionDESC(@RequestParam(value = "region") String region) {
        if (region != null && !region.isEmpty()){
            String query = "SELECT * FROM city JOIN country ON city.CountryCode = country.Code WHERE country.Region = '" + region + "' AND country.Capital = city.ID ORDER BY city.population DESC";
            return getReport_City(query);
        }
        System.out.println("Invalid Region");
       return null;
    }


    /**
     * Retrives top N most populated capital cities in the world.
     * @param N number of items to retrieve
     * @return List of top N populated capital cities
     */
    @RequestMapping("report_TopN_PopulatedCapitalCitiesInWorld")
    public List<City> report_TopN_PopulatedCapitalCitiesInWorld(@RequestParam(value = "N") int N) {
        if (N < 1) return null;
        //String query = "SELECT * FROM city ORDER BY population DESC LIMIT " + N;
        String query = "SELECT * " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * Retrives top N most populated capital cities within a given continent.
     * @param N number of items to retrive
     * @param continent continent name
     * @return List of top N populated capital cities in a given continent
     */
    @RequestMapping("report_TopN_PopulatedCapitalCitiesInContinent")
    public List<City> report_TopN_PopulatedCapitalCitiesInContinent(@RequestParam(value = "N") int N,@RequestParam(value = "continent") String continent) {
        if (N < 1 || continent == null || continent.isEmpty()) return null;
        String query = "SELECT * " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = '" + continent + "' ORDER BY city.population DESC LIMIT " + N;
        return getReport_City(query);
    }

    /**
     * Retrives top N most populated capital cities within a given region.
     * @param N number of items to retrive
     * @param region region name
     * @return List of top N populated capital cities in a given region
     */
    @RequestMapping("report_TopN_PopulatedCapitalCitiesInRegion")
    public List<City> report_TopN_PopulatedCapitalCitiesInRegion(@RequestParam(value = "N") int N,@RequestParam(value = "region") String region) {
        if (N < 1 || region == null || region.isEmpty()) return null;
        String query = "SELECT * " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = '" + region + "' ORDER BY city.population DESC LIMIT " + N ;
        return getReport_City(query);
    }

    /**
     * Retrives population breakdown (total, urban, rural) for all continents.
     */
    @RequestMapping("report_PopulationBreakdown_AllContinents")
    public List<PopulationBreakdown> report_PopulationBreakdown_AllContinents() {
        String query = "SELECT c.Continent, " +
                "SUM(c.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS UrbanPopulation, " +
                "(SUM(c.Population) - SUM(city.Population)) AS  RuralPopulation " +
                "FROM country c " +
                "LEFT JOIN city ON c.Code = city.CountryCode " +
                "GROUP BY c.Continent " +
                "ORDER BY TotalPopulation DESC";


        return getReport_PopulationBreakdown(query, "Continent");
    }

    /**
     * Retrives population breakdown (total, urban, rural) for all regions.
     */
    @RequestMapping("report_PopulationBreakdown_AllRegions")
    public List<PopulationBreakdown> report_PopulationBreakdown_AllRegions() {
        String query = "SELECT c.Region, " +
                "SUM(c.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS UrbanPopulation, " +
                "(SUM(c.Population) - SUM(city.Population)) AS RuralPopulation " +
                "FROM country c " +
                "LEFT JOIN city ON c.Code = city.CountryCode " +
                "GROUP BY c.Region " +
                "ORDER BY TotalPopulation DESC";

        return getReport_PopulationBreakdown(query, "Region");
    }

    /**
     * Retrives population breakdown (total, urban, rural) for all countries.
     */
    @RequestMapping("report_PopulationBreakdown_AllCountries")
    public List<PopulationBreakdown> report_PopulationBreakdown_AllCountries() {
        String query = "SELECT c.Name AS Country, " +
                "SUM(c.Population) AS TotalPopulation, " +
                "SUM(city.Population) AS UrbanPopulation, " +
                "(SUM(c.Population) - SUM(city.Population)) AS RuralPopulation " +
                "FROM country c " +
                "LEFT JOIN city ON c.Code = city.CountryCode " +
                "GROUP BY c.Name " +
                "ORDER BY TotalPopulation DESC";

        return getReport_PopulationBreakdown(query, "Country");
    }

    /**
     * Calculates and retrives the total population of the world.
     * @return total population of the world
     */
    @RequestMapping("report_TotalPopulation_World")
    public long report_TotalPopulation_World() {
        String query = "SELECT SUM(Population) AS TotalWorldPopulation FROM country";

        ResultSet rs = executeQuery(query);
        try{
            if(rs.next()){
                return rs.getLong("TotalWorldPopulation");
            }
        }catch (Exception ignored) {}
        return 0;
    }

    /**
     * Retrives total population within a given continent.
     * @param continent Name of the continent
     * @return total population within the given continent
     */
    @RequestMapping("report_TotalPopulation_Continent")
    public long report_TotalPopulation_Continent(@RequestParam(value = "continent") String continent) {
        if (continent == null || continent.isEmpty()) return 0;

        String query = "SELECT SUM(Population) AS TotalContinentPopulation " +
                "FROM country WHERE continent = '" + continent + "'";

        ResultSet rs = executeQuery(query);
        try{
            if(rs.next()){
                return rs.getLong("TotalContinentPopulation");
            }
        }catch (Exception ignored) {}
        return 0;
    }

    /**
     * Retrives total population within a given region.
     * @param region Name of the region
     * @return total population within the given region
     */
    @RequestMapping("report_TotalPopulation_Region")
    public long report_TotalPopulation_Region(@RequestParam(value= "region")String region) {
        if (region == null || region.isEmpty()) return 0;

        String query = "SELECT SUM(Population) AS TotalRegionPopulation " +
                "FROM country WHERE region = '" + region + "'";

        ResultSet rs = executeQuery(query);
        try{
            if(rs.next()){
                return rs.getLong("TotalRegionPopulation");
            }
        }catch (Exception ignored) {}
        return 0;
    }

    /**
     * Retrives total population within a given country.
     * @param country Name of the country
     * @return total population within the given country
     */
    @RequestMapping("report_TotalPopulation_Country")
    public long report_TotalPopulation_Country(@RequestParam(value = "country") String country) {
        if (country == null || country.isEmpty()) return 0;

        String query = "SELECT Population AS TotalCountryPopulation " +
                "FROM country WHERE Name = '" + country + "'";

        ResultSet rs = executeQuery(query);
        try {
            if (rs.next()) {
                return rs.getLong("TotalCountryPopulation");  // Return the total country population
            }
        } catch (Exception ignored) {}
        return 0;
    }

    /**
     * Retrives total population within a given district.
     * @param district Name of the district
     * @return total population within the given district
     */
    @RequestMapping("report_TotalPopulation_District")
    public long report_TotalPopulation_District(@RequestParam(value = "district") String district) {
        if (district == null || district.isEmpty()) return 0;

        String query = "SELECT SUM(Population) AS TotalDistrictPopulation " +
                "FROM city WHERE District = '" + district + "'";

        ResultSet rs = executeQuery(query);
        try {
            if (rs.next()) {
                return rs.getLong("TotalDistrictPopulation");
            }
        } catch (Exception ignored) {}
        return 0;
    }

    /**
     * Retrives total population within a given city.
     * @param city Name of the city
     * @return total population within the given city
     */
    @RequestMapping("report_TotalPopulation_City")
    public long report_TotalPopulation_City(@RequestParam(value = "city") String city) {
        if (city == null || city.isEmpty()) return 0;

        String query = "SELECT Population AS TotalCityPopulation " +
                "FROM city WHERE name = '" + city + "'";

        ResultSet rs = executeQuery(query);
        try{
            if (rs.next()){
                return rs.getLong("TotalCityPopulation");
            }
        }catch(Exception ignored){}
        return 0;
    }

    /**
     * Retrives a breakdown of spoken languages, including total speakers and percentage of world population
     */
    @RequestMapping("report_WorldLanguagesBreakdown")
    public List<Language> report_WorldLanguagesBreakdown(@RequestParam(value = "worldPopulation") long worldPopulation) {
        if(worldPopulation < 0) return null;

        String query = "SELECT cl.Language, SUM(c.Population * cl.Percentage / 100) AS TotalSpeakers " +
                "FROM country c " +
                "JOIN countrylanguage cl ON c.Code = cl.CountryCode " +
                "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                "GROUP BY cl.Language " +
                "ORDER BY TotalSpeakers DESC";

        return getReport_Language(query, worldPopulation);
    }

//</editor-fold>
    ///////////////////// UTILS AND DATABASE CONNECTIONS /////////////////////
//<editor-fold desc="SQL Connection UTILS">


    /**
     * Generic method to print ToString of all items of a given List
     * @param items List of ANY objects
     */
    public static <T> void print_Items(List<T> items) {
        if(items != null && !items.isEmpty()) {
            for (Object item : items) {
                System.out.println(item);
            }
        }
    }

    /**
     * Method to print specific columns for capital city reports.
     * @param cityList List of capital cities to be printed to screen.
     */
    public static void print_Items_Capitals(List<City> cityList){
        if(cityList != null && !cityList.isEmpty()) {
            for (City city : cityList) {
                System.out.println("City{" +
                                    "Name='" + city.Name + '\'' +
                                    "CountryCode='" + city.CountryCode + '\'' +
                                    ", Population='" + city.Population + '}');
            }
        }
    }




    /**
     * Executes the given query and returns a list of Country objects
     *
     * @param query SQL query to execute
     * @return List of Country objects resulting from the query
     */
    public static List<Country> getReport_Country(String query) {
        // Create ArrayList to hold Countries
        List<Country> countries = new ArrayList<>();

        // Execute query
        ResultSet rs = executeQuery(query);
        try {
            // Populate bjects from the ResultSet
            while (rs.next()) {
                // Extract the country details from the ResultSet
                String code = rs.getString("Code");
                String name = rs.getString("Name");
                String continent = rs.getString("Continent");
                String region = rs.getString("Region");
                double surfaceArea = rs.getDouble("SurfaceArea");
                int indepYear = rs.getObject("IndepYear") != null ? rs.getInt("IndepYear") : 0;
                int population = rs.getInt("Population");
                double lifeExpectancy = rs.getObject("LifeExpectancy") != null ? rs.getDouble("LifeExpectancy") : 0;
                double gnp = rs.getObject("GNP") != null ? rs.getDouble("GNP") : 0;
                double gnpOld = rs.getObject("GNPOld") != null ? rs.getDouble("GNPOld") : 0;
                String localName = rs.getString("LocalName");
                String governmentForm = rs.getString("GovernmentForm");
                String headOfState = rs.getString("HeadOfState");
                int capital = rs.getObject("Capital") != null ? rs.getInt("Capital") : 0;
                String code2 = rs.getString("Code2");

                // Create Country with extracted data
                Country country = new Country(code, name, continent, region, surfaceArea, indepYear, population, lifeExpectancy, gnp, gnpOld, localName, governmentForm, headOfState, capital, code2);

                // Add Country to List
                countries.add(country);
            }
        } catch (Exception ignored) {}

        // Return populated List of Countries
        return countries;
    }



    /**
     * Retrieves a list of cities based on the specified SQL query
     *
     * @param query SQL query to execute for retrieving city records
     * @return A list of City objects populated with data retrieved from the database
     */
    public static List<City> getReport_City(String query) {
        // Create List of Cities
        List<City> cities = new ArrayList<>();

        // Execute query
        ResultSet rs = executeQuery(query);
        try {
            // Populate objects from ResultSet
            while (rs.next()) {
                // Extract city details from ResultSet
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String countryCode = rs.getString("CountryCode");
                String district = rs.getString("District");
                int population = rs.getInt("Population");

                // Create City object
                City city = new City(id, name, countryCode, district, population);

                // Add to List
                cities.add(city);
            }
        } catch (Exception ignored) {}

        return cities;
    }


    /**
     * Retrives a list of population breakdown based on the specified SQL query
     * @param query SQL query to execute for retreiving population breakdowns objects
     * @param title The scope of the breakdown (Country, Continent, Region)
     * @return A list of PopulationBreakdown
     */
    public static List<PopulationBreakdown> getReport_PopulationBreakdown(String query, String title){
        if(query == null || query.isEmpty() || title == null || title.isEmpty()) return null;
        if(!title.equals("Country") && !title.equals("Continent") && !title.equals("Region")) return null;

        List<PopulationBreakdown> populationBreakdowns = new ArrayList<>();
        ResultSet rs = executeQuery(query);

        try {
            while (rs.next()) {
                String country = rs.getString(title);
                long totalPopulation = rs.getLong("TotalPopulation");
                long urbanPopulation = rs.getLong("UrbanPopulation");
                long ruralPopulation = rs.getLong("RuralPopulation");

                populationBreakdowns.add(new PopulationBreakdown(title, country, totalPopulation, urbanPopulation, ruralPopulation));
            }
        } catch (Exception ignored) {}
        return populationBreakdowns;
    }


    /**
     * Retrieves a list of language breakdown based on the specified SQL query
     * @param query SQL query to execute for retreiving language objects
     * @param worldPopulation The total world population value, used for calculating world percentage
     * @return A list of Language objects
     */
    public static List<Language> getReport_Language(String query, long worldPopulation){
        if(query == null || query.isEmpty() ) return null;

        List<Language> languages = new ArrayList<>();
        ResultSet rs = executeQuery(query);

        try {
            while (rs.next()) {
                // Language and total speakers
                String language = rs.getString("Language");
                double totalSpeakers = rs.getDouble("TotalSpeakers");
                double percentage = (totalSpeakers / worldPopulation) * 100;
                languages.add(new Language(language, totalSpeakers, percentage));
            }
        } catch (Exception ignored) {}
        return languages;
    }


    /**
     * Executes the given SQL query and returns the ResultSet.
     *
     * @param query the SQL query to execute
     * @return the ResultSet of the executed query
     */
    public static ResultSet executeQuery(String query){
        try {
            // Create a statement object to execute the query
            Statement stmt = con.createStatement();
            // Return ResultSet of executed query
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            // Handle SQL exception
            throw new RuntimeException(e);
        }
    }

    /**
     * Connection to MySQL database.
     */

    public static Connection con = null;

    public static void setConnection(Connection connection) {
        con = connection;
    }

    /**
     * Connect to the MySQL database.
     */
    public static void connect(String location, int delay) {
        if (location != null) {
            try {
                // Load Database driver
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Could not load SQL driver");
                System.exit(-1);
            }

            int retries = 10;
            boolean shouldWait = false;
            for (int i = 0; i < retries; ++i) {
                System.out.println("Connecting to database...");
                try {
                    if (shouldWait) {
                        // Wait a bit for db to start
                        Thread.sleep(delay);
                    }

                    // Connect to database
                    con = DriverManager.getConnection("jdbc:mysql://" + location
                                    + "/world?allowPublicKeyRetrieval=true&useSSL=false",
                            "root", "example");
                    System.out.println("Successfully connected");
                    break;
                } catch (SQLException sqle) {
                    System.out.println("Failed to connect to database attempt " + i);
                    System.out.println(sqle.getMessage());

                    // Let's wait before attempting to reconnect
                    shouldWait = true;
                } catch (InterruptedException ie) {
                    System.out.println("Thread interrupted? Should not happen.");
                }
            }
        }
        else {
            System.out.println("Failed to connect to database");
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public static void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
//</editor-fold>
}

