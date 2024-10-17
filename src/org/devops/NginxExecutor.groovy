package org.devops

class NginxExecutor {

    static void runPlaybook(String playbook, String inventoryFile = 'resources/ansible/nginx/hosts', Map<String, String> extraVars = [:]) {
        def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
        def command = "ansible-playbook ${playbook} -i ${inventoryFile} ${extraVarsString}"

        try {
            echo "Executing Ansible Playbook: ${playbook}"
            sh command
        } catch (Exception e) {
            error "Failed to execute Ansible Playbook: ${e.message}"
        }
    }

    static void installNginx() {
        runPlaybook('resources/ansible/playbooks/install_nginx.yml')
    }

    static void uninstallNginx() {
        runPlaybook('resources/ansible/roles/nginx/tasks/uninstall.yml')
    }
}