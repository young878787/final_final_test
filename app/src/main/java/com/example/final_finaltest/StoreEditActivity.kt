package com.example.final_finaltest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.final_finaltest.databinding.FragmentStoreEditBinding

class StoreEditActivity : AppCompatActivity() {
    private lateinit var binding: FragmentStoreEditBinding
    private var editIndex: Int = -1
    private var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentStoreEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val store = intent.getParcelableExtra<Store>("store")
        editIndex = intent.getIntExtra("editIndex", -1)
        isEdit = store != null && editIndex >= 0
        if (isEdit && store != null) {
            binding.editStoreName.setText(store.name)
            binding.editStorePhone.setText(store.phone)
            binding.editStoreAddress.setText(store.address)
            binding.editStoreRating.rating = store.rating
        }

        binding.btnSaveStore.setOnClickListener {
            val name = binding.editStoreName.text.toString().trim()
            val phone = binding.editStorePhone.text.toString().trim()
            val address = binding.editStoreAddress.text.toString().trim()
            val rating = binding.editStoreRating.rating
            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                AlertDialog.Builder(this)
                    .setTitle("錯誤")
                    .setMessage("請填寫所有欄位")
                    .setPositiveButton("確定", null)
                    .show()
                return@setOnClickListener
            }
            val newStore = Store(name, phone, address, rating)
            val result = Intent().apply {
                putExtra("store", newStore)
                putExtra("editIndex", if (isEdit) editIndex else -1)
            }
            setResult(Activity.RESULT_OK, result)
            AlertDialog.Builder(this)
                .setTitle("成功")
                .setMessage(if (isEdit) "店家資料已更新" else "新增店家成功")
                .setPositiveButton("確定") { _, _ -> finish() }
                .show()
        }
    }
}
