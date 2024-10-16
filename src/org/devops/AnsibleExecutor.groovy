package org.devops

def runPlaybook(playbook, inventoryFile = 'resources/ansible/nginx/hosts', extraVars = [:]) {
    def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
    def command = "ansible-playbook ${playbook} -i ${inventoryFile} ${extraVarsString}"

    try {
        echo "Executing Ansible Playbook: ${playbook}"
        sh command
    } catch (Exception e) {
        error "Failed to execute Ansible Playbook: ${e.message}"
    }
}
