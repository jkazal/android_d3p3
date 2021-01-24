package com.example.rickandmorty.ui.addnewmeetingform

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.AddNewMeetingFormStep1FragmentBinding
import com.example.rickandmorty.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AddNewMeetingFormStep1Fragment : Fragment() {

    private var binding: AddNewMeetingFormStep1FragmentBinding by autoCleared()
    private val viewModel: AddNewMeetingFormStep1ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<AddNewMeetingFormStep1FragmentBinding>(inflater,R.layout.add_new_meeting_form_step1_fragment,container,false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // OnClickListener -> open date picker -> store variable in viewmodel
        // IMPORTANT
        // 1. Ouvrir datepicker
        // 2. Récupérer valeur sélectionnée quand ok (callback)
        // 3. Stocker dans une MLD coté viewmodel
        // 4. Binder MLD Text sur la vue (textview)

        // view.View: permet de manipuler la visibilité des éléments selon un booléen
        binding.addNewForm1DayBtn.setOnClickListener {
            // Open datepicker
            val c: Calendar = Calendar.getInstance()
            viewModel.selectedDay.postValue(
                c.get(Calendar.YEAR).toString() + "-"
                        + c.get(Calendar.MONTH).toString() + "-"
                        + c.get(Calendar.DAY_OF_MONTH)
            )

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    viewModel.selectedDay.postValue(
                        dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                    )
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        binding.addNewForm1StartTimeBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val mHour = c[Calendar.HOUR_OF_DAY]
            val mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                OnTimeSetListener { view, hourOfDay, minute -> viewModel.selectedStartTime.postValue("$hourOfDay:$minute") },
                mHour,
                mMinute,
                true
            )
            timePickerDialog.show()
        }

        binding.addNewForm1EndTimeBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val mHour = c[Calendar.HOUR_OF_DAY]
            val mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                OnTimeSetListener { view, hourOfDay, minute -> viewModel.selectedEndTime.postValue("$hourOfDay:$minute") },
                mHour,
                mMinute,
                true
            )
            timePickerDialog.show()
        }

        binding.addNewForm1Next.setOnClickListener {
            // Prepare Bundle for next step
            val bundle = bundleOf(
                "topicName" to viewModel.topicName.value,
                "date" to viewModel.selectedDay.value,
                "startTime" to viewModel.selectedStartTime.value,
                "endTime" to viewModel.selectedEndTime.value
            )
            findNavController().navigate(R.id.action_addNewMeetingFormStep1Fragment_to_addNewMeetingFormStep2Fragment, bundle)
        }
    }

//    override fun onClickedCharacter(characterId: Int) {
//        findNavController().navigate(
//            R.id.action_addNewMeetingFormStep1Fragment_to_addNewMeetingFormStep2Fragment,
//            bundleOf()
//        )
//    }
}
