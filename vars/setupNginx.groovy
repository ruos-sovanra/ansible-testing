def setupNginx() {
    println "Starting setupNginx function"
    sh "pwd"

    println "Running Ansible playbook"
    sh '''
        ansible-playbook -i ansible/inventory/development ansible/roles/nginx/tasks/install.yml
    '''
    println "Finished setupNginx function"
}