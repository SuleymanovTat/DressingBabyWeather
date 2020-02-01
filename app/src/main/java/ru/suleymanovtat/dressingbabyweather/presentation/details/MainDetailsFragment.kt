package ru.suleymanovtat.dressingbabyweather.presentation.details

//import coil.api.load
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_details.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.di.component.ViewModelComponent
import ru.suleymanovtat.dressingbabyweather.model.local.CardLocal
import ru.suleymanovtat.dressingbabyweather.presentation.base.BaseFragment


class MainDetailsFragment : Fragment(R.layout.main_details) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardLocal = (arguments?.getParcelable<CardLocal>("card") as CardLocal)
        toolbar?.text = cardLocal.description
        btnBack?.setOnClickListener { activity?.onBackPressed() }
        Glide
            .with(imageView.context)
            .load(cardLocal.image)
            .into(imageView);
    }
}