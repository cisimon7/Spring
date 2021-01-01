@file:Suppress("UnsafeCastFromDynamic")

package components.draggableList

import kotlinext.js.Object
import kotlinext.js.jsObject
import kotlinx.css.*
import react.*
import react.dom.span
import react_spring.SpringProps
import react_spring.Styles
import react_spring.funSprings
import styled.css
import styled.styled
import styled.styledDiv
import swap
import use_gestures.drag
import kotlin.math.abs

val dragList: RBuilder.(item: MutableList<String>) -> ReactElement = { items ->

    console.log("Main: Re-rendered")

    val (springs, set) = funSprings(items.size) { idx, _ ->
        xys = arrayOf(0.0, idx * 120.0, 1.0)
        zIndex = "0"
    }

    val arrangement = useRef(items.mapIndexed { index, _ -> index }.toMutableList())

    val bind = drag {
        val change = movement[1]//.coerceIn(-120.0, 120.0*(items.size+1))

        set { i: Int ->
            val start = args[0]
            val current = arrangement.current.indexOf(i)

            val pos = (current + movement[1]/120).toInt().coerceIn(items.indices)

            if (abs(movement[1]) >= 70)
                arrangement.current = arrangement.current.swap(start as Int, pos)

            if (down && i==args[0])
                jsObject<SpringProps> {
                    xys = arrayOf(0.0, current * 120.0 + change, 1.1)
                    zIndex = "1"
                    immediate = { true }
                }
            else
                jsObject<SpringProps> {
                    xys = arrayOf(0.0, current * 120.0, 1.0)
                    zIndex = "0"
                    immediate = { false }
                }
        }
    }

    styledDiv {
        (springs as Array<dynamic>).mapIndexed { idx, props ->
            val index = arrangement.current[idx]
            child(item(
                jsObject {
                    word = items[index]
                    this.bind= bind(index)
                    anime=jsObject {
                        transform=props.xys.interpolate { _:Double, y:Double, s:Double ->
                            "translate3d(${0.px}, ${y.px}, ${0.px}) scale($s)" }
                        zIndex = props.zIndex
                    }
                    styles={
                        margin(vertical = 5.px)
                        background = back[index]
                        position=Position.absolute
                        width=320.px
                        height=90.px
                        put("transform-origin","50%, 50%, 0px")
                        borderRadius=5.px
                        color=Color.white
                        textTransform=TextTransform.uppercase
                        letterSpacing=2.px
                        put("touch-action", "none")
                    }
                }
            ))
        }
        css {
            position=Position.relative
            width=340.px
            height=455.px
        }
    }
}

val item: RBuilder.(props:IItem) -> ReactElement = { props:IItem ->
    styled(react_spring.a.div)() {
        span { +props.word }
        attrs {
            for (key in Object.keys(props.bind)) {
                val value = props.bind[key]
                if (value != null) this.asDynamic()[key] = value
            }
            style=props.anime
        }
        css {
            +props.styles
            padding(10.px)
        }
    }
}

val back = arrayOf(
    "linear-gradient(135deg, #f6d365 0%, #fda085 100%)",
    "linear-gradient(135deg, #f093fb 0%, #f5576c 100%)",
    "linear-gradient(135deg, #5ee7df 0%, #b490ca 100%)",
    "linear-gradient(135deg, #c3cfe2 0%, #c3cfe2 100%)")

interface IItem : RProps {
    var word: String
    var bind: dynamic
    var anime: Styles
    var styles: CSSBuilder.() -> Unit
    var key: Int
}
