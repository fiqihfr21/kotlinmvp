package com.fiqih.event.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fiqih.event.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_qr.*
import android.widget.Toast
import com.fiqih.event.view.fragment.DetailMapsFragmen


class MapsActivity:AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        im_back.setOnClickListener {
            finish()
        }

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!

        val intercontinental = LatLng( -6.2729323, 106.7392025)
        mMap!!.addMarker(MarkerOptions().position(intercontinental).title("Jakarta Intercontinental"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(intercontinental))
        mMap.animateCamera(CameraUpdateFactory.zoomIn())
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10f), 2000, null)

        val cameraPosition = CameraPosition.Builder()
            .target(intercontinental)      // Sets the center of the map
            .zoom(17f)                   // Sets the zoom
            .bearing(90f)                // Sets the orientation of the camera to east
            .tilt(30f)                   // Sets the tilt of the camera to 30 degrees
            .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        // Retrieve the data from the marker.
        var clickCount:Boolean? = marker!!.getTag() as Boolean

        if (clickCount!!)
        {
            Toast.makeText(this, "map", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}