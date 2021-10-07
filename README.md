# Description
The Challenge has been implemented in GRAILS version 4.0.12

there is the loading thanks to TALEND Open Studio (Data Integration) version 6.2.1

the following Github repo contains the grails code, the talend job and the talend project

the datastore chosen for the challenge is postgresql v9+
the db stuff and related are in *files* directory
---
First create a *adverity* database
by using the following command in psql:

*create database adverity;*

then

you shall do : *psql -U postgres -d adverity < init.sql*

It will create 3 tables :
- one for the *analytics*
- one for the *campaign*
- one for the *datasource*

there are relationships from last 2 to *analytics*

---

As for the loading you have 2 choice:
either by *adverity.sql* (in *files* directory) or launch the talend jobs contained in *files/jobs/adverity*
as following

*./create_ds_run.sh*

then

*./create_campaign_run.sh*

and finally *./create_main_run.sh*

as for grails you can just do a *grails run-app*

# API Calling
The API can be called at:
http://localhost:8080/central
you can supply parameters to the API call
## Basic Query
you can add columns to be queried by supplying the following to the basic URI

*selectedCols=campaign,datasource,daily,clicks,impressions*

## Aggregated Query
for aggregated query (max,min,avg,sum) you can use following parameter
You shall add also a groupBy parameter as following:

*groupBy=campaign,datasource*

The following are available

*maxCols=daily,clicks,impressions*

*maxCols=daily,clicks,impressions*

*sumCols=daily,clicks,impressions*

*avgCols=daily,clicks,impressions*

## Date Query
NOT DONE 
see TODO

## Sample queries

http://localhost:8080/central?selectedCols=campaign,datasource&groupBy=campaign,datasource&sumCols=clicks,impressions
will query the sums of clicks and impressions grouped by campaign
# TODO
the API should be normalized for adding the columns to the result value
e.g. {'datasource':'whatever','sum(clicks)':32}
by lack of time I did not implement the date query but it should not be hard to add a parameter to the query 
for the range between dates

One can select (where clause) the datasource and campaigns : it should not be difficult to do so but design has to 
be carefully done because the table for these are described by an *id* and a *value*
So would be easy to add a parameter for the whereClause by adding such as this:
*?campaign=MY_CAMPAIGN_CODE*
because spaces in parameters can be subject to errors
