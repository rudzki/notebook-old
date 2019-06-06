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
            url: 'git@github.com:rudzki/notebook.git',
            credentialsId: 'd253730b-e019-4a27-ae12-ad62aeadd310',
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