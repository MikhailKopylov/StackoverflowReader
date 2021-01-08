package com.amk.stackoverflowreader.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.model.api.ApiHolder
import com.amk.stackoverflowreader.model.repository.StackUserRepository
import com.amk.stackoverflowreader.presenter.UserAccountPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers


class UserAccountFragment : Fragment(), UserAccountView {

    private lateinit var userAccountPresenter: UserAccountPresenter
    private lateinit var mView: View

    private lateinit var userNameTextView: TextView
    private lateinit var userIDTextView: TextView
    private lateinit var accountIDTextView: TextView
    private lateinit var nextUserButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userAccountPresenter = UserAccountPresenter(
            this,
            AndroidSchedulers.mainThread(),
            StackUserRepository(ApiHolder.dataSource)
        )
        mView = inflater.inflate(R.layout.fragment_user_account, container, false)
        userIDTextView = mView.findViewById(R.id.user_ID_text_view)
        userNameTextView = mView.findViewById(R.id.user_name_text_view)
        accountIDTextView = mView.findViewById(R.id.account_ID_text_view)
//        nextUserButton = mView.findViewById(R.id.next_user_button)
//        nextUserButton.setOnClickListener {
//            userAccountPresenter.clickUpdateUser()
//        }
//        userAccountPresenter.clickUpdateUser()
        userAccountPresenter.loadUser("308903")
        return mView
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UserAccountFragment()
    }

    override fun setName(name: String) {
        userNameTextView.text = name
    }

    override fun setAccountID(accountID: String) {
        accountIDTextView.text = accountID
    }

    override fun setUserID(userID: String) {
        userIDTextView.text = userID
    }
}