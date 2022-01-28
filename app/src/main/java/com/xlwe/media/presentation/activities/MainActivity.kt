package com.xlwe.media.presentation.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import com.xlwe.media.R
import com.xlwe.media.databinding.ActivityMainBinding
import com.xlwe.media.domain.model.User
import com.xlwe.media.presentation.OnResultCamera
import com.xlwe.media.presentation.fragments.AddFragment
import com.xlwe.media.presentation.fragments.CommunicationFragment
import com.xlwe.media.presentation.fragments.SendFragment
import com.xlwe.media.presentation.viewmodels.SignOutViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CommunicationFragment.OnClickUser,
    SendFragment.OnClose,
    AddFragment.OnDataTransmission,
    AddFragment.OnTransition {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: SignOutViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private var onResultCamera: OnResultCamera? = null

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            onResultCamera?.onResultCamera(mFileProvider)
        }
        else {
            binding.bottomNavigation.selectedItemId = R.id.communicationFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.communicationFragment, R.id.postsFragment, R.id.addFragment)
        )

        setClickListener()
    }

    private fun setClickListener() {
        binding.bottomNavigation.setOnItemSelectedListener  { item ->
            when(item.itemId) {
                R.id.communicationFragment -> {
                    item.onNavDestinationSelected(navController)
                }
                R.id.postsFragment -> {
                    item.onNavDestinationSelected(navController)
                }
                R.id.addFragment -> {
                    item.onNavDestinationSelected(navController)
                    startCamera()
                }
            }

            true
        }
    }

    private val simpleDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    private lateinit var photoFile: File
    private lateinit var mFileProvider: Uri

    private fun startCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = createImageFile()

        mFileProvider = FileProvider.getUriForFile(this, "com.xlwe.media.fileprovider", photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mFileProvider)
        startForResult.launch(takePictureIntent)
    }

    private fun createImageFile(): File {
        val timeStamp: String = simpleDateFormat
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.signOut) {
            viewModel.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        return true
    }

    override fun onDataTransmission(onResCamera: OnResultCamera) {
        onResultCamera = onResCamera
    }

    override fun onTransition() {
        binding.bottomNavigation.selectedItemId = R.id.communicationFragment
        Toast.makeText(this, "Пост создан", Toast.LENGTH_SHORT).show()
    }

    override fun onClickUser(user: User) {
        binding.bottomNavigation.visibility = View.GONE
        binding.toolbar.title = user.name
    }

    override fun onClose() {
        binding.bottomNavigation.visibility = View.VISIBLE
        binding.toolbar.title = "Messenger"
    }
}