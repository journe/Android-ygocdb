package tech.jour.ygocdb.base.mvvm.v

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import tech.jour.ygocdb.base.mvvm.vm.BaseViewModel
import tech.jour.ygocdb.base.utils.*
import tech.jour.ygocdb.base.utils.network.AutoRegisterNetListener
import tech.jour.ygocdb.base.utils.network.NetworkStateChangeListener
import tech.jour.ygocdb.base.utils.network.NetworkTypeEnum

/**
 * Activity基类
 *
 * @author Qu Yunshuo
 * @since 8/27/20
 */
abstract class BaseFrameActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity(),
    FrameView<VB>, NetworkStateChangeListener {

    protected val mBinding: VB by lazy(mode = LazyThreadSafetyMode.NONE) {
        BindingReflex.reflexViewBinding(javaClass, layoutInflater)
    }

    protected abstract val mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        // ARouter 依赖注入
//        ARouter.getInstance().inject(this)
        // 注册EventBus
//        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.register(this)

        setStatusBar()
        mBinding.initView()
        initNetworkListener()
        initObserve()
        initRequestData()
    }

    /**
     * 初始化网络状态监听
     * @return Unit
     */
    private fun initNetworkListener() {
        lifecycle.addObserver(AutoRegisterNetListener(this))
    }

    /**
     * 设置状态栏
     * 子类需要自定义时重写该方法即可
     * @return Unit
     */
    open fun setStatusBar() {}

    /**
     * 网络类型更改回调
     * @param type Int 网络类型
     * @return Unit
     */
    override fun networkTypeChange(type: NetworkTypeEnum) {}

    /**
     * 网络连接状态更改回调
     * @param isConnected Boolean 是否已连接
     * @return Unit
     */
    override fun networkConnectChange(isConnected: Boolean) {
        toast(if (isConnected) "网络已连接" else "网络已断开")
    }

    override fun onDestroy() {
//        if (javaClass.isAnnotationPresent(EventBusRegister::class.java)) EventBusUtils.unRegister(
//            this
//        )
        super.onDestroy()
    }

    override fun getResources(): Resources {
        // 主要是为了解决 AndroidAutoSize 在横屏切换时导致适配失效的问题
        // 但是 AutoSizeCompat.autoConvertDensity() 对线程做了判断 导致Coil等图片加载框架在子线程访问的时候会异常
        // 所以在这里加了线程的判断 如果是非主线程 就取消单独的适配
//        if (Looper.myLooper() == Looper.getMainLooper()) {
//            AutoSizeCompat.autoConvertDensityOfGlobal((super.getResources()))
//        }
        return super.getResources()
    }
}