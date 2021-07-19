package com.example.mvvmstarterproject.ui.main.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.users_fragment.*

@AndroidEntryPoint
class UsersFragment : BaseFragment<UsersViewModel>() {

    override val viewModel: UsersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        test.text = viewModel.testString
        viewModel.getUserDetails()
        viewModel.userLiveData.observe(viewLifecycleOwner, {
            if (it != null){
                Toast.makeText(activity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

}
