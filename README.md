# KodeKloud DevSecOps HandsOn

#### A Repository tracking the progress of Implementing a Secure Pipeline in Jenkins.


```
## Fork and Clone this Repo

## Clone to Desktop and VM

## NodeJS Microservice - Docker Image -
`docker run -p 8787:5000 siddharth67/node-service:v1`

`curl localhost:8787/plusone/99`
 
## NodeJS Microservice - Kubernetes Deployment -
`kubectl create deploy node-app --image siddharth67/node-service:v1`

`kubectl expose deploy node-app --name node-service --port 5000 --type ClusterIP`

`curl node-service-ip:5000/plusone/99`

```
Below is the pipeline built
![image](https://user-images.githubusercontent.com/36042547/236666605-de1c6cf1-d01b-4903-981a-992f438f1775.png)
