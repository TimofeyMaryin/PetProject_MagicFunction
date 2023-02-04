package com.example.thisappwillbefunny.domain.use_cases

import com.example.thisappwillbefunny.utils.USER_ENTER_INCORRECT_VALUE


// https://http.cat/

class GetCatsDescInternetStatus {

    private val internetStatusCodes = listOf(
         "100", "101", "102", "103",

         "200", "201", "202", "203", "204", "205", "206", "207",

         "300", "301", "302", "302", "303", "304", "305", "306", "307", "308",

         "400", "401", "402", "403", "404", "405", "406", "407", "408", "409",
         "410", "411", "412", "413", "414", "415", "416", "417", "418", "420",
         "421", "422", "423", "424", "425", "426", "429", "431", "444", "450",
         "451", "497", "498", "499",

         "500", "501", "502", "503", "504", "505", "506", "507", "508", "509",
         "510", "511", "521", "522", "523", "525", "599"
    )

    fun execute(internetStatus: String): String {
        return if (validateCurrentInternetStatus(internetStatus)) {
            Companion.BASE_URL + internetStatus
        } else {
            USER_ENTER_INCORRECT_VALUE
        }
    }

    private fun validateCurrentInternetStatus(value: String): Boolean {
        for (i in internetStatusCodes.indices) {
            if (value == internetStatusCodes[i]) {
                return true
            }
        }

        return false
    }

    companion object {
        private const val BASE_URL = "https://http.cat/"
    }

}