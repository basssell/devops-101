pipeline {
    agent any

    tools {
        maven '3.3.1'  // Replace 'YOUR_MAVEN_VERSION' with the correct Maven version configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
            }
        }

        // If you have deployment steps, make sure to include them in the 'Deploy' stage.
        // If not, you can remove this stage.
        stage('Deploy') {
            steps {
                echo "deploying step"
                // Your deployment steps here.
            }
        }
    }

    post {
        always {
            junit '**/target/test-*.xml'
        }

        failure {
            echo "Build failed. Please check the logs for more information."
        }
    }
}

