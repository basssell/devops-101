pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm  // This checks out the code from your SCM (e.g., GitHub).
            }
        }

        stage('Build and Test') {
            steps {
                script {
                    // Here we're using the 'sh' step because it's a Unix-based command.
                    // Use 'bat' step if you're running Jenkins on a Windows machine.
                    sh 'mvn clean package'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.war', allowEmptyArchive: true
            }
        }

        stage('Deploy') {
            steps {
                // This is just a placeholder. Actual deployment will vary based on your application server and setup.
                echo "Deploying application..."
            }
        }
    }

    post {
        failure {
            // Notify developers/team about the failure. You can integrate with tools like Slack, email, etc.
            echo "Build failed!"
        }
        success {
            echo "Build succeeded!"
        }
    }
}
