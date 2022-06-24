package id.ac.ukdw.a71190471_final

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class BukuAdapter(private val bukulist: ArrayList<Buku>, var context: Context): RecyclerView.Adapter<BukuAdapter.BukuHolder>() {
    inner class BukuHolder(val view: View): RecyclerView.ViewHolder(view) {
        var firestore: FirebaseFirestore? = null
        fun bind(buku: Buku, context: Context) {

            firestore = FirebaseFirestore.getInstance()

            val judul = view.findViewById<TextView>(R.id.tvJudul)
            val penulis = view.findViewById<TextView>(R.id.tvPenulis)
            val penerbit = view.findViewById<TextView>(R.id.tvPenerbit)
            val tahun = view.findViewById<TextView>(R.id.tvTahun)
            val halaman = view.findViewById<TextView>(R.id.tvHalaman)

            judul.setText(buku.judul)
            penulis.setText(buku.penulis)
            penerbit.setText(buku.penerbit)
            tahun.setText(buku.tahun)
            halaman.setText(buku.halaman)


            val btnEdit = view.findViewById<Button>(R.id.btnEdit)
            val btnHapus = view.findViewById<Button>(R.id.btnHapus)


            btnEdit.setOnClickListener {
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("judul", judul.text)
                intent.putExtra("penulis", penulis.text)
                intent.putExtra("penerbit", penerbit.text)
                intent.putExtra("tahun", tahun.text)
                intent.putExtra("halaman", halaman.text)
                context.startActivity(intent)
            }
            btnHapus.setOnClickListener {

                firestore?.collection("Buku")?.whereEqualTo("judul",judul.text)?.get()!!.addOnSuccessListener {
                    for (hapus in it){
                        firestore?.collection("Buku")?.document(hapus.id)?.delete()
                    }
                }
                bukulist.removeAt(adapterPosition)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_buku, parent, false)
        return BukuHolder(v)
    }

    override fun onBindViewHolder(holder: BukuHolder, position: Int) {
        holder.bind(bukulist[position],context)
    }

    override fun getItemCount(): Int {
        return bukulist.size
    }
}