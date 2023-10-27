pipeline {
    agent any  // This will run the pipeline on any available agent

    tools {
        maven 'Maven 3.3.2'  // Make sure this Maven version/name matches what you've configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm  // This checks out the code from your SCM (e.g., GitHub)
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean package'  // This will now use the Maven version specified above
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
            }
        }

        stage('Deploy') {
            steps {
                // Your deployment steps here. Depending on where and how you deploy.
            }
        }
    }

    post {
        always {
            // Actions to always perform after stages
            junit '**/target/test-*.xml'  // Capture any test results if you have tests
        }

        failure {
            // Actions to perform on build failure
            echo "Build failed. Please check the logs for more information."
        }
    }
}

