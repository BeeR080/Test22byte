package ru.testtobyte.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.testtobyte.R
import ru.testtobyte.databinding.ActivityMainBinding

private lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        bottomNavItemClickListener()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, ListNewsFragment())
            .commit()

    }


    private fun bottomNavItemClickListener(){
        binding.bottomNav.apply {
            this.selectedItemId = R.id.bottommnav_btn_news
            this.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.bottommnav_btn_topnews -> supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment, TopNewsFragment())
                        .commit()
                    R.id.bottommnav_btn_news -> supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment, ListNewsFragment())
                        .commit()

                }
                true
            }


        }

    }



companion object{
    const val BASE_URL = "https://newsapi.org/"
    const val API_KEY = "d1f3cf1c2f4247cfa6ade7c558d5c035"
}
}