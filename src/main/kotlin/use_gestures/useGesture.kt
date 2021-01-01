@file:JsModule("react-use-gesture")
@file:JsNonModule

package use_gestures

import react_spring.Config
import kotlin.js.Json

@JsName("useDrag")
external fun useDrag(handler: (props: GestureState) -> Unit): () -> dynamic

@JsName("useDrag")
external fun useDrag(handler: (props: GestureState) -> dynamic, config: Json?): () -> dynamic

@JsName("useGesture")
external fun useGesture(builder: GestureType, config: Config): () -> dynamic
