package com.example.myfirstapp.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.myfirstapp.R
import com.example.myfirstapp.databinding.FragmentPeopleDetailBinding

class PeopleDetailFragment : Fragment() {

    private var _binding: FragmentPeopleDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PeopleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeopleDetailBinding.inflate(
            layoutInflater,
            container,
            false
        )

        val person = viewModel.person.value!! // question here about !!

        _binding?.apply {
            Glide.with(requireContext()) // question here about required context
                .load(person.avatar) // question here null safety
                .placeholder(R.drawable.animate_loading)
                .centerCrop()
                .into(ivProfilePic)

            tvName.text = getString(R.string.person_name, person.firstName, person.lastName)
            tvId.text = getString(R.string.person_id, person.id)
            tvJobtitle.text = getString(R.string.person_jobTitle, person.jobtitle)
            tvEmail.text = getString(R.string.person_email, person.email)
        }

        return binding.root
    }


}