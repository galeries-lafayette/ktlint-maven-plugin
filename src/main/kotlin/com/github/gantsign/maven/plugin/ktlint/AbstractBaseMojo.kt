/*-
 * #%L
 * ktlint-maven-plugin
 * %%
 * Copyright (C) 2018 GantSign Ltd.
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */
package com.github.gantsign.maven.plugin.ktlint

import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File

abstract class AbstractBaseMojo : AbstractMojo() {

    @Parameter(defaultValue = "\${project.basedir}", readonly = true, required = true)
    protected lateinit var basedir: File

    @Parameter(defaultValue = "\${project.compileSourceRoots}", readonly = true, required = true)
    private lateinit var compileSourceRoots: List<String>

    @Parameter(
        defaultValue = "\${project.testCompileSourceRoots}",
        readonly = true,
        required = true
    )
    private lateinit var testCompileSourceRoots: List<String>

    /**
     * Include the production source roots.
     */
    @Parameter(property = "ktlint.includeSourceRoots", defaultValue = "true", required = true)
    private var includeSourceRoots = true

    /**
     * Include the test source roots.
     */
    @Parameter(property = "ktlint.includeTestSourceRoots", defaultValue = "true", required = true)
    private var includeTestSourceRoots = true

    protected val sourceRoots: List<String>
        get() {
            var sourceRoots = emptyList<String>()
            if (includeSourceRoots) {
                sourceRoots += compileSourceRoots
            }
            if (includeTestSourceRoots) {
                sourceRoots += testCompileSourceRoots
            }
            return sourceRoots
        }

    /**
     * File file encoding of the Kotlin source files.
     */
    @Parameter(property = "encoding", defaultValue = "\${project.build.sourceEncoding}")
    protected val encoding: String? = null

    /**
     * A list of inclusion filters for the source files to be processed.
     */
    @Parameter
    protected var includes: Set<String>? = null

    /**
     * A list of exclusion filters for the source files to be processed.
     */
    @Parameter
    protected var excludes: Set<String>? = null

    /**
     * Enable Android Kotlin Style Guide compatibility.
     */
    @Parameter(property = "ktlint.android", defaultValue = "false", required = true)
    protected var android: Boolean = false
}
