# Use Case: 02 Produce a City Report
# Information
## Goal
The organisation wants a report on Cities with the report containing: Name, Country, District, Population of each City in the database.
The report will have these: All the cities in the world organised by largest population to smallest, All the cities in a continent organised by largest population to smallest, All the cities in a region organised by largest population to smallest, All the cities in a country organised by largest population to smallest, All the cities in a district organised by largest population to smallest.
The company wants this data contained within the report of City: The top N populated cities in the world where N is provided by the user, The top N populated cities in a continent where N is provided by the user, The top N populated cities in a region where N is provided by the user, The top N populated cities in a country where N is provided by the user. The top N populated cities in a district where N is provided by the user.
## Scope
City Database
## Level
Primary Task
## Preconditions
That the Cities are stored in the databases with their information
## Success condition
A report is produced to the organisation.
## Failed condition
No report is produced to the organisation.
## Primary Actor
Organisation.
## Trigger
Pressing run on the program to produce the report.
# Main Success Scenario
1. Organisation presses run on the program.
2. System reads the database and completes the query and produces the report.
3. Organisation gets displayed the city report.
# Extensions
No City database found.
User input out of bounds.
# Sub-variations
None.
# Schedule
Release 1.0