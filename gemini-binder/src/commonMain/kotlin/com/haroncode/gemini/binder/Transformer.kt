package com.haroncode.gemini.binder

import kotlinx.coroutines.flow.Flow

/**
 * @author HaronCode
 * @author kdk96
 */
fun interface Transformer<in In, out Out> {

    fun transform(input: Flow<In>): Flow<Out>
}

@Suppress("UNCHECKED_CAST")
fun <T : Any> identityTransformer() = IdentityTransformer as Transformer<T, T>

private object IdentityTransformer : Transformer<Any, Any> {
    override fun transform(input: Flow<Any>): Flow<Any> = input
}
