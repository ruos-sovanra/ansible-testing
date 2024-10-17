package org.devops

class NginxExecutor {

    // Method to run the Ansible playbook with optional extra variables and debug flag
    static void runPlaybook(String playbook, String inventoryFile = 'resources/ansible/inventory/hosts', Map<String, String> extraVars = [:], boolean debug = false) {
        def playbookFile = new File(playbook)
        if (!playbookFile.exists()) {
            throw new RuntimeException("Playbook file not found: ${playbook}")
        }

        def extraVarsString = extraVars.collect { k, v -> "-e ${k}=${v}" }.join(' ')
        def command = "ansible-playbook ${playbook} -i ${inventoryFile} ${extraVarsString} ${debug ? '-vvv' : ''}"

        try {
            println "Executing Ansible Playbook: ${playbook}"
            def process = command.execute()
            def output = new StringBuffer()
            def error = new StringBuffer()
            process.consumeProcessOutput(output, error)
            process.waitFor()

            // Separate output for better log clarity
            println "Ansible Output: ${output}"
            println "Ansible Error: ${error}"

            if (process.exitValue() != 0) {
                throw new RuntimeException("Failed to execute Ansible Playbook: ${playbook}\nError: ${error}")
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute Ansible Playbook: ${e.message}", e)
        }
    }

    // Method to install Nginx using the dedicated Ansible playbook
    static void installNginx(boolean debug = false) {
        runPlaybook('resources/ansible/playbooks/install_nginx.yml', 'resources/ansible/inventory/hosts', [:], debug)
    }

    // Method to uninstall Nginx using the dedicated Ansible task
    static void uninstallNginx(boolean debug = false) {
        runPlaybook('resources/ansible/roles/nginx/tasks/uninstall.yml', 'resources/ansible/inventory/hosts', [:], debug)
    }
}
