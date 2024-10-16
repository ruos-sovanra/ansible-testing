def call() {
    // Print the current working directory
    sh 'pwd'

    sh 'll'

    // Specify the absolute path to your Ansible playbook for Nginx
    def playbookFile = '/opt/jenkins/workspace/remote-ansible/ansible/playbooks/install_nginx.yml'

    // Execute the Ansible playbook
    sh """
    ansible-playbook ${playbookFile}
    """
}
