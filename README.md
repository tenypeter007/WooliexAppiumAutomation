# Wooliesx Appium
This is a google calender appium test automation project for android 

## Getting Started 

1. Install node js https://nodejs.org/en/download/
2. Install appium globally
*npm i -g appium*
3. Clone the repo
4. Change the device capabilities accordingly in app-config.properties.properties 
5. Resolve maven dependencies 

## Run

1. *mvn clean install test* for  running all scenarios 
2. *mvn clean test  -Dcucumber.options="--tags @meeting"*  for running specific scenarios 

## Report

Once execution is completed default cucumber report will be generated inside target/cucumber-html-reports



