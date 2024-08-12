## MeasureComposable [WIP]

Simple utilities to measure the content of a particular composable!


![](https://github.com/user-attachments/assets/61371762-f450-46c5-a790-e385d0f0cf57)


### Use Case

Often, you come across a use case, where you need to measure the size of a particular composable,
to position or measure another composable, in the same screen, you might need to use a lot of hacks and modifiers. This library will give you a readable
workaround over the same

### Potential Solutions and their Problems

If you go to the developer docs, it will always recommend, using [`onSizeChanged`](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier#(androidx.compose.ui.Modifier).onSizeChanged(kotlin.Function1))
and the [`onGloballyPositioned`](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier#(androidx.compose.ui.Modifier).onGloballyPositioned(kotlin.Function1)) `Modifier`s.

- Using `onSizeChanged` is usually bad, because it can get invoked multiple times, or not at all, leading to recompositions

- Using `onGloballyPositioned` can again get invoked multiple times. Similar one is `onPlaced`

All of these lead to a "Recomposition Loop"

> A Recomposition Loop is a loop leading to a lot of un-ending recompositions. If you measure a composable, and place another composable according to the measured value, if the original value changes, you will place again, since size changes, and this will keep happening

Compose will internally stop a Recomposition Loop, or in worst cases, a certain section of your app will consume a lot of resources, and you might not even realise it, until it gets out of hand


### Proposed Solution

We must measure the composable only once, during the Layout phase. The Compose Compiler requires only one pass to measure the UI
during this phase, to keep it performant. Details on various Compose phases can be found [here](https://developer.android.com/develop/ui/compose/phases))


This library will add two utilities

- `MeasureSize` Composable

Provide a `ComposableSizeMeasurer` instance to the `MeasureSize`,
and wrap your `@Composable` function in it, to measure the size

**Usage**

```kotlin
val composableSize = rememberComposableSizeMeasurer()
MeasureSize(composableSizeMeasurer = composableSize){
    // Content to be measured
}

// Access size else where in the app using the supplied state

composableSize.value.size
```


- `measureSize` Modifier

This can be used for a quick one time measure of your composable

```kotlin
Composable(Modifier.measureSize { size ->
    // Size is measured here, and can be used
    size
})
```
