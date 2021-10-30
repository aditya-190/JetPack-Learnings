package com.bhardwaj.cryptocurrency.domain.use_case.get_list

import com.bhardwaj.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

}