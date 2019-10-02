package com.fiqih.event.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.fiqih.event.R
import android.widget.TextView


class ScheduleFragment : Fragment() {

    internal lateinit var view: View

    internal var `val`: Int = 0
    internal lateinit var c: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_schedule, container, false)
        `val` = arguments!!.getInt("someInt", 0)
        c = view.findViewById(R.id.c)
        c.text = "" + `val`
        return view
    }

    companion object {

        fun newInstance(`val`: Int): ScheduleFragment {
            val fragment = ScheduleFragment()
            val args = Bundle()
            args.putInt("someInt", `val`)
            fragment.arguments = args
            return fragment
        }
    }
}
