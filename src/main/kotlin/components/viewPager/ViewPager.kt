@file:Suppress("UnsafeCastFromDynamic")

package components.viewPager

import kotlinext.js.Object
import kotlinext.js.jsObject
import kotlinx.browser.window
import kotlinx.css.px
import react.RProps
import react.functionalComponent
import react.useRef
import react_spring.Animated
import react_spring.SpringProps
import react_spring.funSprings
import styled.styled
import use_gestures.drag

val ViewPager = functionalComponent<RProps> {
    val index = useRef(0)

    val (springs, set, stop) = funSprings(pages.size) { i, _ ->
        x = i * window.innerWidth.toDouble()
        scale = 1.0
        display = "block"
    }

    val bind = drag {

        if (active && distance > window.innerWidth/2) {
            cancel()
            index.current = (index.current + direction[0].let { if (it>0) -1 else 1 }).coerceIn(pages.indices)
        }

        set { i: Int ->
            if (i < index.current-1 || i > index.current+1)
                jsObject<SpringProps> { display = "none" }
            else {
                val nX = (i-index.current) * window.innerWidth + active.let { if (it) movement[0] else 0.0 }
                val nScale = if (active) 1 - (distance/window.innerWidth)/2 else 1.0

                jsObject<SpringProps> { x = nX; scale = nScale; display = "block" }
            }
        }
    }

    (springs as Array<dynamic>).mapIndexed { i, props ->
        val (x_, display_, scale_) = Triple(props.x, props.display, props.scale)
        val obj = bind()
        styled(Animated.div)() {
            attrs {
                for (key in Object.keys(obj)) {
                    val value = obj[key]
                    if (value != null) this.asDynamic()[key] = value
                }
                key=i
                style= jsObject {
                    display=display_
                    transform=x_.interpolate { i:Int -> "translate3d(${i.px}, ${0.px}, ${0.px})" }
                }
            }

            Animated.div {
                attrs {
                    style= jsObject {
                        transform=scale_.interpolate { i:Int -> "scale($i)" }
                        backgroundImage= "url(${pages[i]})"
                    }
                }
            }
        }
    }
}

val pages = arrayOf(
        "https://images.pexels.com/photos/62689/pexels-photo-62689.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/296878/pexels-photo-296878.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/351265/pexels-photo-351265.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260",
        "https://images.pexels.com/photos/924675/pexels-photo-924675.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
)
