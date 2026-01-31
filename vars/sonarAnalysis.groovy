def call() {
    script {
        def scannerHome = tool 'SonarScanner'
        withSonarQubeEnv('SonarQube') {
            sh """
                ${scannerHome}/bin/sonar-scanner \
                  -Dsonar.projectKey=hospital-project \
                  -Dsonar.sources=frontend-api,patient-api,appointment-api \
                  -Dsonar.exclusions=**/node_modules/**,**/dist/**,**/build/** \
                  -Dsonar.host.url=http://20.75.196.235:9000 \
                  -Dsonar.token=\$SONAR_AUTH_TOKEN
            """
        }
    }
}
