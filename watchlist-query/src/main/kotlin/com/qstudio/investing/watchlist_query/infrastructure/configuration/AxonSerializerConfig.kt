package com.qstudio.investing.watchlist_query.infrastructure.configuration

import com.thoughtworks.xstream.XStream
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AxonSerializerConfig {

    @Bean
    fun xStream() = XStream().apply {
        allowTypesByWildcard(arrayOf("com.qstudio.**"))
    }

//    @Bean
//    fun jacksonSerializer(): JacksonSerializer {
//        return object : JacksonSerializer(builder()) {
//            override fun resolveClassName(serializedType: SerializedType): String {
//                return serializedType.name
//            }
//
//            override fun typeForClass(type.kt: Class<*>?): SerializedType {
//                return super.typeForClass(type.kt)
//            }
//        }
//    }


}