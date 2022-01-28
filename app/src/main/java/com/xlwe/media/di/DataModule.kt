package com.xlwe.media.di

import com.xlwe.media.data.communication.UserItemRepositoryImpl
import com.xlwe.media.data.login.AuthenticationRepositoryImpl
import com.xlwe.media.data.logup.RegistrationRepositoryImpl
import com.xlwe.media.data.posts.PostsRepositoryImpl
import com.xlwe.media.data.send.SendRepositoryImpl
import com.xlwe.media.data.sendPost.SendPostRepositoryImpl
import com.xlwe.media.data.signout.SignOutRepositoryImpl
import com.xlwe.media.domain.communication.UserItemRepository
import com.xlwe.media.domain.login.AuthenticationRepository
import com.xlwe.media.domain.logup.RegistrationRepository
import com.xlwe.media.domain.posts.PostsRepository
import com.xlwe.media.domain.send.SendRepository
import com.xlwe.media.domain.sendPost.SendPostRepository
import com.xlwe.media.domain.signout.SignOutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(): AuthenticationRepository {
        return AuthenticationRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideRegistrationRepository(): RegistrationRepository {
        return RegistrationRepositoryImpl()
    }

    @Provides
    fun provideUserItemRepository(): UserItemRepository {
        return UserItemRepositoryImpl()
    }

    @Provides
    fun provideSendRepository(): SendRepository {
        return SendRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSignOutRepository(): SignOutRepository {
        return SignOutRepositoryImpl()
    }

    @Provides
    fun provideSendPostRepository(): SendPostRepository {
        return SendPostRepositoryImpl()
    }

    @Provides
    fun providePostsRepository(): PostsRepository {
        return PostsRepositoryImpl()
    }
}
