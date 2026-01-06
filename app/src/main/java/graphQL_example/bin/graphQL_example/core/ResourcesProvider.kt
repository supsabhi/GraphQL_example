package graphQL_example.bin.graphQL_example.core

import android.content.Context
import androidx.annotation.StringRes

class ResourcesProvider(
     private val context: Context
) {
    fun getString(@StringRes stringResId: Int): String {
        return context.resources.getString(stringResId)
    }
}