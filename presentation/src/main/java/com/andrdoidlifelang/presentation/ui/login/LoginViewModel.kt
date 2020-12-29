package com.andrdoidlifelang.presentation.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.andrdoidlifelang.domain.repository.Event
import com.andrdoidlifelang.domain.usecase.auth.CheckLoginUseCase
import com.andrdoidlifelang.domain.usecase.auth.LoginUseCase
import com.andrdoidlifelang.presentation.annotation.DefaultDispatcher
import com.andrdoidlifelang.presentation.extention.mapToCleanException
import com.andrdoidlifelang.presentation.ui.base.BaseViewModel
import com.andrdoidlifelang.presentation.ui.base.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkLoginUseCase: CheckLoginUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _loginResult = MutableLiveData<Event<Result<Boolean>>>()
    val loginResult: LiveData<Event<Result<Boolean>>>
        get() = _loginResult

    val username = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val enableLogin = MediatorLiveData<Boolean>().apply {
        addSource(username) {
            value = !username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
        }
        addSource(password) {
            value = !username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
        }
    }

    private val _rememberLogin = liveData(defaultDispatcher) {
        checkLoginUseCase.execute().apply {
            emit(this)
            if (this) {
                _loginResult.postValue(Event(Result.Success(true)))
            }
        }
    }
    val rememberLogin: MutableLiveData<Boolean> = _rememberLogin.map { it } as MutableLiveData<Boolean>

    fun login() {
        viewModelScope.launch(defaultDispatcher) {
            _loginResult.postValue(Event(Result.Loading))

            runCatching {
                loginUseCase.execute(LoginUseCase.Params(username.value ?: "", password.value ?: "", rememberLogin.value ?: false))
                _loginResult.postValue(Event(Result.Success(true)))
            }.getOrElse { e ->
                e.mapToCleanException().apply {
                    _loginResult.postValue(Event(Result.Error(this)))
                    setExceptionAsync(this)
                }
            }
        }
    }
}
