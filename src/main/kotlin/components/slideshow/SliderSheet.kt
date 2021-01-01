package components.slideshow

import kotlinx.css.*

val SlideShowStyles = CSSBuilder().apply {

    "*" {
        boxSizing=BoxSizing.borderBox
    }

    listOf(html, body).forEach { elem ->
        elem {
            margin(0.px)
            padding(0.px)
            height=100.pct
            width=100.pct
            overflow=Overflow.hidden
        }
    }

    body {
        background="transparent"
        userSelect= UserSelect.none
        cursor= Cursor.default
        fontFamily="-apple-system, BlinkMacSystemFont, avenir next, avenir, helvetica neue, helvetica, ubuntu, roboto, noto, segoe ui, arial, sans-serif"
    }

    "#root" {
        width=100.pct
        height=100.pct
        margin(0.px)
        padding(0.px)
        backgroundColor= Color.white
        display= Display.flex
        alignItems= Align.center
        background="#171720"
    }
}