import org.devops.Ansible

def call(Map config = [:]) {
    def ansible = new Ansible(this)

    def defaultConfig = [
        droplet_name: "jenkins-droplet",
        droplet_region: "nyc3",
        droplet_size: "s-1vcpu-1gb",
        droplet_image: "ubuntu-20-04-x64",
        ssh_key_name: "sovanra",
        do_api_token: ""
    ]

    config = defaultConfig + config

    ansible.runPlaybook('${WORKSPACE}/resources/ansible/playbook.yml', config)
}