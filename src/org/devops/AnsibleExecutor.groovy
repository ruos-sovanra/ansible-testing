// src/org/devops/AnsibleExecutor.groovy
package org.devops

class AnsibleExecutor {

    static def runPlaybook(script, String playbookPath, String inventoryPath, String extraVars = "") {
        script.sh """
        ansible-playbook -i ${inventoryPath} ${playbookPath} ${extraVars}
        """
    }
}
