pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                npm ci
                mvn clean install
            }
        }
        stage('Test') {
            steps {
                mvn test
            }
        }
        stage('Deploy') {
            steps {
                sls deploy --stage dev
            }
        }
    }
}