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
        TOMCAT_PASSWORD = "Moh123\$\$"  // Use Jenkins credentials for sensitive info
        TOMCAT_DEPLOY_PATH = 'C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0'
    }
    stages {
        // stage('Git Checkout') {
        //     steps {
        //         echo "Cehckout branch ${BRANCH_NAME}"
        //         git scm
        //         echo "Checkout Completed"
        //     }
        // }
        
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
            // Debugging step to print the paths
            echo "Target WAR Path: target\\*.war"
            echo "Tomcat Deployment Path: ${TOMCAT_DEPLOY_PATH}\\webapps\\"
            // Proceed with the deployment
           bat 'copy "target\\*.war" "%TOMCAT_DEPLOY_PATH%\\webapps\\"'
        }
    }
}


    }
}
