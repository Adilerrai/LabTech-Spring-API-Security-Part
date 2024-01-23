pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'Developer', url: 'https://github.com/hasnaou/LabTech-Spring-API/'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean'

            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}

