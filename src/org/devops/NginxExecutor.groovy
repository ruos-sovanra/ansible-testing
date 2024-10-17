package org.devops

class NginxExecutor {

    static void runPlaybook(String playbook, String inventoryFile = 'resources/ansible/nginx/hosts', Map<String, String> extraVars = [:]) {
        def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
        def command = "ansible-playbook ${playbook} -i ${inventoryFile} ${extraVarsString}"

        try {
            println "Executing Ansible Playbook: ${playbook}"
            def process = command.execute()
            process.waitForProcessOutput(System.out, System.err)
            if (process.exitValue() != 0) {
                throw new RuntimeException("Failed to execute Ansible Playbook: ${playbook}")
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