# Use Case: 03 Produce a Capital City Report
# Information
## Goal
The organisation wants a report on Capital Cities with the report containing: Name, Country, Population of each Capital City in the database.
Additional reports are required to be generated these are: All the capital cities in the world organised by largest population to smallest, All the capital cities in a continent organised by largest population to smallest, All the capital cities in a region organised by largest to smallest.
They also want these reports: The top N populated capital cities in the world where N is provided by the user, The top N populated capital cities in a continent where N is provided by the user, The top N populated capital cities in a region where N is provided by the user.
## Scope
Capital City Database
## Level
Primary Task
## Preconditions
That the Capital Cities are stored in the databases with their information
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
2. System reads the database and completes a query to produces the report.
3. Organisation gets displayed the Capital City report.
# Extensions
No Capital City database found.
Number provided by the user is out of bounds.
# Sub-variations
None.
# Schedule
Release 1.0