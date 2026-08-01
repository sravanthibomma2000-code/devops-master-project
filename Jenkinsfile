pipeline {
    agent any

    tools {
        jdk 'java21'
        maven 'maven3'
    }

    environment {
        SCANNER_HOME = tool 'SonarScanner'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/sravanthibomma2000-code/devops-master-project.git'
            }
        }

        stage('Build') {
            steps {
                dir('app/iot-platform') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    dir('app/iot-platform') {
                        sh """
                        \$SCANNER_HOME/bin/sonar-scanner \
                        -Dsonar.projectKey=IoT-Platform \
                        -Dsonar.projectName=IoT-Platform \
                        -Dsonar.sources=src/main \
                        -Dsonar.java.binaries=target/classes
                        """
                    }
                }
            }
        }
    }
}
