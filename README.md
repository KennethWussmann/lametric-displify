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

## Host yourself
You can easily host Displify yourself as your own LaMetric Time App on AWS. 
Even for free in the AWS trial contingent.

Follow these steps:
* [Create an AWS Account](https://aws.amazon.com/en/free/)
* [Download or clone this Code](https://github.com/KennethWussmann/lametric-displify/archive/master.zip)
* Build and deploy using instructions above
* Login to [LaMetric Developer Portal](https://developer.lametric.com/) and [create an Indicator App](https://developer.lametric.com/applications/createdisplay)
* At the End of the deploy step you'll see a url to use for your Indecator LaMetric App:
    ```YAML
    # Example
    api keys:
      None
    endpoints:
      GET - https://example123.execute-api.eu-central-1.amazonaws.com/dev/displify
    functions:
      displify: lametric-displify-dev-displify
    ```