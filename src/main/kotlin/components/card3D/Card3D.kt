@file:Suppress("UnsafeCastFromDynamic")

package components.card3D

import kotlinx.browser.window
import org.w3c.dom.events.MouseEvent
import react.RProps
import react.functionalComponent
import react_spring.Animated
import react_spring.config
import react_spring.funSpring
import react_spring.setSpring
import react_spring.springProps

val Card3D = functionalComponent<RProps> {
    val (props, set, _) = funSpring {
        xys = arrayOf(0.0,.0,1.0)
        config = config {
            mass = 5.0
            tension = 350.0
            friction = 40.0
        }
    }

    fun calc(x: Int, y:Int) = arrayOf((window.innerHeight/2 - y)/20.0, (x - window.innerWidth/2)/20.0, 1.5)
    fun trans(x: Int, y:Int, s:Int) = "perspective(600px) rotateX(${x}deg) rotateY(${y}deg) scale(${s})"

    Animated.div {
        attrs {
            className = "card"
            onMouseMove = { (x, y) -> setSpring(set) { xys=calc(x,y) } }
            onMouseLeave = { setSpring(set) { xys=arrayOf(.0,.0,1.0) } }
            style = springProps { transform=props.xys.interpolate(::trans) }
        }
    }
}

private operator fun MouseEvent.component1() = clientX
private operator fun MouseEvent.component2() = clientY