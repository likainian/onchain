package com.interview.onchain.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


open class BaseViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope by CoroutineScope(Dispatchers.IO) {

    private var launchJobs = ArrayList<Job>()

    fun addLaunch(
        onError: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            exception.printStackTrace()
            onError?.invoke(exception)
            this.cancel()
        }
        val job = launch(SupervisorJob() + exceptionHandler) {
            block.invoke(this)
        }
        launchJobs.add(job)
    }

    override fun onCleared() {
        launchJobs.forEach {
            if (!it.isCancelled) it.cancel()
        }
        super.onCleared()
    }
}