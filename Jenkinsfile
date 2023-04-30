pipeline {
  agent any

  stages {
      stage('Build Artifact') {
            steps {
              sh "mvn clean package -DskipTests=true" 
              archive 'target/*.jar'
            }
        }   
      stage('Unit Tests') {
            steps {
              sh "mvn test"
            }
        } 
        stage('Mutation Tests') {
            steps {
              sh "mvn org.pitest:pitest-maven:mutationCoverage"
            }
        }
        stage('SonarQube') {
            environment {
              token = credentials('sonarqube-token')
              // ip = credentials('sonarqube-ip')
            }
            steps {
              withSonarQubeEnv('Sonarqube'){
              sh 'mvn sonar:sonar -Dsonar.projectKey=numeric-application -Dsonar.host.url=http://localhost:9000'
              }
              timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
           } 
           stage('Vulnerability Scan - Docker') {
            steps {
              parallel{
                "Dependency Scan":{
             sh "mvn dependency-check:check"},
            "Trivy Scan":{
              sh "bash trivy-docker-image-scan.sh"
            }
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
           
           stage('Kubernetes Deploy - DEV') {
            steps {
              withKubeConfig([credentialsId:"kubeconfig"]){
              sh "sed -i 's#replace#harish4948/numeric-app:${GIT_COMMIT}#g' k8s_deployment_service.yaml"
              sh "kubectl apply -f k8s_deployment_service.yaml"
              }}
    }
}
post{
  always{
junit 'target/surefire-reports/*.xml'
jacoco execPattern: 'target/jacoco.exec'
pitmutation mutationStatsFile: '**/target/pit-reports/**/mutations.xml'
dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'
  }
}
}
