package br.com.android.currencyrates

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityKakaoTest {
    @JvmField
    @Rule
    val testRule = ActivityTestRule(MainActivity::class.java)

    private val kakaoScreen = MainScreen()

    @Test
    fun initialViewsDisplayedProperly() {
        kakaoScreen {
            usdTextView {
                isDisplayed()
                hasAnyText()
            }
            plnTextView {
                isDisplayed()
                hasAnyText()
            }
        }
    }
}