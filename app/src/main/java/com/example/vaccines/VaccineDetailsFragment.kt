package com.example.vaccines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView

class VaccineDetailsFragment : Fragment() {

    private lateinit var vaccineImage: ImageView
    private lateinit var vaccineTitle: TextView
    private lateinit var vaccineDescription: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vaccine_details, container, false)

        vaccineImage = view.findViewById(R.id.vaccineImage)
        vaccineTitle = view.findViewById(R.id.vaccineTitle)
        vaccineDescription = view.findViewById(R.id.vaccineDescription)

        val args = arguments
        if (args != null) {
            val name = args.getString(ARG_VACCINE_NAME)
            val imageResId = args.getInt(ARG_VACCINE_IMAGE)
            val description = args.getString(ARG_VACCINE_DESCRIPTION)

            vaccineTitle.text = name
            vaccineImage.setImageResource(imageResId)
            vaccineDescription.text = description
        }

        return view
    }

    companion object {
        private const val ARG_VACCINE_NAME = "vaccine_name"
        private const val ARG_VACCINE_IMAGE = "vaccine_image"
        private const val ARG_VACCINE_DESCRIPTION = "vaccine_description"

        fun newInstance(name: String, imageResId: Int, description: String): VaccineDetailsFragment {
            val fragment = VaccineDetailsFragment()
            val args = Bundle()
            args.putString(ARG_VACCINE_NAME, name)
            args.putInt(ARG_VACCINE_IMAGE, imageResId)
            args.putString(ARG_VACCINE_DESCRIPTION, description)
            fragment.arguments = args
            return fragment
        }
    }
}
