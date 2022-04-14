package tech.jour.ygocdb.common.ui

import androidx.viewbinding.ViewBinding
import tech.jour.ygocdb.base.mvvm.v.BaseFrameFragment
import tech.jour.ygocdb.base.mvvm.vm.BaseViewModel

/**
 * Fragment基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFrameFragment<VB, VM>()