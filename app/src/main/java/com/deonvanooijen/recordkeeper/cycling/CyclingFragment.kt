package com.deonvanooijen.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.deonvanooijen.recordkeeper.databinding.FragmentCyclingBinding
import com.deonvanooijen.recordkeeper.editrecord.EditRecordActivity
import com.deonvanooijen.recordkeeper.editrecord.INTENT_EXTRA_SCREEN_DATA

class CyclingFragment : Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun setupClickListeners() {
        binding.containerLongestRide.setOnClickListener { launchCyclingRecordScreen("Longest Ride", "Distance") }
        binding.containerBiggestClimb.setOnClickListener { launchCyclingRecordScreen("Biggest Climb", "Height") }
        binding.containerBestAvgSpeed.setOnClickListener { launchCyclingRecordScreen("Best Average Speed", "Average Speed") }
    }

    private fun displayRecords() {
        val cyclingPreferences = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

        binding.textViewLongestRideValue.text = cyclingPreferences.getString("Longest Ride ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("Longest Ride ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewBiggestClimbValue.text = cyclingPreferences.getString("Biggest Climb ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("Biggest Climb ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewBestAvgSpeedValue.text = cyclingPreferences.getString("Best Average Speed ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewBestAvgSpeedDate.text = cyclingPreferences.getString("Best Average Speed ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
    }

    private fun launchCyclingRecordScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra(INTENT_EXTRA_SCREEN_DATA, EditRecordActivity.ScreenData(record, FILENAME, recordFieldHint))
        startActivity(intent)
    }

    companion object {

        const val FILENAME = "cycling"

    }

}