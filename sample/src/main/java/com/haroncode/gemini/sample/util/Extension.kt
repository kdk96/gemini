@file:Suppress("NOTHING_TO_INLINE")

package com.haroncode.gemini.sample.util

import androidx.lifecycle.ViewModelStoreOwner
import com.haroncode.gemini.element.Store
import com.haroncode.gemini.keeper.getStore
import com.haroncode.gemini.sample.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Provider

fun Any.objectScopeName() = "${javaClass.simpleName}_${hashCode()}"

fun Navigator.setLaunchScreen(screen: SupportAppScreen) {
    applyCommands(
        arrayOf(
            BackTo(null),
            Replace(screen)
        )
    )
}

/**
 * Wrap T to Product<T>
 * Flowable<T> -> Flowable<Product<T>>
 */
inline fun <T> Flow<T>.asResource(): Flow<Resource<T>> = map<T, Resource<T>> { data -> Resource.Data(data) }
    .catch { throwable -> emit(Resource.Error(throwable)) }
    .onStart { emit(Resource.Loading) }

inline fun <reified T, Action : Any, State : Any, Event : Any> ViewModelStoreOwner.getStore(
    provider: Provider<T>
): T where T : Store<Action, State, Event> =
    getStore(provider::get)
