def call(String projectId, String cluster, String zone) {

    withCredentials([
        file(credentialsId: 'GCP_SERVICE_ACCOUNT_KEY', variable: 'GCP_KEY_FILE')
    ]) {
        sh '''
            export PATH=/home/swathireddy73/google-cloud-sdk/bin:$PATH
            export USE_GKE_GCLOUD_AUTH_PLUGIN=True

            gcloud auth activate-service-account --key-file="$GCP_KEY_FILE"
            gcloud config set project ''' + projectId + '''
            gcloud container clusters get-credentials ''' + cluster + ''' --zone ''' + zone + '''

            kubectl get nodes
        '''
    }
}
