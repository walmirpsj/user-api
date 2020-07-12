# Read Me First
run tests: mvn clean verify sonar:sonar

report jacoco: /user-api/target/site/jacoco/index.html


SonarQube configuration:

sudo docker pull sonarqube
sudo docker run -d -p 9000:9000 sonarqube
