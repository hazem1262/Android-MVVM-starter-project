package com.example.mvvmstarterproject.ui.main.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import com.example.mvvmstarterproject.data.users.remote.User
import com.example.mvvmstarterproject.databinding.UsersFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : BaseFragment<UsersViewModel>() {

    override val viewModel: UsersViewModel by viewModels()
    private val usersAdapter = UsersAdapter(::navigateToUserDetails)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<UsersFragmentBinding>(inflater, R.layout.users_fragment, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            usersRV.adapter = usersAdapter
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserDetails()
    }

    private fun navigateToUserDetails(user: User){
//        val args = OfferDetailsFragmentArgs(offer)
//        findNavController().navigate(R.id.offerDetailsFragment, args.toBundle())
    }
}
