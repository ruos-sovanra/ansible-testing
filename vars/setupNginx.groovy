def call() {
    // Specify the path to your Ansible playbook for Nginx
    def playbookFile = 'ansible/playbooks/install_nginx.yml'

    // Execute the Ansible playbook
    sh """
    ansible-playbook ${playbookFile}
    """
}
