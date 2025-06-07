package com.example.final_finaltest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_finaltest.databinding.FragmentStoreListBinding
import com.google.android.material.snackbar.Snackbar
import androidx.activity.result.contract.ActivityResultContracts

class StoreListFragment : Fragment() {
    private var _binding: FragmentStoreListBinding? = null
    private val binding get() = _binding!!

    private val storeList = mutableListOf(
        Store("小王麵店", "0912345678", "台北市信義區1號", 4.5f),
        Store("阿美早餐", "0987654321", "新北市板橋區2號", 3.5f)
    )

    private lateinit var adapter: StoreAdapter
    private val editStoreLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val store = data?.getParcelableExtra<Store>("store")
            val editIndex = data?.getIntExtra("editIndex", -1) ?: -1
            if (store != null) {
                if (editIndex >= 0) {
                    storeList[editIndex] = store
                    adapter.notifyItemChanged(editIndex)
                    Snackbar.make(binding.root, "店家資料已更新", Snackbar.LENGTH_SHORT).show()
                } else {
                    storeList.add(store)
                    adapter.notifyItemInserted(storeList.size - 1)
                    Snackbar.make(binding.root, "新增店家成功", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = StoreAdapter(storeList) { store ->
            val editIndex = storeList.indexOf(store)
            val intent = Intent(requireContext(), StoreEditActivity::class.java)
            intent.putExtra("store", store)
            intent.putExtra("editIndex", editIndex)
            editStoreLauncher.launch(intent)
        }
        binding.storeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.storeRecyclerView.adapter = adapter
        binding.fabAddStore.setOnClickListener {
            val intent = Intent(requireContext(), StoreEditActivity::class.java)
            editStoreLauncher.launch(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
