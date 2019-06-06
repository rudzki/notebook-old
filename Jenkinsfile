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
        git(
            url: 'ssh://git@github.com:rudzki/notebook.git',
            credentialsId: 'lima-bean',
            branch: 'master'
        )
      }
    }

    stage('build') {
      steps {
          sh 'mvn -B -DskipTests clean package'
      }
    }

  }
}