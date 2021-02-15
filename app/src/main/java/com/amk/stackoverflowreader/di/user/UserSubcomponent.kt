package com.amk.stackoverflowreader.di.user

import com.amk.stackoverflowreader.mvp.presenter.listUser.UserListPresenter
import com.amk.stackoverflowreader.mvp.presenter.user.UserPresenter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {

    fun inject(userListPresenter: UserListPresenter)
    fun inject(userPresenter: UserPresenter)
}
