def call() {
    println "Starting setupNginx function"
    sh "pwd"

    println "Running Ansible playbook"

    sh "ansible-playbook -i inventory/hosts playbooks/nginx.yml"

    println "Finished setupNginx function"
}