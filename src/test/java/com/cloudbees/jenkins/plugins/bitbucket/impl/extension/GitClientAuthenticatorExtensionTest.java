package com.cloudbees.jenkins.plugins.bitbucket.impl.extension;

import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl;
import hudson.plugins.git.extensions.GitSCMExtension;
import org.junit.jupiter.api.Test;
import org.jvnet.hudson.test.Issue;

import static org.assertj.core.api.Assertions.assertThat;

class GitClientAuthenticatorExtensionTest {

    @Issue("JENKINS-75188")
    @Test
    void test_equals_hashCode() throws Exception {
        GitSCMExtension extension = new GitClientAuthenticatorExtension("url", null);
        assertThat(extension.hashCode()).isNotZero();
        assertThat(extension).isEqualTo(new GitClientAuthenticatorExtension("url", null));

        extension = new GitClientAuthenticatorExtension("url", new UsernamePasswordCredentialsImpl(null, "id", null, null, null));
        assertThat(extension.hashCode()).isNotZero();
        assertThat(extension).isNotEqualTo(new GitClientAuthenticatorExtension("url", null));

        extension = new GitClientAuthenticatorExtension("url", new UsernamePasswordCredentialsImpl(null, null, null, null, null));
        assertThat(extension.hashCode()).isNotZero();
        assertThat(extension).isNotEqualTo(new GitClientAuthenticatorExtension("url", new UsernamePasswordCredentialsImpl(null, "some-id", null, null, null)));
    }
}
