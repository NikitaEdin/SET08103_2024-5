# USE CASE 4:
### Produce a report of languages ordered by number of global speakers, from the greatest number to smallest.

### Goal in Context:
As a linguist I want to create a report of the number of people who speak chinese, english, hindi, spanish and arabic from the greatest number to smallest, including the percentage of the world population so that I can analyse language metrics.

### Scope and Level:
The scope is limited to retrieving and sorting language data by global speakers from the database.
The system provides the user with a list the languages along with the data, name, number of people who speak it and percentage of population.
The level is a Primary task.

### Preconditions:
The database stores the language records,including details such as name, number of people who speak it and percentage of population.

### Success End Condition:
An accurate report of the languages (chinese, english, hindi, spanish and arabic) sorted by the greatest number of global speakers to the least is outputted.
Including the information of the name of the language, number of global speakers and percentage of the world population. 

### Failed End Condition:
No report is produced, or the information displayed is not correct.

### Primary Actor:
Linguist for the organisation.

### Trigger:
Pressing run on the program to produce the report.

### Main Success Scenario:
1. The linguist presses run language report method.
2. The system retrieves the language data from the database.
3. The system outputs the language data as a report.
4. The system ends run.

### Schedule:
Release V0.1-alpha 5
