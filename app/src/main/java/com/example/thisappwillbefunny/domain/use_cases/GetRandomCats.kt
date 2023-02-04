package com.example.thisappwillbefunny.domain.use_cases

import com.example.thisappwillbefunny.domain.internet.random_cats.RandomCatsRetrofitHelper


// https://aws.random.cat/meow

class GetRandomCats {

    suspend fun execute(): String {
        return validateEnterData()
    }

    private suspend fun validateEnterData(): String {
        return RandomCatsRetrofitHelper.api.getCats().body()!!.file.replace("\\", "")
    }

}