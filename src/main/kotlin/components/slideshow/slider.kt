@file:Suppress("UNCHECKED_CAST","UnsafeCastFromDynamic")

package components.slideshow

import Destruct
import kotlinext.js.Object
import kotlinext.js.jsObject
import kotlinx.css.*
import react.*
import react_spring.*
import styled.css
import styled.styled
import use_gestures.IGesture
import use_gestures.gestures
import kotlin.math.floor

val Slider = functionalComponent<SliderProps> { props ->
    val (items, styles_, children_, width_, visible) = with(props) { Destruct(items, styles, children, width, visible) }

    val idx = useCallback({ x: Double ->
        val l = items.size
        (if (x<0) x+l else x) % l
    }, arrayOf(items))

    val getPos = useCallback({ i: Int, firstVis: Double, firstVisIdx: Double ->
        idx(i-firstVis+firstVisIdx)
    }, arrayOf(idx))

    val (springs, set, stop) = funSprings(items.size) { i, _ ->
        x = (if (i<items.size - 1) i.toDouble() else -1.0) * width_
    }

    val prev = useRef(arrayOf(.0, 1.0))
    val wheelOffset = useRef(.0)
    val dragOffset = useRef(.0)

    val runSprings = useCallback({ y: Double, vy: Double ->
        val firstVis = idx(floor(y/width_)%items.size)
        val firstVisIdx = (if (vy<0) items.size-visible-1 else 1).toDouble()

        set { i: Int ->
            val position = getPos(i, firstVis, firstVisIdx)
            val prevPosition = getPos(i, prev.current[0], prev.current[1])
            val rank = firstVis - (if (y<0) items.size else 0) + position - firstVisIdx
            val configPos = if (vy>0) position else items.size-position

            jsObject<SpringProps> {
                x = (-y % (width_ * items.size)) + width_ * rank
                immediate = { if (vy < 0) prevPosition > position else prevPosition < position }
                config = config {
                    mass= 1.0
                    tension= (1 + items.size - configPos) * 100
                    friction= 30 + configPos * 40
                }
            }
        }

        prev.current = arrayOf(firstVis, firstVisIdx)
    }, arrayOf(idx, getPos, width_, visible, set, items.size))

    val bind = gestures {
        onDrag = { states: IGesture ->
            val (offset, vxvy) = with(states) { Pair(offset, vxvy) }

            vxvy[0].doIfZero {
                dragOffset.current = -offset[0]
                runSprings(wheelOffset.current + -offset[0], -vxvy[0])
            }
        }
        onWheel = { states: IGesture ->
            val (offset, vxvy) = with(states) { Pair(offset, vxvy) }

            vxvy[1].doIfZero {
                dragOffset.current = -offset[1]
                runSprings(wheelOffset.current + -offset[1], -vxvy[1])
            }
        }
    }

    val obj = bind()
    styled(Animated.div)() {
        attrs {
            for (key in Object.keys(obj)) {
                val value = obj[key]
                if (value != null) this.asDynamic()[key] = value
            }
            style = jsObject {
                for (key in Object.keys(styles_)) {
                    val value = styles_[key]
                    if (value != null) this.asDynamic()[key] = value
                }
            }
        }
        css {
            position=Position.relative
            height=100.pct
            width=100.pct
        }
        (springs as Array<dynamic>).mapIndexed { i, props ->
            styled(react_spring.a.div)() {
                attrs {
                    key=i
                    style = jsObject {
                        transform=props.x.interpolate { i:Int -> "translate3d(${i.px}, ${0.px}, ${0.px})" }
                        width = width_
                    }
                }
                css {
                    position=Position.absolute
                    height=100.pct
                    put("will-change", "transform")
                }
                child(children_(items[i], i))
            }
        }
    }
}

abstract class SliderProps: RProps {

    abstract var items: Array<Item>
    abstract var styles: dynamic
    abstract var children: (item: Item, i: Int) -> ReactElement
    var width = 600.0
    var visible = 4
}

fun Double.doIfZero(block: ()->Unit) = this.let { if (it!=.0) block() else it }