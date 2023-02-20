package uz.rounded.baqlajon.presentation.ui.start.auth.sms

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.phone.SmsRetriever
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.*
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.core.utils.SmsReceiver
import uz.rounded.baqlajon.databinding.FragmentSmsVerifyBinding
import uz.rounded.baqlajon.domain.model.auth.otp.CheckOtpModel
import uz.rounded.baqlajon.domain.model.auth.otp.SendOtpModel
import uz.rounded.baqlajon.domain.model.auth.register.RegisterModel
import uz.rounded.baqlajon.presentation.MainActivity
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class SmsVerifyFragment : BaseFragment<FragmentSmsVerifyBinding>() {

    private val viewModel: SmsViewModel by viewModels()

    @Inject
    lateinit var shared: SharedPreference

    private val REQ_CONSENT_CODE = 200
    private lateinit var smsBroadcastReceiver: SmsReceiver

    private var name = ""
    private var lastname = ""
    private var image = ""
    private var phone = ""
    private var password = ""
    var otp = ""
    var type = 0
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSmsVerifyBinding = FragmentSmsVerifyBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
//        binding.confirm.cardView.setOnClickListener {
//            startActivity(Intent(requireContext(), MainActivity::class.java))
//            (activity as StartActivity).finish()
//        }
        bundle()
        if (type == 5) (activity as MainActivity).setMainToolbarText(getString(R.string.new_phone_number))


        binding.confirm.cardView.setCardBackgroundColor(
            getColor(
                requireContext(), R.color.button_disabled
            )
        )
        binding.confirm.cardView.isClickable = false

        binding.number.text = getString(R.string.please_enter_code, phone)
        binding.resend.setOnClickListener {
            binding.resend.gone()
            binding.resend.visible()
            viewModel.createOtp(SendOtpModel(phone))
        }

        startUserConsent()
        observe()
        setUpPin()
    }

    private fun bundle() {
        val bundle: Bundle? = this.arguments
        bundle?.let {
            name = it.getString("NAME", "")
            lastname = it.getString("LASTNAME", "")
            phone = it.getString("PHONE", "")
            type = it.getInt("TYPE", 0)
            image = it.getString("IMAGE", "")
        }
    }

    private fun observe() {
        viewModel.createOtp(SendOtpModel(phoneNumber = phone.replace(" ", "")))
        Log.d("KJNFJKDS", "observe: ${phone.replace(" ", "")}")
        lifecycleScope.launchWhenStarted {
            viewModel.registration.collectLatest { k ->
                k.data?.let {
                    hideStartProgress()
                    shared.user = it
                    shared.token = it.token
                    shared.hasToken = true
                    navigateWithArgs(
                        R.id.action_smsVerifyFragment_to_resetFragment,
                        bundleOf("PHONE" to phone)
                    )
                    Log.d("SSNSNWJNJNDNWENBU", "observe: $it")
//                    ipAddress()?.let { it1 ->
//                        DeviceModel(
//                            ip_address = it1,
//                            device = getDeviceName()
//                        )
//                    }?.let { it2 ->
//                        viewModel.getDevice(it2)
//                    }
                }
                if (k.error.isNotEmpty()) {
                    hideStartProgress()
                    Log.d("SSNSNWJNJNDNWENBU", "observe: ${k.error}")
                    Toast.makeText(requireContext(), k.error, Toast.LENGTH_SHORT).show()
                    if (k.error == "Student already exists") {
                        navigate(R.id.loginFragment)
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.check.collectLatest { k ->
                k.data?.let {
                    when (type) {
                        0 -> {
                            sendRequest()
                            hideStartProgress()
                        }
                        1 -> {
                            navigateWithArgs(
                                R.id.action_smsVerifyFragment_to_resetFragment,
                                bundleOf("PHONE" to phone, "TYPE" to 1)
                            )
                            hideStartProgress()
                        }
                        5 -> {
                            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                            hideMainProgress()
                            navigateWithArgs(
                                R.id.editProfileFragment,
                                bundleOf("PHONE" to phone, "TYPE" to 5)
                            )
                        }
                    }
                }
                if (k.isLoading) {
                    if (type == 5) {
                        showMainProgress()
                    } else {
                        showStartProgress()
                    }
                }
                if (k.error.isNotEmpty()) {
                    if (type == 5) {
                        showMainProgress()
                    } else {
                        showStartProgress()
                    }
                    Toast.makeText(requireContext(), k.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.create.collectLatest { k ->
                k.data?.let {
                    timerCount.start()
                    binding.resend.visible()
//                    hideStartProgress()
                }
                if (k.error.isNotBlank()) {
                    binding.resend.gone()
                    Toast.makeText(requireContext(), k.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun sendRequest() {
        viewModel.register(
            RegisterModel(
                firstName = name,
                lastName = lastname,
                phoneNumber = phone,
                image = image,
                password = password,
                otp = otp
            )
        )
    }

    private fun setUpPin() {
        binding.confirm.cardView.isClickable = false
        binding.confirm.cardView.setCardBackgroundColor(
            getColor(
                requireContext(),
                R.color.button_disabled
            )
        )
        binding.code.addTextChangedListener {
            if (it.toString().length == 6 && it.toString().isNotEmpty()) {
                binding.confirm.cardView.isClickable = true
                binding.confirm.cardView.setCardBackgroundColor(
                    getColor(
                        requireContext(),
                        R.color.main_blue
                    )
                )
                otp = binding.code.text.toString()
                binding.confirm.cardView.setOnClickListener {
                    navigateWithArgs(
                        R.id.action_smsVerifyFragment_to_resetFragment,
                        bundleOf(
                            "NAME" to name,
                            "LASTNAME" to lastname,
                            "PHONE" to phone,
                            "IMAGE" to image,
                            "OTPREG" to otp
                        )
                    )
                    Log.d("KJNFJKDS", "setUpPin: $otp")
                    Log.d("KJNFJKDS", "setUpPin: $password")
                }
            } else {
                binding.confirm.cardView.isClickable = false
                binding.confirm.cardView.setCardBackgroundColor(
                    getColor(
                        requireContext(),
                        R.color.button_disabled
                    )
                )
            }
        }
    }

    private val timerCount = object : CountDownTimer(60000, 1000) {
        @SuppressLint("SetTextI18n")
        override fun onTick(millisUntilFinished: Long) {
            val min = (millisUntilFinished / 60000) % 60
            val sec = millisUntilFinished / 1000 % 60
            binding.resend.text =
                getString(R.string.time_sms, String.format("%d:%02d", min, sec))
        }

        override fun onFinish() {
            binding.resend.gone()
            binding.greyCard.visible()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CONSENT_CODE) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                val message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE)
                getOtpMessage(message)
            }
        }
    }

    private fun getOtpMessage(message: String?) {
        val pattern = Pattern.compile("(|^)\\d{6}")
        val matcher = pattern.matcher(message)
        if (matcher.find()) {
            binding.code.setText(matcher.group(0))
            viewModel.checkOtp(CheckOtpModel(otp = matcher.group(0) as String))
        }
    }

    private fun startUserConsent() {
        val client = SmsRetriever.getClient(requireActivity())
        client.startSmsUserConsent(null)
    }

    private fun registerBroadcastReceiver() {
        smsBroadcastReceiver = SmsReceiver {
            startActivityForResult(it, REQ_CONSENT_CODE)
        }
        val intentFilter = IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION)
        activity?.registerReceiver(smsBroadcastReceiver, intentFilter)
    }

    override fun onStart() {
        super.onStart()
        registerBroadcastReceiver()
    }

    override fun onStop() {
        super.onStop()
        activity?.unregisterReceiver(smsBroadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        timerCount.cancel()
    }
}