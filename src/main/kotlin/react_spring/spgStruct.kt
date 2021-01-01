package react_spring

import kotlinext.js.jsObject
import kotlinx.css.RuleContainer
import kotlinx.css.StyledElement
import kotlinx.html.CommonAttributeGroupFacade
import org.w3c.dom.events.MouseEvent
import react.RProps
import use_gestures.GestureState

abstract class Styles : SpringProps, GestureState/*, StyledElement(), RuleContainer*/

interface AnimatedProps: RProps, CommonAttributeGroupFacade {
    var key: Int
    var style: Styles
    var value: String
    var className: String
    var children: Unit
    var onMouseMove: (MouseEvent)->Unit
    var onClick: (MouseEvent)->Unit
    var onMouseLeave: (MouseEvent)->Unit
}

interface SpringProps : GestureState {
    var from: SpringProps               // Base values, optional
    var to: SpringProps                 // Animates to ...
    var delay: Double                   // Delay in ms before the animation starts. Also valid as a function for individual keys: key => delay
    var immediate: (dynamic)->Boolean   // Prevents animation if true. Also valid as a function for individual keys: key => immediate
    var config: Config                  // Spring config (contains mass, tension, friction, etc). Also valid as a function for individual keys: key => config
    var reset: Boolean                  // The spring starts to animate from scratch (from -> to) if set true
    var reverse: Boolean                // "from" and "to" are switched if set true, this will only make sense in combination with the "reset" flag
    var onStart: ()->Unit               // Callback when a key is about to be animated
    var onReset: ()->Unit               // Callback when all animations come to a stand-still
    var onFrame: ()->Unit               // Frame by frame callback, first argument passed is the animated value
    var opacity: Int
    var xys: Array<dynamic>
    var xyzs: Array<dynamic>
    var transform: dynamic
    var size: Double
    var scale: Double
    var display: String
    var x: Double
    var y: Double
    var zIndex: String
    var shadow: Int
    var bg: String
    var width: Double
    var backgroundImage: String
}

inline fun spring(builder: SpringProps.()->Unit): dynamic {
    return useSpring(jsObject<SpringProps>().apply(builder))
}

inline fun funSpring(crossinline builder: SpringProps.()->Unit): Array<dynamic> {
    return useSpring { jsObject(builder) }
}

fun funSprings(length: Any, builder: SpringProps.(i: Int, ctrl: dynamic) -> Unit): Array<dynamic> {
    return useSprings(length) { i, ctrl -> jsObject { builder(i, ctrl) } }
}

inline fun setSpring(setter: dynamic, builder: SpringProps.()->Unit): dynamic {
    return setter(jsObject(builder))
}

inline fun springProps(builder: SpringProps.()->Unit): dynamic {
    return jsObject(builder)
}