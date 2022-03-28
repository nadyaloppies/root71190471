package id.ac.ukdw.pertemuan7_71190471

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContactDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_contact)

        val name = intent.getStringArrayExtra("name")
        val nohpview = findViewById<TextView>(R.id.edtPerson)
        nameview.setText(name).toString()

        val nohp = intent.getStringArrayExtra("noHp")
        val nohpview = findViewById<TextView>(R.id.tvNoHp)
        ohpview.setText(nohp).toString()

        val email = intent.getStringExtra("email")
        val emailview = findViewById<TextView>(R.id.tvEmail)
        emailview.setText(email).toString()
    }
}
