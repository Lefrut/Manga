package com.dashkevich.choice_genres

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dashkevich.choice_genres.databinding.FragmentChoiceGenresBinding


class ChoiceGenresFragment : Fragment(R.layout.fragment_choice_genres) {

    lateinit var binding: FragmentChoiceGenresBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChoiceGenresBinding.bind(view)
    }
}