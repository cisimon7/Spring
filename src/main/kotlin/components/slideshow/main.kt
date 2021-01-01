package components.slideshow

import kotlinext.js.js
import kotlinext.js.jsObject
import kotlinx.css.*
import react.RProps
import react.child
import react.functionalComponent
import styled.css
import styled.styled
import styled.styledDiv
import styled.styledSpan

val SlideShow = functionalComponent<RProps> {
    styledDiv {
        css { height=400.px }
        child(
            component = Slider,
            props = jsObject {
                visible = 3
                width = 700.0
                items = itemList
                styles = js {  }
                children = { item: Item, i:Int ->
                    val (css, _) = item

                    styledDiv {
                        css {
                            height = 100.pct
                            width = 100.pct
                            padding(70.px, 100.px)
                        }

                        styledSpan {
                            css {
                                color = Color.white
                                position = Position.absolute
                                top = 0.px
                                left = 140.px
                                fontFamily = "monospace"
                            }
                            +"$i".padStart(2, '0')
                        }

                        styled(react_spring.a.div)() {
                            css {
                                width = 100.pct
                                height = 100.pct
                                backgroundSize = "cover"
                                backgroundPosition = "center center"
                                backgroundImage = Image(css)
                            }
                        }
                    }
                }
            }
        )
    }
}
