# LaMetric Displify
LaMetric time app based on AWS Lambda for displaying your currently playing Spotify track on your LaMetric time.

## Build
> Make sure you have [Maven installed](https://maven.apache.org/install.html)

Install Node, Serverless Framework and create deployable JAR:
```
mvn clean install
```

## Deploy
Setup your [AWS Credentials from IAM](https://docs.aws.amazon.com/de_de/IAM/latest/UserGuide/id_credentials_access-keys.html):
```
serverless config credentials --provider aws --key <AWS-KEY> --secret <AWS-SECRET>
```

Deploy `dev` Stage to AWS:
```
npm run deploy:dev
```

Deploy `prd` Stage to AWS:
```
npm run deploy:prd
```
Or deploy with ease using your Jenkins and the `Jenkinsfile` in this repo.
