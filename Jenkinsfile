pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'JDK8'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -Pfrontend-clean'
                archiveArtifacts 'target/*.jar'
            }
        }
        stage('Deploy') {
            if (env.BRANCH_NAME == 'develop') {
                steps {
                    echo "Deploying on stage: dev"
                    withCredentials([
                        string(credentialsId: 'aws-deploy-key-id', variable: 'AWS_ACCESS_KEY_ID'),
                        string(credentialsId: 'aws-deploy-key', variable: 'AWS_SECRET_ACCESS_KEY')
                    ]) {
                        sh 'npm run deploy:dev'
                    }
                }
            } else if (env.BRANCH_NAME == 'master') {
                steps {
                    echo "Deploying on stage: prd"
                    withCredentials([
                        string(credentialsId: 'aws-deploy-key-id', variable: 'AWS_ACCESS_KEY_ID'),
                        string(credentialsId: 'aws-deploy-key', variable: 'AWS_SECRET_ACCESS_KEY')
                    ]) {
                        sh 'npm run deploy:prd'
                    }
                }
            }
        }
    }
}