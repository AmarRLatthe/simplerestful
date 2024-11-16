pipeline {
    agent any
    
    tools {
        maven "mymaven"
    }
    stages {
        stage('git clone') {
            steps {
                echo 'Cloning the repository'
                git 'https://github.com/AmarRLatthe/simplerestful.git'
            }
        }
        stage('Compile') {
            steps {
                bat 'mvn compile'
            }
        }
        stage('Codereview') {
            steps {
                bat 'mvn pmd:pmd'
            }
            post{
                success{
                   recordIssues(tools: [pmdParser(pattern: '**/pmd.xml')]) 
                }
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                success {
                    junit stdioRetention: '', testResults: 'target/surefire/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }
}
