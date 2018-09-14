// IGNORE_BACKEND: JS, NATIVE, JS_IR, JVM_IR
// WITH_REFLECT

import kotlin.reflect.full.instanceParameter
import kotlin.reflect.jvm.jvmErasure
import kotlin.test.assertEquals

open class A {
    val property = "OK"

    fun function() {}
}

class B : A()

fun box(): String {
    assertEquals(B::class, B::property.instanceParameter!!.type.jvmErasure)
    assertEquals(B::class, B::function.instanceParameter!!.type.jvmErasure)

    val property = B::class.members.single { it.name == "property" }
    val function = B::class.members.single { it.name == "function" }
    assertEquals(A::class, property.instanceParameter!!.type.jvmErasure)
    assertEquals(A::class, function.instanceParameter!!.type.jvmErasure)

    return "OK"
}
