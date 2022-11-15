package moe.ganen.yui.utils

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlin.reflect.KClass

/**
 * from: https://gist.github.com/mrmans0n/cc5fbbdb5ec89cc4d014ec368d6a0b20
 *
 * Creates a [PreviewParameterProvider] based on the classes of two existing providers.
 *
 * You can create your own easily with Kotlin delegation:
 * ```
 * class ExampleProvider : PreviewParameterProvider<Pair<Type1, Type2>>
 *    by compositeProvider(Type1ParameterProvider::class, Type2ParameterProvider::class)
 * ```
 */
fun <T1 : Any, T2 : Any> compositeProvider(
    kClass1: KClass<out PreviewParameterProvider<T1>>,
    kClass2: KClass<out PreviewParameterProvider<T2>>
): PreviewParameterProvider<Pair<T1, T2>> = CompositeParameterProvider(kClass1, kClass2)

private class CompositeParameterProvider<T1 : Any, T2 : Any>(
    kClass1: KClass<out PreviewParameterProvider<T1>>,
    kClass2: KClass<out PreviewParameterProvider<T2>>
) : PreviewParameterProvider<Pair<T1, T2>> {

    private val provider1 = kClass1.java.newInstance()
    private val provider2 = kClass2.java.newInstance()

    override val values: Sequence<Pair<T1, T2>>
        get() = provider1.values union provider2.values
}

private infix fun <T1 : Any, T2 : Any> Sequence<T1>.union(
    sequence: Sequence<T2>
): Sequence<Pair<T1, T2>> = flatMap { original -> sequence.map { original to it } }
