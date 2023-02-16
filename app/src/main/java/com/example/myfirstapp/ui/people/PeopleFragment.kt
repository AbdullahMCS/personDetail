package com.example.myfirstapp.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myfirstapp.R
import com.example.myfirstapp.data.model.people.PeopleModel
import com.example.myfirstapp.databinding.FragmentPeopleBinding
import com.example.myfirstapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPeopleBinding.inflate(inflater, container, false)

        binding.let { ui ->

            viewModel.peopleList.observe(viewLifecycleOwner) {
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
            viewModel.getPeopleList()
        }

        return binding.root
    }

    private fun initView(data: PeopleModel?) {
        data?.let {
            binding.rvPeople.layoutManager = LinearLayoutManager(context)
            binding.rvPeople.adapter = PeopleAdapter(data) { person ->
                viewModel.setPerson(person) // question here what is the different between this
                // approach and passing the person as an argument to the next fragment in the navGraph.
                findNavController().navigate(R.id.action_navigationPeople_to_navigationPeopleDetail)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}