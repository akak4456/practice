package com.example.practice

import androidx.fragment.app.activityViewModels
import com.example.practice.databinding.FragmentABinding

class FragmentA : BaseFragment<FragmentABinding>(R.layout.fragment_a) {

    val vm: ViewModel by activityViewModels()
    override fun initView() {
        vm.test {
            (requireActivity() as MainActivity).replaceFragment(FragmentB())
        }
    }

    override fun initAfterBinding() {

    }
}