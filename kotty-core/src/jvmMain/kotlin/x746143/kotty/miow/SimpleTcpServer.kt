/*
 * Copyright 2025 0x746143
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package x746143.kotty.miow

import java.lang.foreign.FunctionDescriptor.of
import java.lang.foreign.Linker
import java.lang.foreign.SymbolLookup
import java.lang.foreign.ValueLayout.JAVA_INT
import java.lang.invoke.MethodHandle

class SimpleTcpServer {
    companion object {
        init {
            // TODO: read the lib from a jar-file and copy to tmp dir
            val kottyCoreDir = System.getProperty("user.dir")
            System.load("$kottyCoreDir/../kotty-rust/target/debug/libkottymiow.so")
        }

        // TODO: consider to use jextract for binding generation
        val initServer: MethodHandle = SymbolLookup.loaderLookup()
            .find("kotty_miow_init_server")
            .map { Linker.nativeLinker().downcallHandle(it, of(JAVA_INT)) }
            .orElseThrow()
    }

    fun startServer() {
        val result = initServer.invokeExact() as Int
        if (result < 0) {
            throw Exception("Failed to start simple TCP server")
        }
    }
}