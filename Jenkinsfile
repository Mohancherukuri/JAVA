pipeline {
    agent any
    
    tools {
        jdk 'jdk11'
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
        // stage('Git Checkout') {
        //     steps {
        //         git branch: 'main', credentialsId: '98e2ce7d-24b8-41fa-9440-6a8f37ced8b1', url: 'https://github.com/sojutta/DevOpsCaseStudyJavaMaven'
        //     }
        // }
        stage('Build WAR') {
            steps {
                bat "mvn clean package -DskipTests"
            }
        }
        stage('Test') {
            steps {
                bat "mvn test"
            }
        }
        stage('Deploy to Tomcat') {
            steps {
                script {
                    bat 'copy "target\\*.war" "%CATALINA_HOME%\\webapps\\"'
                    //bat 'call "%CATALINA_HOME%\\bin\\shutdown.bat"'
                    //sleep(time: 20, unit: 'SECONDS')
                    //bat 'call "%CATALINA_HOME%\\bin\\startup.bat"'
                    //sleep(time: 20, unit: 'SECONDS')
                    //bat "net stop Tomcat9"
                    //bat "net start Tomcat9"
                }
                
            }
        }
        stage('Restart Tomcat') {
            steps {
                script {
                    bat "net stop Tomcat9"
                    bat "net start Tomcat9"
                }
                
            }
        }
    }
}
