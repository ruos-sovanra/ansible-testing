package org.devops

class NginxExecutor {

    static void runNginxPlaybook(String playbook = 'resources/ansible/playbooks/install_nginx.yml', String inventoryFile = 'resources/ansible/inventory/hosts', Map<String, String> extraVars = [:]) {
        def workspace = System.getenv('WORKSPACE')
        def playbookPath = "${workspace}/${playbook}"
        def inventoryPath = "${workspace}/${inventoryFile}"
        def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
        def command = "ansible-playbook ${playbookPath} -i ${inventoryPath} ${extraVarsString}"

        println "Command to execute: ${command}"

        try {
            println "Executing Ansible Playbook: ${playbookPath}"
            def process = command.execute()
            def output = new StringBuffer()
            def error = new StringBuffer()
            process.consumeProcessOutput(output, error)
            process.waitFor()

            println "Output: ${output}"
            println "Error: ${error}"

            if (process.exitValue() != 0) {
                throw new RuntimeException("Failed to execute Ansible Playbook: ${playbookPath}\nError: ${error}")
            }
        } catch (Exception e) {
            println "Error: ${e.message}"
            throw e
        }
    }

    static void installNginx() {
        runNginxPlaybook('resources/ansible/playbooks/install_nginx.yml')
    }

    static void uninstallNginx() {
        runNginxPlaybook('resources/ansible/roles/nginx/tasks/uninstall.yml')
    }
}