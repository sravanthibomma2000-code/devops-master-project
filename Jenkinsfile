pipeline {
    agent any

    tools {
        jdk 'java21'
        maven 'maven3'
    }

    environment {
        SCANNER_HOME = tool 'SonarScanner'
        IMAGE_NAME = "sravanthibomma2000/iot-platform"
        IMAGE_TAG = "${BUILD_NUMBER}"
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

        stage('Build Docker Image') {
            steps {
                dir('app/iot-platform') {
                    sh """
                    docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
                    docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${IMAGE_NAME}:latest
                    """
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {

                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    docker push '"${IMAGE_NAME}:${IMAGE_TAG}"'
                    docker push '"${IMAGE_NAME}:latest"'
                    docker logout
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "=================================="
            echo "CI/CD Pipeline Completed Successfully!"
            echo "Docker Image: ${IMAGE_NAME}:${IMAGE_TAG}"
            echo "=================================="
        }

        failure {
            echo "=================================="
            echo "Pipeline Failed!"
            echo "Check Console Output."
            echo "=================================="
        }

        always {
            cleanWs()
        }
    }
}
