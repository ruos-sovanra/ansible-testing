// src/org/devops/NginxExecutor.groovy
package org.devops

class NginxExecutor {

    static def installNginx(script) {
        def playbookPath = "resources/ansible/playbooks/install_nginx.yml"
        def inventoryPath = "resources/ansible/inventory/hosts"

        // Run the Ansible playbook to install Nginx
        AnsibleExecutor.runPlaybook(script, playbookPath, inventoryPath)
    }

    static def uninstallNginx(script) {
        def playbookPath = "resources/ansible/roles/nginx/tasks/uninstall.yml"
        def inventoryPath = "resources/ansible/inventory/hosts"

        // Run the Ansible task to uninstall Nginx
        AnsibleExecutor.runPlaybook(script, playbookPath, inventoryPath)
    }
}
