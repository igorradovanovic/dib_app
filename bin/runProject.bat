@ECHO OFF 
:: This batch file download dependencies, build and run the Project.
TITLE DibApp-DOWNLOAD DEPS - BUILD - RUN
pause
echo DOWNLOAD DEPS, BUILD AND RUN THE PROJECTS
call mvn clean install
echo mavenClean
call mvn clean package
echo mavenPackage
java -jar target\dib_app-0.0.1-SNAPSHOT.jar
echo running dib_app-0.0.1-SNAPSHOT.jar
pause