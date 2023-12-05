package com.sxiaozhi.somking.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sxiaozhi.somking.core.base.BaseFragment
import com.sxiaozhi.somking.core.base.ErrorState
import com.sxiaozhi.somking.core.base.LoginState
import com.sxiaozhi.somking.core.extensions.makeShortToast
import com.sxiaozhi.somking.databinding.FragmentDebtAskBinding
import com.sxiaozhi.somking.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DebtAskFragment : BaseFragment<FragmentDebtAskBinding>() {

    override fun getToolbar(): Toolbar = binding.toolbar

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup): FragmentDebtAskBinding {
        return FragmentDebtAskBinding.inflate(inflater, container, true)
    }

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.dataState.observe(viewLifecycleOwner){ state ->
            when (state) {
                is HomeViewModel.HomeState.HomeDataState -> {
                    val result = state.result
                }

                is LoginState -> {
                    gotoLoginAuth()
                }

                is ErrorState -> {
                    state.message?.let {
                        context?.makeShortToast(it)
                    }
                }
            }
        }


        homeViewModel.getSubmitData()

    }

}