/**
 * The MIT License
 *
 * Copyright (c) 2019 SoundTech (angrysoundtech@gmail.com)
 *
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
 */
package com.github.angrysoundtech.makro.scriptengine.kotlin

import com.github.angrysoundtech.makro.scriptengine.LoadException
import net.minecraft.client.Minecraft
//import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
//import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngineFactory
import java.io.InputStream
import java.io.Reader
import javax.script.ScriptEngineManager

/**
 * This class is not thread-safe, don't use it for parallel executions and create new instances instead.
 */
class KtsObjectLoader(classLoader: ClassLoader? = Thread.currentThread().contextClassLoader) {

//    val engine: KotlinJsr223JvmLocalScriptEngine

    init {
        val scriptEngineManager = ScriptEngineManager(Minecraft.getInstance().javaClass.classLoader)
//        scriptEngineManager.registerEngineExtension("kts", KotlinJsr223JvmLocalScriptEngineFactory())
//        engine = scriptEngineManager.getEngineByExtension("kts") as KotlinJsr223JvmLocalScriptEngine
    }

    inline fun <R> safeEval(evaluation: () -> R?) = try {
        evaluation()
    } catch (e: Exception) {
        throw LoadException("Cannot load script", e)
    }

    inline fun <reified T> Any?.castOrError() = takeIf { it is T }?.let { it as T }
            ?: throw IllegalArgumentException("Cannot cast $this to expected type ${T::class}")

//    inline fun <reified T> load(script: String): T = safeEval { engine.eval(script) }.castOrError()

//    inline fun <reified T> load(reader: Reader): T = safeEval { engine.eval(reader) }.castOrError()

//    inline fun <reified T> load(inputStream: InputStream): T = load(inputStream.reader())

//    inline fun <reified T> loadAll(vararg inputStream: InputStream): List<T> = inputStream.map(::load)
}
