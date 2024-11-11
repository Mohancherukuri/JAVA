pipeline {
    agent any
    
    tools {
        jdk 'jdk17'
        maven 'maven'
    }
    environment {
        // Define environment variables
        BRANCH_NAME = "${env.GIT_BRANCH}"
        TOMCAT_SERVER = "http://192.168.0.113:8080"
        TOMCAT_USER = "admin"
        TOMCAT_PASSWORD = "Moh123\$\$"  // Use Jenkins credentials for sensitive info
        TOMCAT_DEPLOY_PATH = "'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0"  // Ensure the correct path format
    }

    stages {
        stage('Git Checkout') {
            steps {
                git scm
            }
        }
        
        stage('Test') {
            steps {
                bat "mvn test"
            }
        }

        stage('Build WAR') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    bat 'copy "target\\*.war" "TOMCAT_DEPLOY_PATH\\webapps\\"'
                }
                
            }
        }

    }
}
