package components.card3D

import kotlinx.css.*
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.s
import kotlinx.css.properties.transition

val Card3DStyles = CSSBuilder().apply {
    root {
        width=100.pct
        height=100.pct
        margin(0.px)
        padding(0.px)
        backgroundColor= Color.white

        display=Display.flex
        alignItems=Align.center
        justifyContent=JustifyContent.center
        overflow=Overflow.hidden
    }

    body {
        background="transparent"
        userSelect=UserSelect.none
        cursor=Cursor.default
        fontFamily="-apple-system, BlinkMacSystemFont, avenir next, avenir, helvetica neue, helvetica, ubuntu, roboto, noto, segoe ui, arial, sans-serif"
    }

    val image = "url(https://drscdn.500px.org/photo/435236/q%3D80_m%3D1500/v2?webp=true&sig=67031bdff6f582f3e027311e2074be452203ab637c0bd21d89128844becf8e40)"

    ".card" {
        width=300.px
        height=300.px
        backgroundColor=Color.gray
        borderRadius=5.px
        backgroundImage=Image(image)
        backgroundSize="cover"
        backgroundPosition="center center"
        boxShadow(rgba(0,0,0,0.3), 0.px, 10.px, 30.px, (-5).px)
        transition("box-shadow", 0.5.s)
    }
}
