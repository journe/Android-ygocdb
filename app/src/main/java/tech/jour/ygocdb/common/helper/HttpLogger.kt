package tech.jour.ygocdb.common.helper

import com.orhanobut.logger.Logger
import okhttp3.logging.HttpLoggingInterceptor
import tech.jour.ygocdb.base.utils.JsonUtil

class HttpLogger : HttpLoggingInterceptor.Logger {
  private val mMessage = StringBuffer() //try to resolve the arrayIndexOutOfBoundsException
  override fun log(message: String) { // 请求或者响应开始
    var message = message
    if (message.startsWith("--> POST")) {
      mMessage.setLength(0)
    }
    // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
    if (message.startsWith("{") && message.endsWith("}")
        || message.startsWith("[") && message.endsWith("]")
    ) {
      message = JsonUtil.formatJson(message)
    }
    mMessage.append(message)
    mMessage.append("\n")
    // 请求或者响应结束，打印整条日志
    if (message.startsWith("<-- END HTTP")) {
      Logger.t("apilog")
          .d(mMessage.toString())
      mMessage.setLength(0)
    }
  }
}
