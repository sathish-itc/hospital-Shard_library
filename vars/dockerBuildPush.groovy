def call(String registry, String tag) {

    withCredentials([
        usernamePassword(
            credentialsId: 'sathish33',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {
        sh '''
            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
        '''

        def images = [
            [dir: 'frontend-api',    name: "${registry}/frontend_api_image"],
            [dir: 'patient-api',     name: "${registry}/patient_api_image"],
            [dir: 'appointment-api', name: "${registry}/appointment_api_image"]
        ]

        images.each {
            sh """
                docker build -t ${it.name}:${tag} ${it.dir}
                docker push ${it.name}:${tag}
            """
        }
    }
}
