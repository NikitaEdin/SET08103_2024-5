# USE CASE 5:
### Generate a report of 

### Goal in Context
As a sociologist I want to create a report of the total population, people living in cities, and people not living in cities in each region, so that I can compare urbanization rates across different regions.

### Scope and Level:
The functionality of the application that retrieves data of population of regions from database.
The system allows the user to view the total population of regions along with relevant data, such as urban and rural populations.
The level is a primary task.

### Preconditions:
The database stores the population data ,including details such as urban and rural populations of each region.

### Success End Condition:
An accurate report of the population of each region organised by largest population to smallest is outputted.

### Failed End Condition:
No report is produced, or the information displayed is not correct.

### Primary Actor:
Sociologist for the organisation.

### Trigger:
Pressing run on the program to produce the report.

### Main Success Scenario:
1. Sociologist presses run method which prints all the populations of regions organised by largest population to smallest.
2. The system retrieves the population data for regions from the database.
3. The system outputs the population data as a report.
4. The system ends run.

### Extensions:
4a. The system cannot connect to the database.
    5.The system does not run query.
    6.The system ends run.

### Sub-variations:
1a.Sociologist presses run method which prints all the populations of countries organised by largest population to smallest. 
1b.Sociologist presses run method which prints the population data of the world organised by largest population to smallest.
### Schedule:
Release V0.1-alpha 5