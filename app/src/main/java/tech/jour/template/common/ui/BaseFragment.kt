package tech.jour.template.common.ui

import androidx.viewbinding.ViewBinding
import tech.jour.template.base.mvvm.v.BaseFrameFragment
import tech.jour.template.base.mvvm.vm.BaseViewModel

/**
 * Fragment基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFrameFragment<VB, VM>()