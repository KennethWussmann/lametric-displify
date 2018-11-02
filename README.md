# LaMetric Displify
LaMetric time app based on AWS Lambda for displaying your currently playing Spotify track on your LaMetric time.

## Build
Install Serverless Framework and create deployable JAR
```
mvn clean install
```

## Deploy
Deploy `dev` Stage to AWS
```
npm run deploy:dev
```

Deploy `prd` Stage to AWS
```
npm run deploy:prd
```
Or deploy with ease using your Jenkins and the `Jenkinsfile` in this repo.