package com.example.news.core.common

import app.cash.turbine.test
import com.example.news.core.common.result.Result
import com.example.news.core.common.result.asResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class ResultTest {

    @Test
    fun `Result catches errors`() = runTest {
        flow {
            emit(1)
            throw Exception("error")
        }.asResult()
            .test {
                assertEquals(Result.Success(1), awaitItem())
                when (val errorResult = awaitItem()) {
                    is Result.Error -> assertEquals(
                        "error",
                        errorResult.exception?.message,
                    )

                    is Result.Success,
                    -> throw IllegalStateException(
                        "The flow should have emitted an Error Result",
                    )
                }
                awaitComplete()
            }
    }
}
