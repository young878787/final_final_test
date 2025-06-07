package com.example.final_finaltest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoreAdapter(
    private val stores: List<Store>,
    private val onItemClick: (Store) -> Unit
) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {
    class StoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.store_name)
        val phone: TextView = view.findViewById(R.id.store_phone)
        val address: TextView = view.findViewById(R.id.store_address)
        val rating: RatingBar = view.findViewById(R.id.store_rating)
        val image: ImageView = view.findViewById(R.id.store_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = stores[position]
        holder.name.text = store.name
        holder.phone.text = store.phone
        holder.address.text = store.address
        holder.rating.rating = store.rating
        store.imageResId?.let { holder.image.setImageResource(it) }
        holder.itemView.setOnClickListener { onItemClick(store) }
        // 點擊電話號碼撥號
        holder.phone.setOnClickListener {
            val context = holder.itemView.context
            val intent = android.content.Intent(android.content.Intent.ACTION_DIAL)
            intent.data = android.net.Uri.parse("tel:${store.phone}")
            context.startActivity(intent)
        }
        // 評分互動
        holder.rating.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                store.rating = rating
                // 通知外部有評分變動（可選）
            }
        }
    }

    override fun getItemCount() = stores.size
}
