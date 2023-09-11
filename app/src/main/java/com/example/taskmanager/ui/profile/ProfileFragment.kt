package com.example.taskmanager.ui.profile

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentProfileBinding
import com.example.taskmanager.utils.loadImage
import com.example.taskmanager.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.theartofdev.edmodo.cropper.CropImage

import com.theartofdev.edmodo.cropper.CropImageView


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val pref: Pref by lazy {
        Pref(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etName.setText(pref.getName())
        binding.etName.addTextChangedListener {
            pref.saveName(binding.etName.text.toString())
        }
        if (pref.getAvatar()?.isNotEmpty() == true) {
            pref.getAvatar()?.let { binding.profileImage.loadImage(it) }
        }
        binding.profileImage.setOnClickListener {
            pickImageGallery()
        }

        binding.ivExit.setOnLongClickListener {
            showAlertDialog()
            true
        }
    }

    private fun showAlertDialog(){
        val dialog = AlertDialog.Builder(requireContext())
        dialog.setTitle("Confirm Logout")
            .setMessage("Are you sure you want to log out of your current account?")
            .setCancelable(true)
            .setPositiveButton(R.string.yes) { _, _ ->
                auth.signOut()
                showToast("You have logged out of your account.")
                findNavController().navigate(R.id.authFragment)
            }
            .setNegativeButton(R.string.no) { _, _ ->
            }
            .show()
    }

    private fun pickImageGallery() {
        CropImage.activity()
            .setAspectRatio(1, 1)
            .setRequestedSize(1080, 1080)
            .setCropShape(CropImageView.CropShape.OVAL)
            .start(requireActivity(), this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val resultUri = CropImage.getActivityResult(data).uri
            pref.saveAvatar(resultUri.toString())
            pref.getAvatar()?.let { binding.profileImage.loadImage(it) }

        }
    }
}