package components.draggableList

import kotlinx.css.*
import styled.StyleSheet
import styled.css

val DraggableListStyles = CSSBuilder().apply {
    listOf(html, body).forEach { elem ->
        elem {
            margin(0.px)
            padding(0.px)
            height=100.pct
            width=100.pct
            overflow=Overflow.hidden
            userSelect=UserSelect.none
            fontFamily="Raleway, sans-serif"
            display=Display.flex
            justifyContent=JustifyContent.center
            alignItems=Align.center
            background="f0f0f0"
            cursor=Cursor.auto
        }
    }
}