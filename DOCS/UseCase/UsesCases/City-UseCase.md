# USE CASE 1 :
### Produce a report on top 50 populated cities in a continent.

### Goal in Context:
As a researcher I want to create a report of the top 50 populated cities in a continent, so that I can compare these cities in terms of economic and cultural data.

### Scope and Level:
The scope is limited to retrieving and sorting city data by population from the database. 
The system provides the user with a list of 50 cities along with the data, such as population, name, and district.
The level is a Primary task.

### Preconditions:
The database stores the city records,including details such as name, population, area, country, and continent.

### Success End Condition:
An accurate report of the 50 most populated cities of the inputted continent are displayed from highest to lowest population, containing the Name, Country, Districts and Population of each city is generated.

### Failed End Condition:
No report is produced, or the information displayed is not correct.

### Primary Actor:
Researcher for the organisation.

### Trigger:
Pressing run on the program to produce the report.

### Main Success Scenario:
1. Researcher runs method which prints top N populated cities in a continent.
2. Researcher inputs the continent they want to produce a report of the cities in.
3. Researcher inputs "50" as the  number of cities they want to be printed in the report.
4. System finds the cities of the selected continent in database.
5. System organises the cities from most populated to least.
6. System outputs the top 50 cities and their details to user as a report including Name, Country, Districts and Population of each city.
7. The system ends run.

### Extensions:
3a. The number provided by user is out of bounds.
    5. The system returns an empty as a report._
    6. The system ends run.

_4a. The system cannot find the continent in the database.
    5. The system returns an empty as a report._
    6. The system ends run.

### Sub-variations:
1a. Researcher runs the method to view all cities in a country organised by population from largest to smallest.

1b. Researcher runs the method to view all cities in a region organised by  on by population from largest to smallest.

3c. Researcher inputs 100 as the number of cities to be printed in report.
### Schedule:
Release V0.1-alpha 4


