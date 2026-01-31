def call(String tag) {

    withCredentials([
        usernamePassword(
            credentialsId: 'sathish33',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {
        sh '''
            export PATH=/home/swathireddy73/google-cloud-sdk/bin:$PATH
            export USE_GKE_GCLOUD_AUTH_PLUGIN=True

            kubectl create namespace hospital --dry-run=client -o yaml | kubectl apply -f -

            kubectl create secret docker-registry dockerhub-secret \
              --docker-server=index.docker.io \
              --docker-username=$DOCKER_USER \
              --docker-password=$DOCKER_PASS \
              --namespace hospital \
              --dry-run=client -o yaml | kubectl apply -f -

            kubectl apply -f mysql/deployment.yaml

            helm upgrade --install appointment appointment-api/helm \
              --namespace hospital \
              --set image.tag=''' + tag + '''

            helm upgrade --install patient patient-api/helm \
              --namespace hospital \
              --set image.tag=''' + tag + '''

            helm upgrade --install frontend frontend-api/helm \
              --namespace hospital \
              --set image.tag=''' + tag + '''
        '''
    }
}
