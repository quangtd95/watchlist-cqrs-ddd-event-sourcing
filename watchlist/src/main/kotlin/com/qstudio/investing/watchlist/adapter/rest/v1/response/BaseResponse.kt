package com.qstudio.investing.watchlist.adapter.rest.v1.response

class BaseResponse<T>(val status: Int, val message: String, val data: T) {
    companion object {
        fun <T> success(data: T): BaseResponse<T> {
            return BaseResponse(status = 200, message = "success", data = data)
        }
    }
}
