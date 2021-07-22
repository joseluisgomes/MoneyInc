package com.example.moneyinc.client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moneyinc.R
import com.example.moneyinc.databinding.ActivityClientBinding

class ClientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityClientBinding.inflate(layoutInflater)
        setTheme(R.style.Main)
        setContentView(binding.root)

        val paymentFragment= ClientPaymentFragment()
        val transferFragment= ClientTransferFragment()
        val accountsFragment= ClientAccountsFragment()
        val profileFragment= ClientMovementsFragment()
        val cardsFragment= ClientCardsFragment()

        setCurrentFragment(paymentFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.miPayment -> setCurrentFragment(paymentFragment)
                R.id.miTransfer -> setCurrentFragment(transferFragment)
                R.id.miAccounts -> setCurrentFragment(accountsFragment)
                R.id.miCards -> setCurrentFragment(cardsFragment)
                R.id.miMovements -> setCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}