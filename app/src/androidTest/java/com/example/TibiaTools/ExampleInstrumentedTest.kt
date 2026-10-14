package com.example.TibiaTools

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Prueba instrumentada básica: comprueba que el contexto corresponde al `applicationId` de la app.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        // El namespace es com.example.ttools, pero el applicationId (el paquete instalado) es com.miguel.ttools.
        assertEquals("com.miguel.ttools", appContext.packageName)
    }
}
