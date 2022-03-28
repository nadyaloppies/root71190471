package id.ac.ukdw.pertemuan7_71190471

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = arrayListOf<Contact>()
        listContact.add(Contact("Nadya", R.mipmap.female1, "0812404211", "nadya@gmail.com"))
        listContact.add(Contact("Nanad", R.mipmap.female2, "0812436129", "nanad@gmail.com"))
        listContact.add(Contact("Nady", R.mipmap.female3, "0832361821", "nady@gmail.com"))

        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        rvContact.layoutManager = LinearLayoutManager(this)
        val contactAdapter = ContactAdapter(listContact)
        rvContact.adapter = contactAdapter
    }
}