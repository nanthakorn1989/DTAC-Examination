package com.dtac.examination.app.ui.profile


import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.dtac.examination.app.R
import com.dtac.examination.app.base.BaseActivity
import com.dtac.examination.app.databinding.ActivityProfileBinding
import com.dtac.examination.app.extension.dismissProgress
import com.dtac.examination.app.extension.showProgress
import com.dtac.examination.app.ui.profile.adapter.ProfileAdapter
import com.dtac.examination.app.ui.profile.viewmodel.ProfileViewModel

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>(ProfileViewModel::class.java) {

    override fun getLayout(): Int = R.layout.activity_profile
    var profileAdapter: ProfileAdapter? = null

    override fun initialView() {

        profileAdapter = ProfileAdapter()
        binding.apply {
            btnSearch.setOnClickListener {
                showProgress()
                etInput.clearFocus()
                viewModel.getProfile(etInput.text.toString().toInt())
            }

            etInput.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    btnSearch.isEnabled = s.toString().isNotEmpty()
                }
            })

            rvProfile.apply {
                layoutManager = LinearLayoutManager(this@ProfileActivity, LinearLayoutManager.VERTICAL, false)
                adapter = profileAdapter
            }
        }

        viewModel.profileMutableLiveData.observe(this, { response ->
            dismissProgress()
            when{
                response != null -> {
                    profileAdapter?.data = response.results
                }
                else -> profileAdapter?.data = null
            }
        })
    }
}