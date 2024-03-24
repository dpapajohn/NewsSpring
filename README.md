# NewsSpring

## Project instructions

Your task is to create a simple REST API framework that interacts with a public news API for fetching articles. For the back-end, you have to use the Java language and Spring framework. For example, you can use the GNews API and then create your own API service, with documentation, that interacts with this API for fetching articles.

You API should have a few basic methods like, fetching N news articles, finding a news article with a specific title or author, and searching by keywords. Include a cache in your API service as well so users are not fetching the same things over and over.

## How to submit

Upload your completed project to your GitHub, and then paste a link to the repository below in the form along with any comments you have about your solution. Please include instructions on how to run the application in your README.md.

## Test and Deploy

Use the built-in continuous integration in GitLab.

- [ ] [Get started with GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/index.html)
- [ ] [Analyze your code for known vulnerabilities with Static Application Security Testing (SAST)](https://docs.gitlab.com/ee/user/application_security/sast/)
- [ ] [Deploy to Kubernetes, Amazon EC2, or Amazon ECS using Auto Deploy](https://docs.gitlab.com/ee/topics/autodevops/requirements.html)
- [ ] [Use pull-based deployments for improved Kubernetes management](https://docs.gitlab.com/ee/user/clusters/agent/)
- [ ] [Set up protected environments](https://docs.gitlab.com/ee/ci/environments/protected_environments.html)

## How to run

You will need to use the application-EXAMPLE.properties file to create an application.properties file with the api key for the NewsAPI.org API filled out.

Use the example file as a template here:
`/src/main/resources/application-EXAMPLE.properties`

to create the application.properties file here:
`/src/main/resources/application.properties`

To run locally, you will need the JDK, maven, and your choice of spring boot IDE installed. I used VSCode with these following extensions installed.

Name: Spring Boot Extension Pack
VS Marketplace Link: https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack

Name: Extension Pack for Java
VS Marketplace Link: https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack

You should be able to run the project on your development machine with these things installed, by opening the project folder, and hitting F5 (this is the shortcut for *Run > Start Debugging*) while in the `src\main\java\com\papajohn\NewsSpring\NewsSpringApplication.java` file, for instance (any java file should do). This will lauch the project in debug mode, and you should have the webserver running locally on `localhost:8080`. You should be able to confirm in the Spring Boot Dashboard extension of VSCode that your application is running, and even launch a simple browser isntance within VSCode.

You should be able to see a basic hello world health check on the root path when the application is running. 
http://localhost:8080/

## Available endpoints
### News Articles by Source
http://localhost:8080/api/articles/source/CNN
### News Articles by Title
http://localhost:8080/api/articles/top/us/all
### News Articles by Keyword Search
http://localhost:8080/api/articles/keyword/apple
### Top News Articles
http://localhost:8080/api/articles/top/us/all
