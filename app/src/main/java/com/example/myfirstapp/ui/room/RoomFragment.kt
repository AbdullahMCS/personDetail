package com.example.myfirstapp.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfirstapp.data.model.room.RoomModel
import com.example.myfirstapp.databinding.FragmentRoomBinding
import com.example.myfirstapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    // delegates
    private val viewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRoomBinding.inflate(inflater, container, false)

        _binding.let { ui ->

            viewModel.roomList.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {
                        Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Success -> {
                        initView(it.data)
                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            viewModel.getRoomList()

        }

        return binding.root
    }

    private fun initView(data: RoomModel?) {
        data?.let {
            _binding?.rvRooms?.adapter = RoomsAdapter(data)
            _binding?.rvRooms?.layoutManager = GridLayoutManager(context, 3)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}