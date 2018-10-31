# LaMetric Displify
LaMetric time app based on AWS Lambda for displaying your currently playing Spotify track on your LaMetric time.

## Build
Create deployable JAR
```
mvn clean install
```
## Deploy
> Make sure you have [Serverless Framework](https://serverless.com/framework/docs/providers/aws/guide/installation/) installed

Deploy to AWS
```
sls deploy
```