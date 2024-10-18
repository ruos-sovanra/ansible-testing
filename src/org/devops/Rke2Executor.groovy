package org.devops

class Rke2Executor {

    static def installRke2(script) {
        def playbookPath = "resources/ansible/playbooks/install_rke2.yml"
        def inventoryPath = "resources/ansible/inventory/hosts"

        // Run the Ansible playbook to install Rke2
        AnsibleExecutor.runPlaybook(script, playbookPath, inventoryPath)
    }

    static def createDroplet(script) {
        def playbookPath = "resources/ansible/playbooks/create_droplet.yml"
        def inventoryPath = "resources/ansible/inventory/hosts"

        // Run the Ansible playbook to create a DigitalOcean Droplet
        def result = AnsibleExecutor.runPlaybook(script, playbookPath, inventoryPath)

        // Extract the Droplet IP address from the result
        def dropletIp = result.droplet.data.ip_address

        // Update the inventory file with the new Droplet IP address
        updateInventoryFile(dropletIp)
    }

    static def updateInventoryFile(dropletIp) {
        def inventoryFile = new File("resources/ansible/inventory/hosts")
        def inventoryContent = inventoryFile.text

        // Replace the existing IP address with the new Droplet IP address
        inventoryContent = inventoryContent.replaceAll(/(\[nginx_servers\]\n).*/, "\$1${dropletIp} ansible_user=root")
        inventoryContent = inventoryContent.replaceAll(/(\[rke2_servers\]\n).*/, "\$1${dropletIp} ansible_user=root")

        // Write the updated content back to the inventory file
        inventoryFile.text = inventoryContent
    }
}