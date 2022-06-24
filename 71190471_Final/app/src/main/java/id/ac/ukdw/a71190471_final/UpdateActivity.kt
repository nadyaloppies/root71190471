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

class UpdateActivity : AppCompatActivity() {

    var firestore: FirebaseFirestore? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()


        val getJudul = intent.getStringExtra("judul").toString()
        val getPenulis = intent.getStringExtra("penulis").toString()
        val getPenerbit = intent.getStringExtra("penerbit").toString()
        val getTahun = intent.getStringExtra("tahun").toString()
        val getHalaman = intent.getStringExtra("halaman").toString()

        val edtJudul = findViewById<EditText>(R.id.edtUpdateJudul)
        val edtPenulis = findViewById<EditText>(R.id.edtUpdatePenulis)
        val edtPenerbit = findViewById<EditText>(R.id.edtUpdatePenerbit)
        val edtTahun = findViewById<EditText>(R.id.edtUpdateTahun)
        val edtHalaman = findViewById<EditText>(R.id.edtUpdateHalaman)

        val btnSave = findViewById<Button>(R.id.btnSimpan)

        edtJudul.setText(getJudul)
        edtPenulis.setText(getPenulis)
        edtPenerbit.setText(getPenerbit)
        edtTahun.setText(getTahun)
        edtHalaman.setText(getHalaman)

        btnSave.setOnClickListener {
            val updateBuku = Buku(
                edtJudul.text.toString(),
                edtPenulis.text.toString(),
                edtPenerbit.text.toString(),
                edtTahun.text.toString(),
                edtHalaman.text.toString()
            )
            firestore?.collection("Buku")?.whereEqualTo("judul", getJudul)?.get()!!
                .addOnSuccessListener {
                    for (update in it) {
                        firestore?.collection("Buku")?.document(update.id)?.set(updateBuku)
                            ?.addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Toast.makeText(this, "Update Berhasil", Toast.LENGTH_SHORT)
                                        .show()
                                    val i = Intent(this, MainActivity::class.java)
                                    startActivity(i)
                                }
                            }
                    }
                }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }
}