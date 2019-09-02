package com.fiqih.event.view.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment
import com.fiqih.event.R
import com.fiqih.event.model.Menu
import com.fiqih.event.view.adapter.MenuMoreAdapter
import kotlinx.android.synthetic.main.bottom_sheet_grid.*
import java.util.ArrayList

class BSMenuFragment : SuperBottomSheetFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.bottom_sheet_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMenu = ArrayList<Menu>()
        listMenu.add(Menu("Barcode", R.drawable.ic_qr))
        listMenu.add(Menu("Agenda", R.drawable.ic_schedule))
        listMenu.add(Menu("Message", R.drawable.ic_message))
        listMenu.add(Menu("Maps", R.drawable.ic_maps))
        listMenu.add(Menu("Gallery", R.drawable.ic_gallery))
        listMenu.add(Menu("Document", R.drawable.ic_document))
        listMenu.add(Menu("Quiz", R.drawable.ic_quiz))
        listMenu.add(Menu("Souvenir", R.drawable.ic_souvenir))
        listMenu.add(Menu("Feedback", R.drawable.ic_rate))
        listMenu.add(Menu("Survey", R.drawable.ic_survei))
        listMenu.add(Menu("About", R.drawable.ic_about))
        listMenu.add(Menu("Help", R.drawable.ic_help))

        val myAdapter = MenuMoreAdapter(activity!!.applicationContext, listMenu)
        bs_recycler_view.layoutManager = GridLayoutManager(activity!!.applicationContext, 4)
        bs_recycler_view.adapter = myAdapter
    }

    //override fun getCornerRadius() = context!!.resources.getDimension(R.dimen.demo_sheet_rounded_corner)

    override fun getPeekHeight(): Int {
        return 900
    }
}