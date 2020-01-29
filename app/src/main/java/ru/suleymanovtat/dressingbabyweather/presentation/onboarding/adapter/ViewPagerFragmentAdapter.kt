package ru.suleymanovtat.dressingbabyweather.presentation.onboarding.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment.CitiesFragment
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment.DateOfBirthFragment
import ru.suleymanovtat.dressingbabyweather.presentation.onboarding.fragment.WelcomeFragment
import java.util.*


class ViewPagerFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val arrayList =
        ArrayList<Fragment>()

    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 ->  WelcomeFragment.newInstance()
            1 ->  DateOfBirthFragment.newInstance()
            2 ->  CitiesFragment.newInstance()
            else ->  WelcomeFragment.newInstance()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}