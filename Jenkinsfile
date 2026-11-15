pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out ShopSphere source code'
                checkout scm
            }
        }

        stage('Build Discovery Server') {
            steps {
                dir('discovery-server') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Product Service') {
            steps {
                dir('product-service') {
                    sh 'chmod +x mvnw'
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Success') {
            steps {
                echo 'ShopSphere build completed successfully!'
            }
        }
    }
}