# USE CASE 2:
### Produce a report of all the capital cities in a continent organised by largest population to smallest.

### Goal in Context:
As a database technician I want to create a report of all the capital cities in a continent organised by largest population to smallest, so that I can ensure the database system is producing correct result.

### Scope and Level:
The functionality of the application that retrieves data of capital cities from database.
The system allows the user to view a list of all capital cities in a continent along with relevant data, such as name, population and country.
The level is a primary task.

### Preconditions:
The database stores the city records,including details such as name, population, area, country, and continent.
The database stores the country records containing capital cities for each as city codes.

### Success End Condition:
An accurate report of all the capital cities in a continent organised by largest population to smallest is outputted.

### Failed End Condition:
No report is produced, or the information displayed is not correct.

### Primary Actor:
Database technician for the organisation.

### Trigger:
Pressing run on the program to produce the report.

### Main Success Scenario:
1. Database technician presses run method which prints all the capital cities in a continent organised by largest population to smallest.
2. Database technician inputs the continent they want to produce a report of the capital cities in.
3. System joins the city and country tables in database, ensuring that only records where the city.ID matches country.Capital are selected. 
   This filters the results to only include capital cities.
4. System finds the cities of the selected continent in database.
5. System organises the cities from most populated to least.
6. System outputs all the capital cities and their details to database technician as a report.
7. The system ends run.

### Extensions:
4a. The system cannot find the continent in the database.
   5.The system returns an empty as a report.
   6.The system ends run.

### Sub-variations:
1a. Database technician runs the method to view all capital cities in the world organised by population from largest to smallest.

### Schedule:
Release V0.1-alpha 4