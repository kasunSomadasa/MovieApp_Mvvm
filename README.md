# The Movie App - Mvvm Architecture

The Movie App - App consuming a [TMDB API](https://developers.themoviedb.org/3) to display movie details. The app has been built with mvvm architecture principles usign kotlin language.
This app shows the usage of the kotlin coroutines, jetpack navigation architecture component in collaboration with the bottom navigation view and this codebase also includes unit testing.

## App features:

 - List of Latest Movies
 - List of Latest Tv Shows
 - Details of the Movies and Tv shows

## App packages:
- data: It contains all the data accessing components.
- repository: Provide business logic and data manipulating components.
- di: Dependency providing classes using Dagger Hilt.
- ui: View classes along with their corresponding ViewModel.
- utils: Utility classes.

## Screenshots:
<p align="center"><kbd><img src="https://user-images.githubusercontent.com/32154905/218305747-dd4388eb-2dad-4207-b2ac-5aead4ba6bec.jpg" width="320"></kbd>&nbsp;&nbsp;&nbsp;&nbsp;<kbd><img src="https://user-images.githubusercontent.com/32154905/218305769-1092546c-efa7-41a0-8a0e-66f7e7ff03d1.jpg" width="320"></kbd><p>

## Architecture:
Model-View-ViewModel (ie MVVM) is a template of a client application architecture, proposed by John Gossman as an alternative to MVC and MVP patterns when using Data Binding technology. Its concept is to separate data presentation logic from business logic by moving it into particular class for a clear distinction.
<p align="center"><br><img src="https://user-images.githubusercontent.com/32154905/218304092-a8d672bb-68cc-4976-9b69-0e9f9ed32844.png" width="1020"><p>

## MVVM Best Pratice:
- Avoid references to Views in ViewModels.
- Instead of pushing data to the UI, let the UI observe changes to it.
- Distribute responsibilities, add a domain layer if needed.
- Add a data repository as the single-point entry to your data.
- Expose information about the state of your data using a wrapper or another LiveData.
- Consider edge cases, leaks and how long-running operations can affect the instances in your architecture.
- Don’t put logic in the ViewModel that is critical to saving clean state or related to data. Any call you make from a ViewModel can be the last one.

## Tech stack

- [Kotlin](https://kotlinlang.org/) - Programming language
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Used to handle gradle dependencies and config versions.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - For reactive style programming (from VM to UI).
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Used get lifecyle event of an activity or fragment and performs some action in response to change.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
- [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Used to navigate between fragments.
- [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Used to bind UI components in your XML layouts.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- [Retrofit](https://github.com/square/retrofit) - Used for REST api communication.
- [OkHttp](https://square.github.io/okhttp/) - HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
- [Glide](https://bumptech.github.io/glide/) - Glide is a fast and efficient image loading library for Android
- [Junit](https://developer.android.com/training/testing/local-tests) - Used as a unit testing framework
- [Truth](https://truth.dev/) - Fluent assertions for Java and Android
