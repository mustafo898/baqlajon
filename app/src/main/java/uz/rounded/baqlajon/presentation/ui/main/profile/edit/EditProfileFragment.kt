package uz.rounded.baqlajon.presentation.ui.main.profile.edit

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.getColor
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentEditProfileBinding
import uz.rounded.baqlajon.domain.model.DataModel
import uz.rounded.baqlajon.domain.model.main.profile.UpdateUserRequestModel
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentEditProfileBinding.inflate(inflater)

    private val viewModel: EditViewModel by viewModels()

    @Inject
    lateinit var sharedPreference: SharedPreference

    private var uri: Uri = Uri.EMPTY

    private var type = 0
    private var firstName = ""
    private var lastName = ""
    private var image = ""
    private var password = ""
    private var password1 = ""
    private var phoneNumber = ""
    private var phoneNumber2 = ""
    private var body: MultipartBody.Part? = null

    override fun created(view: View, savedInstanceState: Bundle?) {
        hideMainProgress()

        bundle()
        setAdapters()
        setUserFields(sharedPreference.user)

        getProfile()
        uploadObserve()
        validateFields()
        actions()
    }

    private fun actions() {
        binding.signUp.cardView.setOnClickListener {
            if (validation()) {
                showMainProgress1()
                if (body != null) {
                    viewModel.uploadImage(body!!)
                } else {
                    update()
                }
            }
        }

        binding.edit.setOnClickListener {
            makePermissionMediaRequest()
        }

        binding.changePassword.setOnClickListener {
            navigate(R.id.action_editProfileFragment_to_editPasswordFragment)
        }

        binding.changePhone.setOnClickListener {
            navigateWithArgs(
                R.id.action_editProfileFragment_to_smsVerifyFragment,
                bundleOf("TYPE" to 3, "PHONE" to phoneNumber)
            )
        }
    }

    private fun setAdapters() {
        val regions = resources.getStringArray(R.array.gender)
        val adapter = ArrayAdapter(
            requireContext(), R.layout.item_gender, regions
        )

        binding.gender.adapter = adapter
        binding.date.adapter = adapter
    }

    private fun bundle() {
        arguments?.let {
            password1 = it.getString("PASSWORD", "")
            phoneNumber2 = it.getString("PHONE", "")
            type = it.getInt("TYPE", 0)
            Log.d("sdlkffskjfhsjdfh", "bundle:type $type")
            Log.d("sdlkffskjfhsjdfh", "bundle:phone $phoneNumber2")
        }
    }

    private fun uploadObserve() {
        lifecycleScope.launchWhenStarted {
            viewModel.uploadImage.collectLatest {
                it.data?.let { p ->
                    sharedPreference.user.image = p
                    image = p
                    Log.d("sdkflshdflshf", "actions: upload $image")
                    Log.d("sdkflshdflshf", "actions: upload ${sharedPreference.user.image}")

                    update()
                }
            }
        }
    }

    private fun getProfile() {
        viewModel.getUser()

        lifecycleScope.launchWhenStarted {
            viewModel.user.collectLatest {
                it.data?.let { p ->
                    sharedPreference.user = p
                    setUserFields(sharedPreference.user)
                }
                if (it.error.isNotBlank()) {
                    Log.d("sdksfkhsjlddhj", "observe: ${it.error}")
                    hideMainProgress()
                }
            }
        }
    }

    private fun update() {
        viewModel.updateUser(
            UpdateUserRequestModel(
                firstName, image, lastName, password, phoneNumber
            )
        )

        lifecycleScope.launchWhenStarted {
            viewModel.updateUser.collectLatest {
                it.data?.let { p ->
                    sharedPreference.user = p
                    setUserFields(sharedPreference.user)
                    hideMainProgress1()
                    body = null
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun makePermissionMediaRequest() {
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    @SuppressLint("MissingPermission")
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            val galleryIntent = Intent(Intent.ACTION_PICK, null)
            galleryIntent.type = "image/*"
            val chooser = Intent(Intent.ACTION_CHOOSER)
            chooser.putExtra(Intent.EXTRA_INTENT, galleryIntent)
            chooser.putExtra(Intent.EXTRA_TITLE, "Select from:")
            resultLauncher.launch(chooser)
        } else {
//            val count = shared.getClickPermission()
//            shared.setClickPermission(count + 1)
//
//            if (shared.getClickPermission() > 2) {
//                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//                val uri = Uri.fromParts("package", activity?.packageName, null)
//                intent.data = uri
//                startActivity(intent)
//            }
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val bit: Bitmap?
                uri = data?.data!!
                try {
                    getPath(uri, requireActivity())?.let { updatePhoto(it) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    private fun updatePhoto(str: String) {
        val file = File(str)
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        Glide.with(requireContext()).load(str).into(binding.image)
    }

    private fun getPath(uri: Uri?, activity: Activity): String? {
        val projection = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor = activity.managedQuery(uri, projection, null, null, null)
        val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        cursor.moveToFirst()
        return cursor.getString(columnIndex)
    }

    private fun validation(): Boolean {
        return if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
            binding.signUp.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.main_blue
                )
            )
            binding.signUp.cardView.isClickable = true
            true
        } else {
            binding.signUp.cardView.setCardBackgroundColor(
                getColor(
                    requireContext(), R.color.button_disabled
                )
            )
            binding.signUp.cardView.isClickable = false
            false
        }
    }

    private fun validateFields() {
        binding.firstName.editText.addTextChangedListener {
            firstName = it.toString().trim()
            validation()
        }
        binding.lastName.editText.addTextChangedListener {
            lastName = it.toString().trim()
            validation()
        }
    }

    private fun setUserFields(user: DataModel) {
        lastName = user.lastName
        firstName = user.firstName
        image = user.image

        phoneNumber = user.phoneNumber
        password = user.password

        Log.d("sdlkflskdhfldh", "setUserFields: ${user.phoneNumber}")
        Log.d("sdlkflskdhfldh", "setUserFields: $phoneNumber2")

        when (type) {
            1 -> {
                password = password1
            }
            2 -> {
                phoneNumber = phoneNumber2
            }
        }

        binding.image.loadImage(requireContext(), user.image)
        binding.firstName.editText.setText(user.firstName)
        binding.lastName.editText.setText(user.lastName)
        binding.editText.text = user.phoneNumber

        binding.signUp.cardView.setCardBackgroundColor(
            getColor(
                requireContext(), R.color.button_disabled
            )
        )

        var string = ""
        for (i in 0 until user.password.length) {
            string += "*"
        }
        binding.password.text = string
    }
}