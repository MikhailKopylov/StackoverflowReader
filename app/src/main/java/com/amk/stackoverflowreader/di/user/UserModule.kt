package com.amk.stackoverflowreader.di.user

import com.amk.stackoverflowreader.mvp.model.api.IDataSource
import com.amk.stackoverflowreader.mvp.model.repository.UserRepository
import com.amk.stackoverflowreader.mvp.model.repository.interfaces.IUserRepo
import dagger.Module
import dagger.Provides

@Module
open class UserModule {

    @UserScope
    @Provides
    fun questionRepo(api: IDataSource): IUserRepo = UserRepository(api)

}