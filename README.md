# Bitbucket Branch Source Plugin

[![Build](https://ci.jenkins.io/job/Plugins/job/bitbucket-branch-source-plugin/job/master/badge/icon)](https://ci.jenkins.io/job/Plugins/job/bitbucket-branch-source-plugin/job/master/)
[![GitHub release](https://img.shields.io/github/release/jenkinsci/bitbucket-branch-source-plugin.svg?label=release)](https://github.com/jenkinsci/bitbucket-branch-source-plugin/releases/latest)
[![Jenkins Plugin Installs](https://img.shields.io/jenkins/plugin/i/cloudbees-bitbucket-branch-source?color=blue)](https://plugins.jenkins.io/cloudbees-bitbucket-branch-source)
[![Contributors](https://img.shields.io/github/contributors/jenkinsci/bitbucket-branch-source-plugin.svg)](https://github.com/jenkinsci/bitbucket-branch-source-plugin/contributors)
[![Join the chat](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/jenkinsci/bitbucket-branch-source-plugin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## User Guide

[Browse the user guide here](docs/USER_GUIDE.adoc)

## Notes

* Unlike GitHub, in Bitbucket, [team admins do not have access to forks](https://bitbucket.org/site/master/issues/4828/team-admins-dont-have-read-access-to-forks).
This means that when you have a private repository, or a private fork of a public repository, the team admin will not be able to see the PRs within the fork.

## Developers and DevOps notes

Classes under the packages `com.cloudbees.jenkins.plugins.bitbucket.api` is intended to be public api and can be used to extend functionality in other plugins. Changes in the method signature will be marked with @deprecated providing an alternative new signature or class to use. After a reasonable time (about a year) the method could be removed at all. If some methods are not intended to be used then are marked with `@Restricted(NoExternalUse.class)`.

Classes in other packages are not intended to be used outside of this plugin. Signature can be changed in any moment, backward compatibility are no guaranteed.

When implementing a pipeline (scripted or declarative) we encourage the use of symbols instead of using the name (or fully qualified name) of the class. Symbols are safer against possible reorganization of the plugin code (classic examples: renaming the class or moving it to different packages).

Compliant example:

```
multibranch:
  branchSource:
    bitbucket:
      repoOwner: 'organization'
      repository: 'repository'
      credentialsId: 'bitbucket-credentials'
      traits:
        - bitbucketBranchDiscovery:
            strategyId: 1
        - bitbucketSshCheckout:
            credentialsId: 'bitbucket-ssh-credentials'
```

Noncompliant code example:

```
multibranch:
  branchSource:
    bitbucket:
      repoOwner: 'organization'
      repository: 'repository'
      credentialsId: 'bitbucket-credentials'
      traits:
        - $class: 'BranchDiscoveryTrait'
            strategyId: 1
        - $class: 'com.cloudbees.jenkins.plugins.bitbucket.SSHCheckoutTrait':
            credentialsId: 'bitbucket-ssh-credentials'
```



## How-to run and test with Bitbucket Server locally (deprecated)

* [Install the Atlassian SDK on Linux or Mac](https://developer.atlassian.com/server/framework/atlassian-sdk/install-the-atlassian-sdk-on-a-linux-or-mac-system/) or [on Windows](https://developer.atlassian.com/server/framework/atlassian-sdk/install-the-atlassian-sdk-on-a-windows-system/)
* To run 5.2.0 server: `atlas-run-standalone -u 6.3.0 --product bitbucket --version 5.2.0 --data-version 5.2.0`
