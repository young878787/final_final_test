package com.example.final_finaltest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.final_finaltest.databinding.FragmentStoreEditBinding

class StoreEditFragment : Fragment() {
    private var _binding: FragmentStoreEditBinding? = null
    private val binding get() = _binding!!
    private val args: StoreEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val store = args.store
        if (store != null) {
            binding.editStoreName.setText(store.name)
            binding.editStorePhone.setText(store.phone)
            binding.editStoreAddress.setText(store.address)
            binding.editStoreRating.rating = store.rating
        }
        binding.btnSaveStore.setOnClickListener {
            // 這裡可加上資料驗證與回傳
            Toast.makeText(requireContext(), "儲存成功", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
