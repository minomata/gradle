/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.vcs.git.internal

import org.gradle.vcs.git.GitVersionControlSpec
import spock.lang.Specification

class DefaultGitVersionControlSpecSpec extends Specification {
    def 'handles file urls'() {
        given:
        GitVersionControlSpec spec = new DefaultGitVersionControlSpec()
        spec.url = new URI("file:/tmp/repos/foo")

        expect:
        spec.repoName == 'foo'
        spec.uniqueId == 'file:/tmp/repos/foo'
        spec.displayName == 'Git Repository at file:/tmp/repos/foo'
    }

    def 'handles urls which do not end in .git'() {
        given:
        GitVersionControlSpec spec = new DefaultGitVersionControlSpec()
        spec.url = 'https://github.com/gradle/gradle-checksum'

        expect:
        spec.repoName == 'gradle-checksum'
        spec.uniqueId == 'https://github.com/gradle/gradle-checksum'
        spec.displayName == 'Git Repository at https://github.com/gradle/gradle-checksum'
    }

    def 'handles urls which do end in .git'() {
        given:
        GitVersionControlSpec spec = new DefaultGitVersionControlSpec()
        spec.url = 'https://github.com/gradle/gradle-checksum.git'

        expect:
        spec.repoName == 'gradle-checksum'
        spec.uniqueId == 'https://github.com/gradle/gradle-checksum.git'
        spec.displayName == 'Git Repository at https://github.com/gradle/gradle-checksum.git'

    }
}
