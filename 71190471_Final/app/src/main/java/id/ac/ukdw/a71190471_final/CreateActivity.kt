package id.ac.ukdw.a71190471_final

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateActivity: AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        val edtJudul = findViewById<EditText>(R.id.edtJudul)
        val edtPenulis = findViewById<EditText>(R.id.edtPenulis)
        val edtPenerbit = findViewById<EditText>(R.id.edtPenerbit)
        val edtTahun = findViewById<EditText>(R.id.edtTahun)
        val edtHalaman = findViewById<EditText>(R.id.edtHalaman)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val buku = Buku(
                edtJudul.text.toString(), edtPenulis.text.toString(),
                edtPenerbit.text.toString(), edtTahun.text.toString(),
                edtHalaman.text.toString()
            )

            edtJudul.setText("")
            edtPenulis.setText("")
            edtPenerbit.setText("")
            edtTahun.setText("")
            edtHalaman.setText("")
            firestore?.collection("Buku")?.add(buku)?.addOnSuccessListener {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                Toast.makeText(this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}