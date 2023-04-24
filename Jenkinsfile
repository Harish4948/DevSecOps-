pipeline {
  agent any

  stages {
      stage('Build Artifact') {
            steps {
              sh "mvn clean package -DskipTests=true" //s
              archive 'target/*.jar'
            }
        }   
      stage('Unit Tests') {
            steps {
              sh "mvn test"
            }
              post { 
                always { 
                  junit 'target/surefire-reports/*.xml'
                  jacoco execPattern: 'target/jacoco.exec'
                }
              }
        } 
          stage('Docker Build and push') {
            steps {
              withDockerRegistry([credentialsId:"docker-hub", url: ""]){
              sh "printenv"
              sh 'docker build -t harish4948/numeric-app:""$GIT_COMMIT"" .'
              sh 'docker push harish4948/numeric-app:""$GIT_COMMIT""'
              }}
        } 
    }
}
