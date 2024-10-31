import org.devops.Ansible

def call(Map config = [:]) {
    def ansible = new Ansible(this)

    def defaultConfig = [
        dropletName: "jenkins-droplet",
        dropletRegion: "nyc3",
        dropletSize: "s-1vcpu-1gb",
        dropletImage: "ubuntu-20-04-x64",
        sshKeyName: "jenkins-ssh-key",
        doApiToken: ""
    ]

    config = defaultConfig + config

    ansible.runPlaybook('${WORKSPACE}/resources/ansible/playbook.yml', [
        droplet_name: config.dropletName,
        droplet_region: config.dropletRegion,
        droplet_size: config.dropletSize,
        droplet_image: config.dropletImage,
        ssh_key_name: config.sshKeyName,
        do_api_token: config.doApiToken
    ])
}