package com.miketruso.churchdinners

class BootStrap {

    BootstrapDataService bootstrapDataService

    def init = { servletContext ->
        bootstrapDataService.initData()
    }

    def destroy = {
    }
}
