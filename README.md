# NewsSpring

This project was an interview excercise, for which the 'project instructions' and 'how to submit' are included below. It is required to integrate with a News API, and is required to use the Spring Boot framework, so I called it NewsSpring.

Because of the extremely simple nature of the code's logic (there are hardly any execution branches), rather than spending more of my time writing unit tests, I decided to implement a very basic DI/CD pipeline. I created an AWS Elastic Container Registry which the project pushes built docker images to in GitHub actions, and an AWS Elastic Container Service cluster, which the project deploys it's code to, and can be tried here (it's routed through an application load balancer to produce the given URI):
http://newsspringloadbalancer-42707299.us-west-1.elb.amazonaws.com

See the 'available endpoints' section for API usage directions.

You can check out the GitHub Actions that have been building and deploying this application here, but you won't have able to see the logs as a public user:
https://github.com/dpapajohn/NewsSpring/actions

Caching is achieved, as requested, but is done with an in-memory cache. This means that each instance of the application running in a cluster has it's own cache, so identical calls will result in a cache hit if the load balancer routes them to the same instance, but a cache miss if the load balancer routes to a different instance. You could use a distributed and/or L2 cache, or possibly with good enough session affinity, the effects of this could be ameliorated.

## Project instructions

Your task is to create a simple REST API framework that interacts with a public news API for fetching articles. For the back-end, you have to use the Java language and Spring framework. For example, you can use the GNews API and then create your own API service, with documentation, that interacts with this API for fetching articles.

You API should have a few basic methods like, fetching N news articles, finding a news article with a specific title or author, and searching by keywords. Include a cache in your API service as well so users are not fetching the same things over and over.

## How to submit

Upload your completed project to your GitHub, and then paste a link to the repository below in the form along with any comments you have about your solution. Please include instructions on how to run the application in your README.md.

## How to run

You will need to use the application-EXAMPLE.properties file to create an application.properties file with the api key for the NewsAPI.org API filled out. Note that in the deployment, I am using an environment variable injected by AWS ECS, via the `taskDefinition.json` file. For local development, I would suggest simply putting your API key into the application.properties file (which I have added to the `.gitignore` file to avoid accidentally checking in secrets).

Use the example file as a template here:
`/src/main/resources/application-EXAMPLE.properties`

to create the application.properties file here:
`/src/main/resources/application.properties`

To run locally, you will need the JDK, maven, and your choice of spring boot IDE installed. I used VSCode with these following extensions installed.

Name: Spring Boot Extension Pack
VS Marketplace Link: https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack

Name: Extension Pack for Java
VS Marketplace Link: https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack

You should be able to run the project on your development machine with these things installed, by opening the project folder, and hitting F5 (this is the shortcut for *Run > Start Debugging*) while in the `src\main\java\com\papajohn\NewsSpring\NewsSpringApplication.java` file, for instance (any java file should do). This will lauch the project in debug mode, and you should have the webserver running locally on `localhost:80`. You should be able to confirm in the Spring Boot Dashboard extension of VSCode that your application is running, and even launch a simple browser isntance within VSCode.

You should be able to see a basic hello world health check on the root path when the application is running. 
http://localhost:80/

Additionally, I have written a simple Dockerfile, so you can skip maven, the jdk, and all of that by having docker installed, and using these two commands:
`docker build -t newsapi -f Dockerfile .`
`docker run -p 80:80 newsapi:latest`
Again, this should allow you to access the application at http://localhost:80/

I have also deployed the web service to AWS ECS, and it is running at this URI, so you can try it out without running it yourself:
http://newsspringloadbalancer-42707299.us-west-1.elb.amazonaws.com

## Available endpoints

Keep in mind, this project is using the NewsApi.org APIs.
https://newsapi.org/docs/endpoints/everything

https://newsapi.org/docs/endpoints/top-headlines

https://newsapi.org/docs/endpoints/sources

### News Articles by Source

/api/articles/source/{source}

You can get all of the articles from a given source with this API. Note that the newsapi sources API mentioned above can list all of the sources for you, or you can look at their online documentation. https://newsapi.org/sources

Examples:

http://localhost:80/api/articles/source/CNN

http://newsspringloadbalancer-42707299.us-west-1.elb.amazonaws.com/api/articles/source/CNN


### News Articles by Keyword Search

/api/articles/keyword/{keyword}

You can search news articles by keyword. This will search the article contents, description, and title for your keyword.

Examples:

http://localhost:80/api/articles/keyword/apple

http://newsspringloadbalancer-42707299.us-west-1.elb.amazonaws.com/api/articles/keyword/apple

### News Articles by Title

/api/articles/title/{title}

You can search news articles by title. This works like a keyword search, but is limited to the title.

Example:

http://localhost:80/api/articles/title/grapes

http://newsspringloadbalancer-42707299.us-west-1.elb.amazonaws.com/api/articles/title/grapes

### Top News Articles

/api/articles/top/{country}/all
or
/api/articles/top/{country}/{category}

You can get the top articles for your country, or get the top articles for your country in only a certain category. Check the documentation for a comprehensive list of countries supported, and categories supported. https://newsapi.org/docs/endpoints/top-headlines 

Note that if you do not want to filter by category, you must pass 'all' as the category.

Examples:

http://localhost:80/api/articles/top/us/all

http://localhost:80/api/articles/top/us/science


http://newsspringloadbalancer-42707299.us-west-1.elb.amazonaws.com/api/articles/top/us/all

http://newsspringloadbalancer-42707299.us-west-1.elb.amazonaws.com/api/articles/top/us/science
