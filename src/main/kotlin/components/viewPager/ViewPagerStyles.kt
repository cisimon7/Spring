package components.viewPager

import kotlinx.css.*
import kotlinx.css.properties.*

val ViewPagerStyles = CSSBuilder().apply {
    listOf(html, body).forEach { elem ->
        elem {
            overscrollBehavior=OverscrollBehavior.contain
            margin(0.px)
            padding(0.px)
            height=100.pct
            width=100.pct
            userSelect=UserSelect.none
            fontFamily="-apple-system, BlinkMacSystemFont, avenir next, avenir, helvetica neue, helvetica, ubuntu, roboto, noto, segoe ui, arial, sans-serif"
            position=Position.fixed
            overflow=Overflow.hidden
        }
    }

    root {
        position=Position.fixed
        overflow=Overflow.hidden
        width=100.pct
        height=100.pct
        cursor=Cursor.auto
        //cursor=Cursor.valueOf("url('https://uploads.codesandbox.io/uploads/user/b3e56831-8b98-4fee-b941-0e27f39883ab/Ad1_-cursor.png') 39 39")
    }

    "#root" { div {
        position=Position.absolute
        width=100.vw
        height=100.vh
        setCustomProperty("will-change", QuotedString("transform"))
    } }

    "#root" { div { div {
        put("touch-action", "none")
        put("will-change", "transform")
        backgroundSize="cover"
        backgroundRepeat=BackgroundRepeat.noRepeat
        backgroundPosition="center center"
        width=100.pct
        height=100.pct
        boxShadow(rgba(0,0,0,0.3), 0.px, 62.5.px, 125.px, (-25).px)
        boxShadow(rgba(0,0,0,0.6), 0.px, 37.5.px, 75.px, (-37.5).px)
    } } }
}