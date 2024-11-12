pipeline {
    agent any
    
    tools {
        jdk 'java17'
        maven 'maven'
    }
    environment {
        // Define environment variables
        BRANCH_NAME = "${env.GIT_BRANCH}"
        TOMCAT_SERVER = "http://192.168.0.113:8080"
        TOMCAT_USER = "admin"
        TOMCAT_PASSWORD = "Moh123\$\$" 
        TOMCAT_DEPLOY_PATH = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0'
    }
    stages {

        
        stage('Test') {
            steps {
                echo "JUnit Test in Progress"
                bat "mvn test"
                echo "JUnit Test Finished"
            }
        }

        stage('Build JAVA WAR File for deployment') {
            steps {
                echo "Building WAR File"
                bat "mvn clean package -DskipTests"
                echo "Building WAR File Completed"
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                script {
                    echo "Target WAR Path: target\\*.war"
                    echo "Tomcat Deployment Path: ${TOMCAT_DEPLOY_PATH}\\webapps\\"
                    bat 'copy "target\\*.war" "%TOMCAT_DEPLOY_PATH%\\webapps\\"'
                    echo "Deployed the .war File"
                }
            }
        }

    }
}
