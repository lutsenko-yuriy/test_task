# The Coding Exercise

## Technologies used:
* **[Kotlin](https://kotlinlang.org/)** (the task was to write the app in Kotlin)
* **[Koin](https://insert-koin.io/)** as a DI Framework (Deploying Koin is way faster than Dagger so at least for small projects it makes sense to use Koin instead of Dagger)
* **[Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)** (seems well-working along with the architecture components. The codebase is definitely more readable than an analogous codebase with RxJava.)
* **[Android Architecture Components](https://developer.android.com/topic/libraries/architecture)**
* **[Room](https://developer.android.com/training/data-storage/room/index.html)**
* **[Retrofit](https://square.github.io/retrofit/)**

Using coroutines has its own limitations. There were a lot of articles/StackOverflow questions/Forum Discussions on testing but they were not very helpful ((example)[https://github.com/lutsenko-yuriy/test_task/blob/master/app/src/test/java/com/yurich/test_task/viewmodels/MainActivityViewModelTest.kt]). I hope that the new version of kotlin-coroutines-test will solve most of these problems.

The app itself can also be improved. Here are a couple of examples:
- Using icons or covers of albums in the list of albums;
- Using real user's name in descriptions of the albums instead of their IDs.
