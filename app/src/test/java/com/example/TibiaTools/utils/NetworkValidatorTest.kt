@file:Suppress("DEPRECATION")

package com.example.TibiaTools.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NetworkValidatorTest {

    private val connectivityManager = mockk<ConnectivityManager>()
    private val network = mockk<Network>()

    private fun capabilitiesWith(vararg transports: Int) = mockk<NetworkCapabilities> {
        every { hasTransport(any()) } answers { firstArg<Int>() in transports }
    }

    // region API >= 23
    @Test
    fun `connected through wifi`() {
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns
            capabilitiesWith(NetworkCapabilities.TRANSPORT_WIFI)

        assertTrue(NetworkValidator.isConnected(connectivityManager, sdkInt = 23))
    }

    @Test
    fun `connected through cellular or ethernet`() {
        every { connectivityManager.activeNetwork } returns network

        every { connectivityManager.getNetworkCapabilities(network) } returns
            capabilitiesWith(NetworkCapabilities.TRANSPORT_CELLULAR)
        assertTrue(NetworkValidator.isConnected(connectivityManager, sdkInt = 30))

        every { connectivityManager.getNetworkCapabilities(network) } returns
            capabilitiesWith(NetworkCapabilities.TRANSPORT_ETHERNET)
        assertTrue(NetworkValidator.isConnected(connectivityManager, sdkInt = 30))
    }

    @Test
    fun `not connected through unsupported transport`() {
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns
            capabilitiesWith(NetworkCapabilities.TRANSPORT_BLUETOOTH)

        assertFalse(NetworkValidator.isConnected(connectivityManager, sdkInt = 34))
    }

    @Test
    fun `not connected without active network`() {
        every { connectivityManager.activeNetwork } returns null

        assertFalse(NetworkValidator.isConnected(connectivityManager, sdkInt = 34))
        verify(exactly = 0) { connectivityManager.getNetworkCapabilities(any()) }
    }

    @Test
    fun `not connected when capabilities are null`() {
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns null

        assertFalse(NetworkValidator.isConnected(connectivityManager, sdkInt = 34))
    }
    // endregion

    // region API 21–22
    @Test
    fun `legacy api uses active network info`() {
        val info = mockk<NetworkInfo> { every { isConnected } returns true }
        every { connectivityManager.activeNetworkInfo } returns info

        assertTrue(NetworkValidator.isConnected(connectivityManager, sdkInt = 21))
        verify(exactly = 0) { connectivityManager.activeNetwork }
    }

    @Test
    fun `legacy api not connected`() {
        every { connectivityManager.activeNetworkInfo } returns null
        assertFalse(NetworkValidator.isConnected(connectivityManager, sdkInt = 22))

        every { connectivityManager.activeNetworkInfo } returns mockk<NetworkInfo> { every { isConnected } returns false }
        assertFalse(NetworkValidator.isConnected(connectivityManager, sdkInt = 22))
    }
    // endregion

    // region hasSupportedTransport / Context entry point
    @Test
    fun `null capabilities are not supported`() {
        assertFalse(NetworkValidator.hasSupportedTransport(null))
    }

    @Test
    fun `context without connectivity service is not connected`() {
        val appContext = mockk<Context> { every { getSystemService(Context.CONNECTIVITY_SERVICE) } returns null }
        val context = mockk<Context> { every { applicationContext } returns appContext }

        assertFalse(NetworkValidator.isConnected(context))
    }
    // endregion
}
