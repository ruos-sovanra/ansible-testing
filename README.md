
## This is the complete code for the Jenkins Ansible library. To use this library:

1. Set up this project structure in your version control system.
2. Configure it as a shared library in your Jenkins instance.
3. Use the `createDroplet` function in your Jenkins pipelines to create DigitalOcean droplets using Ansible.

Remember to replace `"your_digitalocean_api_token"` in `resources/ansible/roles/droplet/vars/main.yml` with your actual DigitalOcean API token, preferably using Jenkins credentials for security.

Is there anything specific about this code or its usage that you'd like me to explain further?



# Jenkins Ansible Library

This Jenkins shared library provides functionality to create DigitalOcean droplets using Ansible.

## Usage

In your Jenkins pipeline:

```groovy
@Library('jenkins-ansible-library') _

pipeline {
    agent any
    
    stages {
        stage('Create Droplet') {
            steps {
                createDroplet([
                    dropletName: 'my-custom-droplet',
                    dropletRegion: 'sfo3',
                    dropletSize: 's-2vcpu-2gb'
                ])
            }
        }
    }
}