package com.dikiy.workshop.utils.constants

object Constants {

    /**
     * Logs
     */
    enum class LOGS(val tag: String) {
        PARSING("AppParsing"),
        DATABASE("AppDatabase"),
        EVENTS("AppEvent"),
        DATA("AppData")
    }

    /**
     * Margins
     */
    enum class MARGINS(val value: Int) {
        MARGIN2(2),
        MARGIN4(4),
        MARGIN8(8),
        MARGIN16(16),
        MARGIN20(20),
        MARGIN24(24)
    }
}
