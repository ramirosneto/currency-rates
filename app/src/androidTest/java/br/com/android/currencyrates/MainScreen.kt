package br.com.android.currencyrates

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.agoda.kakao.KTextView
import com.agoda.kakao.Screen

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

class MainScreen : Screen<MainScreen>() {

    val usdTextView: KTextView =
        KTextView { withId(R.id.usd) }

    val plnTextView: KTextView =
        KTextView { withId(R.id.pln) }
}