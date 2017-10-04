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

package org.gradle.nativeplatform.test.xctest.internal;

import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.file.DirectoryVar;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.internal.file.FileOperations;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.language.swift.SwiftBundle;
import org.gradle.language.swift.SwiftComponent;
import org.gradle.language.swift.internal.DefaultSwiftBundle;
import org.gradle.language.swift.internal.DefaultSwiftComponent;
import org.gradle.nativeplatform.test.xctest.SwiftXcodeXCTestSuite;

import javax.inject.Inject;

public class DefaultSwiftXcodeXCTestSuite extends DefaultSwiftComponent implements SwiftXcodeXCTestSuite {
    private final DefaultSwiftBundle bundle;
    private final DirectoryVar resourceDirectory;
    private final SwiftComponent testedComponent;

    @Inject
    public DefaultSwiftXcodeXCTestSuite(String name, ObjectFactory objectFactory, FileOperations fileOperations, ProviderFactory providerFactory, ConfigurationContainer configurations, ProjectLayout projectLayout, SwiftComponent testedComponent) {
        super(name, fileOperations, providerFactory, configurations);

        this.testedComponent = testedComponent;
        resourceDirectory = projectLayout.newDirectoryVar();
        resourceDirectory.set(projectLayout.getProjectDirectory().dir("src/" + name + "/resources"));
        bundle = objectFactory.newInstance(DefaultSwiftBundle.class, name + "Bundle", objectFactory, getModule(), true, getSwiftSource(), configurations, getImplementationDependencies(), getResourceDir());
    }

    @Override
    public SwiftComponent getTestedComponent() {
        return testedComponent;
    }

    @Override
    public DirectoryVar getResourceDir() {
        return resourceDirectory;
    }

    @Override
    public SwiftBundle getDevelopmentBinary() {
        return bundle;
    }

    @Override
    public SwiftBundle getBundle() {
        return bundle;
    }
}
