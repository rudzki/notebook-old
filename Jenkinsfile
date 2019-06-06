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
            credentialsId: 'fe60c1ff-5919-46f1-9432-5d329c25459e',
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