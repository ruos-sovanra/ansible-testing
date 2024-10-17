package org.devops

class NginxExecutor {

    static void runNginxPlaybook(String playbook = '/opt/jenkins/workspace/remote-ansible/resources/ansible/playbooks/install_nginx.yml', String inventoryFile = 'resources/ansible/inventory/hosts', Map<String, String> extraVars = [:]) {
        def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
        def command = "ansible-playbook ${playbook} -i ${inventoryFile} ${extraVarsString}"

        println "Command to execute: ${command}"

        try {
            println "Executing Ansible Playbook: ${playbook}"
            def process = command.execute()
            def output = new StringBuffer()
            def error = new StringBuffer()
            process.consumeProcessOutput(output, error)
            process.waitFor()

            println "Output: ${output}"
            println "Error: ${error}"

            if (process.exitValue() != 0) {
                throw new RuntimeException("Failed to execute Ansible Playbook: ${playbook}\nError: ${error}")
            }
        } catch (Exception e) {
            println "Error: ${e.message}"
            throw e
        }
    }

    static void installNginx() {
        runNginxPlaybook('/opt/jenkins/workspace/remote-ansible/resources/ansible/playbooks/install_nginx.yml')
    }

    static void uninstallNginx() {
        runNginxPlaybook('resources/ansible/roles/nginx/tasks/uninstall.yml')
    }
}
