package use_gestures

import kotlinext.js.jsObject
import react.RProps
import react_spring.Config
import react_spring.default

interface GestureState : RProps {
    var down: Boolean
    var delta: dynamic
    var xy: Array<Int>
    val active: Boolean
    val movement: Array<Double>
    val direction: Array<Double>
    val distance: Double
    val cancel: ()->Unit
    val event: dynamic
    val args: Array<Any>        // arguments you passed to bind
    var offset: Array<Double>
    var vxvy: Array<Double>
}

interface IGesture {
    var offset: Array<Double>
    var vxvy: Array<Double>
}

interface GestureType {
    var onDrag: (state:IGesture) -> Unit
    var onDragStart: (state:IGesture) -> Unit
    var onDragEnd: (state:IGesture) -> Unit
    var onPinch: (state:IGesture) -> Unit
    var onPinchStart: (state:IGesture) -> Unit
    var onPinchEnd: (state:IGesture) -> Unit
    var onScroll: (state:IGesture) -> Unit
    var onScrollStart: (state:IGesture) -> Unit
    var onScrollEnd: (state:IGesture) -> Unit
    var onMove: (state:IGesture) -> Unit
    var onMoveStart: (state:IGesture) -> Unit
    var onMoveEnd: (state:IGesture) -> Unit
    var onWheel: (state:IGesture) -> Unit
    var onWheelStart: (state:IGesture) -> Unit
    var onWheelEnd: (state:IGesture) -> Unit
    var onHover: (state:IGesture) -> Unit
}

inline fun gestures(config: Config = Config.default, builder: GestureType.()->Unit): dynamic {
    return useGesture(jsObject<GestureType>().apply(builder), config)
}

fun drag(handler: GestureState.()->Unit): dynamic {
    return useDrag(handler)
}

