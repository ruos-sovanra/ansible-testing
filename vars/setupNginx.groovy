def setupNginx() {
    sh '''
        ansible-playbook -i ansible/inventory/development ansible/roles/nginx/tasks/install.yml
    '''
}