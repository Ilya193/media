package com.xlwe.media.di

import com.xlwe.media.domain.communication.GetUserListUseCase
import com.xlwe.media.domain.communication.UserItemRepository
import com.xlwe.media.domain.login.AuthenticationRepository
import com.xlwe.media.domain.login.LogInSystemUseCase
import com.xlwe.media.domain.login.RegisteredUseCase
import com.xlwe.media.domain.login.SuccessLogInSystemUseCase
import com.xlwe.media.domain.logup.LogUpSystemUseCase
import com.xlwe.media.domain.logup.RegistrationRepository
import com.xlwe.media.domain.logup.SuccessLogUpSystemUseCase
import com.xlwe.media.domain.posts.GetPostListUseCase
import com.xlwe.media.domain.posts.PostsRepository
import com.xlwe.media.domain.send.DeleteMessageUseCase
import com.xlwe.media.domain.send.GetMessageListUseCase
import com.xlwe.media.domain.send.SendMessageUseCase
import com.xlwe.media.domain.send.SendRepository
import com.xlwe.media.domain.sendPost.SendPostRepository
import com.xlwe.media.domain.sendPost.OkSendPostUseCase
import com.xlwe.media.domain.sendPost.SendPostUseCase
import com.xlwe.media.domain.signout.SignOutRepository
import com.xlwe.media.domain.signout.SignOutUseCase
import com.xlwe.media.domain.signout.SuccessSignOutUseCase
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
    fun provideSuccessLogInSystem(loginRepository: AuthenticationRepository): SuccessLogInSystemUseCase {
        return SuccessLogInSystemUseCase(loginRepository)
    }

    @Provides
    fun provideLogUpSystemUseCase(registrationRepository: RegistrationRepository): LogUpSystemUseCase {
        return LogUpSystemUseCase(registrationRepository)
    }

    @Provides
    fun provideSuccessLogUpSystem(registrationRepository: RegistrationRepository): SuccessLogUpSystemUseCase {
        return SuccessLogUpSystemUseCase(registrationRepository)
    }

    @Provides
    fun provideGetUserListUseCase(userItemRepository: UserItemRepository): GetUserListUseCase {
        return GetUserListUseCase(userItemRepository)
    }

    @Provides
    fun provideGetMessageListUseCase(sendRepository: SendRepository): GetMessageListUseCase {
        return GetMessageListUseCase(sendRepository)
    }

    @Provides
    fun provideSendMessageUseCase(sendRepository: SendRepository): SendMessageUseCase {
        return SendMessageUseCase(sendRepository)
    }

    @Provides
    fun provideDeleteMessageUseCase(sendRepository: SendRepository): DeleteMessageUseCase {
        return DeleteMessageUseCase(sendRepository)
    }

    @Provides
    fun provideSignOutUseCase(signOutRepository: SignOutRepository): SignOutUseCase {
        return SignOutUseCase(signOutRepository)
    }

    @Provides
    fun provideSuccessSignOutUseCase(signOutRepository: SignOutRepository): SuccessSignOutUseCase {
        return SuccessSignOutUseCase(signOutRepository)
    }

    @Provides
    fun provideOkSendPostUseCase(sendPostRepository: SendPostRepository): OkSendPostUseCase {
        return OkSendPostUseCase(sendPostRepository)
    }

    @Provides
    fun provideSendPostUseCase(sendPostRepository: SendPostRepository): SendPostUseCase {
        return SendPostUseCase(sendPostRepository)
    }

    @Provides
    fun provideGetPostListUseCase(postsRepository: PostsRepository): GetPostListUseCase {
        return GetPostListUseCase(postsRepository)
    }
}
