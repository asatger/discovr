package org.discovr.mobile.core.di.module

import org.discovr.mobile.core.data.httpclient.modules.httpClientModule
import org.koin.dsl.module

val coreModule = module {
    httpClientModule()
}