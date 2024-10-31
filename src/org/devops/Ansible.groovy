package org.devops

import groovy.json.JsonOutput

class Ansible implements Serializable {
    def script

    Ansible(script) {
        this.script = script
    }

    def runPlaybook(String playbookPath, Map extraVars = [:]) {
        def extraVarsJson = JsonOutput.toJson(extraVars)
        def extraVarsEscaped = extraVarsJson.replace('"', '\\"')

        script.sh """
            ansible-playbook ${playbookPath} \
            -i \${WORKSPACE}/resources/ansible/inventory.ini \
            -e '${extraVarsEscaped}'
        """
    }
}