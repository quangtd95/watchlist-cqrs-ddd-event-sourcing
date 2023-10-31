package com.qstudio.investing.watchlist_command.adapter.api.rest.response

class BaseResponse<T>(val status: Int, val message: String, val data: T) {
    companion object {
        fun <T> success(data: T): BaseResponse<T> {
            return BaseResponse(status = 200, message = "success", data = data)
        }
    }
}
