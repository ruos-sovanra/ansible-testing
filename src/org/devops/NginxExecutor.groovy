package org.devops

class NginxExecutor {

    static void runNginxPlaybook(String playbook = 'resources/ansible/playbooks/install_nginx.yml', String inventoryFile = 'resources/ansible/inventory/hosts', Map<String, String> extraVars = [:]) {
        def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
        def command = "ansible-playbook ${playbook} -i ${inventoryFile} ${extraVarsString}"

        println "Command to execute: ${command}"

        try {
            println "Executing Ansible Playbook: ${playbook}"
            def process = command.execute()
            process.waitForProcessOutput(System.out, System.err)
            if (process.exitValue() != 0) {
                throw new RuntimeException("Failed to execute Ansible Playbook: ${playbook}")
            }
        } catch (Exception e) {
            println "Error: ${e.message}"
            throw e
        }
    }
}