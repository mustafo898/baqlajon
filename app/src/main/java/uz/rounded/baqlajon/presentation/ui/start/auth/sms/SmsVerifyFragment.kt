package uz.rounded.baqlajon.presentation.ui.start.auth.sms

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.CountDownTimer
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
    private var referralCode = ""
    var otp = ""
    var type = 0

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSmsVerifyBinding = FragmentSmsVerifyBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        bundle()
        if (type == 3 || type == 4) {
            hideMainProgress()
        }

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
            referralCode = it.getString("REFERALCODE", "")
        }
    }

    private fun observe() {
        if (type == 0 || type == 4) {
            viewModel.createOtp(SendOtpModel(phoneNumber = phone.replace(" ", "")))
        } else {
            viewModel.createForgetOtp(SendOtpModel(phoneNumber = phone.replace(" ", "")))
        }

        lifecycleScope.launchWhenStarted {
            viewModel.check.collectLatest { k ->
                k.data?.let {
                    actions()
                }
                if (k.isLoading) {
                    if (type == 3 || type == 4) {
                        showMainProgress1()
                    } else {
                        showStartProgress()
                    }
                }
                if (k.error.isNotEmpty()) {
                    if (type == 3 || type == 4) {
                        hideMainProgress1()
                    } else {
                        hideStartProgress()
                    }
                    Toast.makeText(requireContext(), k.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.updatePhone.collectLatest { k ->
                k.data?.let {
                    when (type) {
                        4 -> {
                            navigateWithArgs(
                                R.id.action_smsVerifyFragment2_to_editProfileFragment,
                                bundleOf("PHONE" to phone, "TYPE" to 2)
                            )
                            shared.user = it.data
                            shared.token = it.token
                            shared.hasToken = true

                            hideMainProgress1()
                        }
                    }
                }
                if (k.isLoading) {
                    if (type == 3 || type == 4) {
                        showMainProgress1()
                    } else {
                        showStartProgress()
                    }
                }
                if (k.error.isNotEmpty()) {
                    if (type == 3 || type == 4) {
                        hideMainProgress1()
                    } else {
                        hideStartProgress()
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
                }
                if (k.error.isNotBlank()) {
                    binding.resend.gone()
                    Toast.makeText(requireContext(), k.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun actions() {
        when (type) {
            0 -> {
                navigateWithArgs(
                    R.id.action_smsVerifyFragment_to_resetFragment, bundleOf(
                        "NAME" to name,
                        "LASTNAME" to lastname,
                        "PHONE" to phone,
                        "REFERALCODE" to referralCode,
                        "TYPE" to 0,
                    )
                )
                hideStartProgress()
            }
            1 -> {
                navigateWithArgs(
                    R.id.action_smsVerifyFragment_to_resetFragment, bundleOf(
                        "NAME" to name,
                        "LASTNAME" to lastname,
                        "PHONE" to phone,
                        "REFERALCODE" to referralCode,
                        "TYPE" to 1,
                    )
                )
                hideStartProgress()
            }
            3 -> {
                navigate(R.id.action_smsVerifyFragment2_to_editPhoneFragment)
                hideMainProgress1()
            }

        }
    }

    private fun setUpPin() {
        binding.confirm.cardView.isClickable = false
        binding.confirm.cardView.setCardBackgroundColor(
            getColor(
                requireContext(), R.color.button_disabled
            )
        )
        binding.code.addTextChangedListener {
            if (it.toString().length == 6 && it.toString().isNotEmpty()) {
                binding.confirm.cardView.isClickable = true
                binding.confirm.cardView.setCardBackgroundColor(
                    getColor(
                        requireContext(), R.color.main_blue
                    )
                )
                otp = binding.code.text.toString()
                binding.confirm.cardView.setOnClickListener {
//                    actions()
                    if (type == 4) viewModel.updatePhone(
                        CheckOtpModel(
                            otp = otp, phoneNumber = phone
                        )
                    )
                    else viewModel.checkOtp(
                        CheckOtpModel(
                            otp = otp, phoneNumber = phone
                        )
                    )
                }
            } else {
                binding.confirm.cardView.isClickable = false
                binding.confirm.cardView.setCardBackgroundColor(
                    getColor(
                        requireContext(), R.color.button_disabled
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
            binding.resend.text = getString(R.string.time_sms, String.format("%d:%02d", min, sec))
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
            if (type == 4) viewModel.updatePhone(
                CheckOtpModel(
                    otp = matcher.group(0) as String, phoneNumber = phone
                )
            )
            else viewModel.checkOtp(
                CheckOtpModel(
                    otp = matcher.group(0) as String, phoneNumber = phone
                )
            )
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