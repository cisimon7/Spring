package react_spring

import kotlinext.js.jsObject

interface Config {
    var mass: Double
    var tension: Double
    var friction: Double

    companion object { }
}

inline fun config(builder: Config.()->Unit): Config {
    return jsObject<Config>().apply(builder)
}

val Config.Companion.default: Config
    get() = config {
        mass= 1.0
        tension= 170.0
        friction= 26.0
    }

val Config.Companion.gentle: Config
    get() = config {
        mass= 1.0
        tension= 120.0
        friction= 14.0
    }

val Config.Companion.wobbly: Config
    get() = config {
        mass= 1.0
        tension= 180.0
        friction= 12.0
    }

val Config.Companion.stiff: Config
    get() = config {
        mass= 1.0
        tension= 210.0
        friction= 20.0
    }

val Config.Companion.slow: Config
    get() = config {
        mass= 1.0
        tension= 280.0
        friction= 60.0
    }

val Config.Companion.molasses: Config
    get() = config {
        mass= 1.0
        tension= 280.0
        friction= 120.0
    }
