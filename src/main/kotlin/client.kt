import components.draggableList.DraggableListStyles
import components.draggableList.dragList
import kotlinx.browser.document
import kotlinx.browser.window
import react.child
import react.dom.render
import react.functionalComponent
import styled.injectGlobal

fun main() {
    injectGlobal(DraggableListStyles.toString())
    window.onload = {
        render(document.getElementById("root")) {
            child(
                functionalComponent {
                    dragList("Lorem ipsum dolor sit".split(" ").toMutableList())
                }
            )
        }
    }
}