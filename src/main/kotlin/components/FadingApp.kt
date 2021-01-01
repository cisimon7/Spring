package components

import kotlinext.js.jsObject
import react.RProps
import react.functionalComponent
import react_spring.Animated
import react_spring.spring

val fadingApp = functionalComponent<RProps> {
    val spring = spring {
        opacity = 0
        from = jsObject { opacity = 1 }
    }

    Animated.div {
        attrs.style = spring
        attrs.className = "fadingApp"
        +"I will fade"
    }
}
