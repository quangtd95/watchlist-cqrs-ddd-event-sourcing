package com.qstudio.investing.watchlist_query.infrastructure.message

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.stereotype.Component

@Component
object MessageUtils {
    @Autowired
    lateinit var messageSource: MessageSource

    fun getMessage(messageKey: String, vararg args: Any?): String {
        return messageSource.getMessage(
            messageKey,
            args,
            LocaleContextHolder.getLocale()
        )
    }
}