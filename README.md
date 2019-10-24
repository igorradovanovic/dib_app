# Project Name
> This Project is example of consuming RestApi with HTTPS!

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [System Requirements](#system-requirements)
* [Setup](#setup)
* [Features](#features)
* [Contact](#contact)


## General info
This Application in RestFull Web Service. Data are provided throw RestApi 
`https://api.punkapi.com/v2/beers/random`

## Technologies
* SpirngBoot - version 2.2.0.RELEASE

## System Requirements
* Apache Maven 3.5.4
* JDK 1.8.0

## Setup
Run "runProject.bat". This script will start up this Project. All dependencies and build will be executed running this file. 
** Max Allowed Connections for free mySql DB is 1. 

## Test Example
Test this Application with POSTMAN. Download from this 
`https://www.getpostman.com/downloads/`

## Features
1. This Application is secured throw Spring Security. UserDetailesService interface has been implemented.
To log in and test this App with PostMan setup Basic Auth and use:
username: admin
password: 2910

1. To load all beers make http request GET 
`http://localhost:8080/api/beers` 
* List of 10 or less beers will be displayed.
2. To DELETE Beer By ID http request DELETE 
`http://localhost:8080/api/beers/{id}`
* Selected row will be deleted.
3. To FILL UP beer table make http request POST 
`http://localhost:8080/api/fillUpBeers`
* This feature provied filling up beer table up to max 10 different beers.

## Contact
Created by [@igor.radovanovic] email:i.radovanovic@sbb.rs