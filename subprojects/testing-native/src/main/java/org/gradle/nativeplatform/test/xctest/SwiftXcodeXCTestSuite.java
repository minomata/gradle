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

package org.gradle.nativeplatform.test.xctest;

import org.gradle.api.Incubating;
import org.gradle.api.file.DirectoryVar;
import org.gradle.language.swift.SwiftBundle;

/**
 * A XCTest suite targeting Xcode runtime of the API.
 *
 * @since 4.4
 */
@Incubating
public interface SwiftXcodeXCTestSuite extends SwiftXCTestSuite {
    /**
     * Returns the bundle that is built to run this test suite.
     */
    SwiftBundle getBundle();

    /**
     * Returns the resource directory for this component.
     *
     * <p>{@code src/test/resources} is used by default.
     */
    DirectoryVar getResourceDir();
}
