package com.xlwe.media.di

import com.xlwe.media.domain.login.AuthenticationRepository
import com.xlwe.media.domain.login.LogInSystemUseCase
import com.xlwe.media.domain.login.RegisteredUseCase
import com.xlwe.media.domain.login.SuccessLogInSystem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideLoginSystemUseCase(loginRepository: AuthenticationRepository): LogInSystemUseCase {
        return LogInSystemUseCase(loginRepository)
    }

    @Provides
    fun provideRegisteredUseCase(loginRepository: AuthenticationRepository): RegisteredUseCase {
        return RegisteredUseCase(loginRepository)
    }

    @Provides
    fun provideSuccessLogInSystem(loginRepository: AuthenticationRepository): SuccessLogInSystem {
        return SuccessLogInSystem(loginRepository)
    }
}
