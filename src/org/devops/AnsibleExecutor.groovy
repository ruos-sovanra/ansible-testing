package org.devops

def runPlaybook(playbook, inventoryFile = 'resources/ansible/nginx/hosts', extraVars = [:]) {
    def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
    def command = ["ansible-playbook", playbook, "-i", inventoryFile] + extraVarsString.split(' ')

    try {
        echo "Executing Ansible Playbook: ${playbook}"
        sh(script: command.join(' '), returnStdout: true)
    } catch (Exception e) {
        error "Failed to execute Ansible Playbook: ${e.message}"
    }
}