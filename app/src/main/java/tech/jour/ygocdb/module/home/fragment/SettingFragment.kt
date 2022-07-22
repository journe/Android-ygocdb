package tech.jour.ygocdb.module.home.fragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import com.blankj.utilcode.util.ClipboardUtils
import tech.jour.ygocdb.base.ktx.clickDelay
import tech.jour.ygocdb.base.mvvm.vm.EmptyViewModel
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentSettingBinding


class SettingFragment : BaseFragment<FragmentSettingBinding, EmptyViewModel>() {
    override val mViewModel: EmptyViewModel by viewModels()
    override fun FragmentSettingBinding.initView() {
        mBinding.emailAdress.clickDelay {
            ClipboardUtils.copyText("androidcoder.lv@qq.com")
            val data = Intent(Intent.ACTION_SENDTO)
            data.data = Uri.parse("mailto:androidcoder.lv@qq.com")
            data.putExtra(Intent.EXTRA_SUBJECT, "ygocdb-android Feedback")
            data.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(data)
        }
    }

    override fun initObserve() {
    }

    override fun initRequestData() {
    }

}