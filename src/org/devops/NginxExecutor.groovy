package org.devops

class NginxExecutor {

    static void runPlaybook(String playbook, String inventoryFile = 'resources/ansible/nginx/hosts', Map<String, String> extraVars = [:]) {
        def playbookFile = new File(playbook)
        println("Playbook file: ${playbookFile}")
        if (!playbookFile.exists()) {
            throw new RuntimeException("Playbook file not found: ${playbook}")
        }

        def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
        def command = "ansible-playbook ${playbook} -i ${inventoryFile} ${extraVarsString}"

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
            throw new RuntimeException("Failed to execute Ansible Playbook: ${e.message}", e)
        }
    }

    static void installNginx() {
        runPlaybook('resources/ansible/playbooks/install_nginx.yml')
    }

    static void uninstallNginx() {
        runPlaybook('resources/ansible/roles/nginx/tasks/uninstall.yml')
    }
}