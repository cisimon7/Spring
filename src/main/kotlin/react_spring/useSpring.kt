@file:JsModule("react-spring")
@file:JsNonModule

package react_spring

import react.*

@JsName("useSpring")
external fun useSpring(props: SpringProps): dynamic

@JsName("useSpring")
external fun useSpring(props: () -> SpringProps): Array<dynamic>

@JsName("useSprings")
external fun useSprings(length: Any, props: (i: Int, ctrl: dynamic) -> SpringProps): Array<dynamic>

@JsName("animated")
external class Animated(component: Any) : Component<RProps, RState> {

    override fun render(): ReactElement

    @JsName("div")
    class Div : Component<AnimatedProps, RState> {
        override fun render(): ReactElement
    }

    companion object {
        @JsName("div")
        val div: RClass<AnimatedProps>
    }
}

@JsName("a")
external class a(component: Any) : Component<RProps, RState> {

    override fun render(): ReactElement

    @JsName("div")
    class a : Component<AnimatedProps, RState> {
        override fun render(): ReactElement
    }

    companion object {
        @JsName("div")
        val div: RClass<AnimatedProps>
    }
}