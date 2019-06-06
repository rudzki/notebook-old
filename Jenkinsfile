pipeline {
  stages {
    stage('HelloWorld') {
      steps {
        echo 'Hello World'
      }
    }
    stage('git clone') {
      steps {
        git clone "git@github.com:rudzki/notebook.git"
      }
    }
  }
}