ruleset {
    ruleset('rulesets/basic.xml') {
        'EmptyMethod' {
            enabled = false
        }
    }
    ruleset('rulesets/braces.xml')
    ruleset('rulesets/generic.xml')
    ruleset('rulesets/groovyism.xml')
    ruleset('rulesets/grails.xml') {
        'GrailsMassAssignment' {
            enabled = false
        }
        'GrailsDomainHasEquals' {
            enabled = false
        }
    }
    ruleset('rulesets/imports.xml')
    ruleset('rulesets/unused.xml')
    ruleset('rulesets/unnecessary.xml') {
        'UnnecessaryObjectReferences' {
            enabled = false
        }
    }
    ruleset('rulesets/formatting.xml') {
        'ClassJavadoc' {
            enabled = false
        }
        'SpaceAroundMapEntryColon' {
            enabled = false
        }
        'LineLength' {
            enabled = false
        }
    }
}
