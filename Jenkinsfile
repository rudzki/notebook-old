pipeline {
  agent any

  stages {

    stage('HelloWorld') {
      steps {
        echo 'Hello World'
      }
    }

    stage('git clone') {
      steps {
        git clone 'git@github.com:rudzki/notebook.git'
      }
    }

    stage('build') {
      steps {
          sh 'mvn -B -DskipTests clean package'
      }
    }

  }
}