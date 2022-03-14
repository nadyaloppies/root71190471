package id.ac.ukdw.pertemuan5_71190471

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = intent.getStringExtra("user")
        val txvHello = findViewById<TextView>(R.id.txvHello)
        txvHello.text = "Selamat Datang Ayang ${user}"
    }
}