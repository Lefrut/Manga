package com.dashkevich.choice_genres

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.dashkevich.choice_genres.adapter.GenresItemDelegate
import com.dashkevich.choice_genres.adapter.decorations.MarginItemDecoration
import com.dashkevich.choice_genres.databinding.FragmentChoiceGenresBinding
import com.dashkevich.choice_genres.databinding.GenreItemBinding
import com.dashkevich.utility.adapter.AdapterItemDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


class ChoiceGenresFragment : Fragment(R.layout.fragment_choice_genres) {

    private lateinit var binding: FragmentChoiceGenresBinding
    private val appAdapter = ListDelegationAdapter<List<AdapterItemDelegate>>(
        genresAdapterDelegates {  }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChoiceGenresBinding.bind(view)

        binding.rv.apply {
            adapter = appAdapter
            layoutManager = StaggeredGridLayoutManager(3, VERTICAL)
        }
        val fire = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.fire)
        val heart = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.heart)
        val drama = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.sneezing_face)
        val horror = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.smail_horror)
        val unicorn = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.unicorn)
        val camera = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.camera)
        val magicBall = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.magic_ball)
        val comedy = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.smail_comedy)
        val dailyLife = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.calendar)
        val clock = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.clock)
        val air = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.airplane)
        val cold = getDrawable(requireContext(), com.dashkevich.ui.R.drawable.cold_face)




        kotlin.runCatching {
            appAdapter.items = listOf(
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.action), icon = fire!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.romance), icon = heart!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.drama), icon = drama!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.horror), icon = horror!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.fantasy), icon = unicorn!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.mystical), icon = camera!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.magic), icon = magicBall!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.comedy), icon = comedy!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.daily_life), icon = dailyLife!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.psychology), icon = clock!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.adventure), icon = air!!),
                GenresItemDelegate(title = getString(com.dashkevich.ui.R.string.thriller), icon = cold!!),
            )
        }
    }

    private fun getDrawable(context: Context, @DrawableRes drawable: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawable)
    }

    private fun genresAdapterDelegates(itemClickedListener : (GenresItemDelegate) -> Unit)
    : AdapterDelegate<List<AdapterItemDelegate>> {
        return adapterDelegateViewBinding<GenresItemDelegate, AdapterItemDelegate, GenreItemBinding>(
            viewBinding = { layoutInflater, root ->
                GenreItemBinding.inflate(layoutInflater, root, false)
            },
            on = { item, _, _ ->
                item is GenresItemDelegate
            }
        ) {
            binding.root.setOnClickListener {
                itemClickedListener(item)
            }
            bind {
                binding.title.text = item.title
                binding.icon.setImageDrawable(item.icon)
            }
        }
    }
}

//            addItemDecoration(MarginItemDecoration())