def call() {
    sh '''
        export PATH=/home/swathireddy73/google-cloud-sdk/bin:$PATH
        export USE_GKE_GCLOUD_AUTH_PLUGIN=True

        echo "✅ Verifying gcloud & GKE auth plugin"
        gcloud version
        gke-gcloud-auth-plugin --version
        kubectl version --client
    '''
}
