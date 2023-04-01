package stenograffia.app.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(): T
}

