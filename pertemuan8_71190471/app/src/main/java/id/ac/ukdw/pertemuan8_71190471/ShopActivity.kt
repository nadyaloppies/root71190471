
package id.ac.ukdw.pertemuan8_71190471

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class ShopActivity : AppCompatActivity() {
    var pager : ViewPager2? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        //View Pager
        val pager = findViewById<ViewPager2>(R.id.pager)
        val listFragment : ArrayList<Fragment> = arrayListOf(ShopFragment(), FavoriteFragment())
        pager?.adapter = HomeActivity.PagerAdapter(this, listFragment)
    }

    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>): FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = listFragment.size
        override fun createFragment(position: Int): Fragment = listFragment[position]

    }
}