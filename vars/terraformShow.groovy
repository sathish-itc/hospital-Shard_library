def call() {
    dir('terraform') {
        withCredentials([
            file(credentialsId: 'GCP_SERVICE_ACCOUNT_KEY', variable: 'GCP_KEY_FILE')
        ]) {
            sh '''
                export GOOGLE_APPLICATION_CREDENTIALS=$GCP_KEY_FILE
                terraform init
                terraform plan -out=tfplan
                terraform show -json tfplan
                terraform output
            '''
        }
    }
}
