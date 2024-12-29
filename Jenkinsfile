pipeline{
    agent any
    
    stages{
        
        stage('Checkout'){
            steps{
                checkout([
                    $class:'GitSCM',
                    branches:[[name: '*/main']],
                    userRemoteConfigs:[[
                            url:'https://github.com/dhruvshingari/Flipkart_Automation.git'
                        ]]
                    
                    ]
                )
            }
        }
        
         stage('code Build'){
            steps{ 
                bat "mvn clean compile"
                 }
        }
        
         stage('Test'){
            steps{
                bat "mvn test"
                 }
         }
         

    }
    post {
        always {
            cleanWs(cleanWhenNotBuilt: false,
                    deleteDirs: true,
                    disableDeferredWipeout: true,
                    notFailBuild: true,
                    patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
                               [pattern: '.propsfile', type: 'EXCLUDE']])
        }
        
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed. Please check logs.'
        }
    }
}
    
