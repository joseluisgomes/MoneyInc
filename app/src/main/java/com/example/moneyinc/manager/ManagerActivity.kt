package com.example.moneyinc.manager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moneyinc.R
import com.example.moneyinc.databinding.ActivityManagerBinding

class ManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityManagerBinding.inflate(layoutInflater)
        setTheme(R.style.Main)
        setContentView(binding.root)

        val firstFragment= ManagerCLAccountsFragment()
        val secondFragment= ManagerCardsFragment()
        val thirdFragment= ManagerCreateAccountFragment()
        val fourthFragment= ManagerSearchAccountFragment()

        setCurrentFragment(thirdFragment)

        binding.bottomNavigationView2.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.mi_clients -> setCurrentFragment(firstFragment)
                R.id.mi_client_creditcards -> setCurrentFragment(secondFragment)
                R.id.mi_accounts -> setCurrentFragment(thirdFragment)
                R.id.mi_searchClient -> setCurrentFragment(fourthFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flManagerFragment,fragment)
            commit()
        }
}