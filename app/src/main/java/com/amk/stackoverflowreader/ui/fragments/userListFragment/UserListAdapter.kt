package com.amk.stackoverflowreader.ui.fragments.userListFragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amk.stackoverflowreader.R
import com.amk.stackoverflowreader.mvp.model.entity.image.IImageLoader
import com.amk.stackoverflowreader.mvp.presenter.listUser.UserItemPresenter
import com.amk.stackoverflowreader.mvp.view.listUser.UserItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter(
    private val listUserItemPresenter: UserItemPresenter,
    val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<UserListAdapter.UserListHolder>() {

    inner class UserListHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer, UserItemView {
        override fun setLogin(login: String) = with(containerView) {
            tv_login.text = login
        }

        override fun loadAvatar(url: String) = with(containerView) {
            imageLoader.loadInto(url, iv_user_avatar)
        }

        @SuppressLint("SetTextI18n")
        override fun setReputation(reputation: Int) = with(containerView) {
            tv_reputation.text = "reputation: $reputation"
        }

        override fun setLocation(location: String) = with(containerView) {
            tv_location.text = location
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListHolder =
        UserListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )

    override fun onBindViewHolder(holder: UserListHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            listUserItemPresenter.itemClickListener?.invoke(holder)
        }
        listUserItemPresenter.bindView(holder)
    }

    override fun getItemCount(): Int = listUserItemPresenter.getCount()
}