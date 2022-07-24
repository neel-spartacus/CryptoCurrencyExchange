#!groovy

pipeline {

  agent any

  environment {
    git_commit_message = ''
    git_commit_diff = ''
    git_commit_author = ''
    git_commit_author_name = ''
    git_commit_author_email = ''
  }

   tools {
          jdk 'jdk1.8'
          maven 'mvn3.5.0'
      }

  stages {

    // Build
    stage('Build') {
      steps {
        checkout scm: [$class: 'GitSCM', branches: [[name: '*/develop']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'stash-jenkins-access-key', url: 'https://github.com/neel-spartacus/CryptoCurrencyExchange.git']]])
        sh 'mvn -Dmaven.test.failure.ignore=true clean install'
      }
      post {
        success {
             junit 'target/surefire-reports/**/*.xml'
        }
      }
    }

    stage('Test') {
            when{
               expression{
                  BRANCH_NAME== 'develop' || BRANCH_NAME== 'master'
               }
            }
            steps {
                /* `make check` returns non-zero on test failures,
                * using `true` to allow the Pipeline to continue nonetheless
                */
                sh 'make check || true'
                junit '**/target/*.xml'
            }
        }
    stage('deploy') {

      steps {
          echo 'Deploying the application'
          withCredentials([
              usernamePassword(credentials:'server-credentials', usernameVariable: USER, passwordVariable: PWD)
          ])
      }
    }

  }
  post {
    success {
      sh "echo 'Send mail on success'"
      // mail to:"me@example.com", subject:"SUCCESS: ${currentBuild.fullDisplayName}", body: "Yay, we passed."
    }
    failure {
      sh "echo 'Send mail on failure'"
      // mail to:"me@example.com", subject:"FAILURE: ${currentBuild.fullDisplayName}", body: "Boo, we failed."
    }
  }
}