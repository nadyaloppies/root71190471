package id.ac.ukdw.pertemuan6_71190471

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_first, container, false)
        val btnFragmentA = v.findViewById<Button>(R.id.btnFirst)
        btnFragmentA.setOnClickListener {
            val i = Intent(context, SecondActivity::class.java)
            context?.startActivity(i)
        }
        return v
    }
}