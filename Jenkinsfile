pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install -Pfrontend-clean'
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([string(credentialsId: 'aws-deploy-key-id', variable: 'AWS_ACCESS_KEY_ID'), string(credentialsId: 'aws-deploy-key', variable: 'AWS_SECRET_ACCESS_KEY')]) {
                    sh 'sls deploy --stage dev'
                }
            }
        }
    }
}