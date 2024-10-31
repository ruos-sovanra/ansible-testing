package org.devops

class Ansible implements Serializable {
    def script

    Ansible(script) {
        this.script = script
    }

    def runPlaybook(String playbookPath, Map extraVars = [:]) {
        def extraVarsString = extraVars.collect { k, v ->
            if (k == 'do_api_token') {
                return "${k}='${v}'"
            } else {
                return "${k}=${v}"
            }
        }.join(' ')

        script.sh """
            ansible-playbook ${playbookPath} \
            -i \${WORKSPACE}/resources/ansible/inventory.ini \
            -e ${extraVarsString}
        """
    }
}