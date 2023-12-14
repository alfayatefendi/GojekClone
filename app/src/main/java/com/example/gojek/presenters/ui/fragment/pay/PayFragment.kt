package com.example.gojek.presenters.ui.fragment.pay

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.gojek.databinding.FragmentPayBinding
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PayFragment : Fragment() {

    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirect: File
    private lateinit var cameraExsecutors: ExecutorService

    private var _binding: FragmentPayBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = mutableListOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        ).apply {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }.toTypedArray()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPayBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (hasPermissionGranted()){
            startCamera()
        }else{
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        binding.btnBackArrow.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
        cameraExsecutors = Executors.newSingleThreadExecutor()
    }
    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinderCamera.surfaceProvider)
                }
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner, cameraSelector, preview)
            } catch (exc: Exception) {
                Log.e("TAG", "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResult: IntArray) {
            if (requestCode == REQUEST_CODE_PERMISSIONS) {
                if (hasPermissionGranted()) {
                    startCamera()
                } else {
                    Toast.makeText(requireContext(), "Permissions not granted by the user.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    private fun hasPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExsecutors.shutdown()
    }
}