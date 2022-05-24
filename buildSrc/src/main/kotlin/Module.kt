/**
 * @Author maksonic on 23.05.2022
 */
object Module {
    const val CORE = ":core"
    const val DATA = ":data"
    const val DOMAIN = ":domain"

    object Navigation {
        const val API = ":navigation:api"
        const val IMPL = ":navigation:impl"
    }

    object Shared {
        const val UI_MODEL = ":shared:ui-model"
        const val THEME = ":shared:theme"
        const val UI_WIDGET = ":shared:ui-widget"
    }
}