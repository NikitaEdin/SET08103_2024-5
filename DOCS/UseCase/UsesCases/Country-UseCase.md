# USE CASE 3:
### Produce a report of all the countries in a region organised by largest population to smallest

### Goal in Context:
As a data analyst I want to display a report of all the countries in a region organised by largest population to smallest, so that I can deliver information about the populations of countries in a region.

### Scope and Level:
The functionality of the application that retrieves data of countries from database.
The system allows the user to view a list of all countries in a region along with relevant data, such as name, code, population, continent and region.
The level is a primary task.

### Preconditions:
The database stores the country records containing their relevant information.

### Success End Condition:
An accurate report of all the countries in a region organised by largest population to smallest is outputted.

### Failed End Condition:
No report is produced, or the information displayed is not correct.

### Primary Actor:
Data analyst for the organisation.

### Trigger:
Pressing run on the program to produce the report.

### Main Success Scenario:
1. Data analyst presses run method which prints all the countries in a region organised by largest population to smallest.
2. Data analyst inputs the region they want to produce a report of the countries in.
3. System finds the countries of the selected region in database.
4. System organises the countries from most populated to least.
5. System outputs all the countries and their details as a report.
6. The system ends run.


### Extensions:
3a. The system cannot find the region in the database (it must be invalid).
    5.The system returns an empty as a report.
    6.The system ends run.

### Sub-variations:
1a. Data analyst runs the method to view all countries in a continent organised by population from largest to smallest.
1b. Data analyst runs the method to view all countries in the world organised by population from largest to smallest.

### Schedule:
Release V0.1-alpha 4
