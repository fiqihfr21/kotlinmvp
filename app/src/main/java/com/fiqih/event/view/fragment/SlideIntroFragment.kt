package com.fiqih.event.view.fragment

import android.graphics.Color
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.github.paolorotolo.appintro.ISlideBackgroundColorHolder
import android.support.annotation.ColorInt
import kotlinx.android.synthetic.main.layout_screen.*


class SlideIntroFragment : Fragment() {

    private var layoutResId: Int = 0
    companion object {

        private val ARG_LAYOUT_RES_ID = "layoutResId"

        fun newInstance(layoutResId: Int): SlideIntroFragment {
            val sampleSlide = SlideIntroFragment()

            val args = Bundle()
            args.putInt(ARG_LAYOUT_RES_ID, layoutResId)
            sampleSlide.arguments = args

            return sampleSlide
        }
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null && arguments!!.containsKey(ARG_LAYOUT_RES_ID)) {
            layoutResId = arguments!!.getInt(ARG_LAYOUT_RES_ID)
        }
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater, @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }

}