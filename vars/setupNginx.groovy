def call() {
    println "Starting setupNginx function"
    sh "pwd"

    println "Running Ansible playbook"

    sh "ansible-playbook -i ansible/inventory/development ansible/playbooks/install_nginx.yml"

    println "Finished setupNginx function"
}