package com.amk.stackoverflowreader.model.entity

import com.amk.stackoverflowreader.model.UserAccount
import com.google.gson.annotations.Expose

data class UserAccountList(
    @Expose val items: Array<UserAccount>,
) {
}