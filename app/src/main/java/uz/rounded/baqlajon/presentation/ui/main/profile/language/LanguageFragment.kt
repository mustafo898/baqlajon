package uz.rounded.baqlajon.presentation.ui.main.profile.language

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentLanguageBinding
import uz.rounded.baqlajon.presentation.MainActivity
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class LanguageFragment : BaseFragment<FragmentLanguageBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLanguageBinding.inflate(inflater)

    @Inject
    lateinit var sharedPref: SharedPreference
    private var a = "2"

    override fun created(view: View, savedInstanceState: Bundle?) {
        hideMainProgress()
        binding.uz.setOnClickListener {
            Hawk.put("pref_lang", "uz")
            a = "1"
            sharedPref.language = a
            binding.checkUz.setImageResource(R.drawable.check)
            binding.checkEn.visibility = View.INVISIBLE
            binding.checkUz.visibility = View.VISIBLE
            binding.checkRu.visibility = View.INVISIBLE
            requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

        Log.d("dfjkbfdkfbfhbs", "onViewCreate: ${sharedPref.language}")
        binding.en.setOnClickListener {
            Hawk.put("pref_lang", "en")
            a = "2"
            sharedPref.language = a

            binding.checkEn.setImageResource(R.drawable.check)
            binding.checkUz.visibility = View.INVISIBLE
            binding.checkEn.visibility = View.VISIBLE
            binding.checkRu.visibility = View.INVISIBLE
            requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))

        }

        binding.ru.setOnClickListener {
            Hawk.put("pref_lang", "ru")
            a = "3"
            sharedPref.language = a
            binding.checkRu.setImageResource(R.drawable.check)
            binding.checkEn.visibility = View.INVISIBLE
            binding.checkRu.visibility = View.VISIBLE
            binding.checkUz.visibility = View.INVISIBLE
            requireActivity().startActivity(Intent(requireActivity(), MainActivity::class.java))
        }

        when (sharedPref.language) {
            "1" -> {
                binding.checkUz.apply {
                    visible()
                    setImageResource(R.drawable.check)
                }
            }
            "2" -> {
                binding.checkEn.apply {
                    visible()
                    setImageResource(R.drawable.check)
                }
            }
            "3" -> {
                binding.checkRu.apply {
                    visible()
                    setImageResource(R.drawable.check)
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LanguageFragment()
    }


}