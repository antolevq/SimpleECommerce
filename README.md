# SimpleECommerce
## _Simple mocked api application_
This application simulates a purchase process for some products.
The application can also be launched from a completely offline device.

## Dependency and technologies

- [retrofit] - To convert the http api into an interface
- [gson] - To deserialize and serialize json response into a custom object
- [coroutines] - For the management of asynchronous calls
- [glide] - To load a remote image into the imageviews
- [koin] - For the dependency injection
- [mockwebserver] - To integrate repositories test

## Modules
- **Data** - Manages application data. IN this case retrieve data from fake api
- **Domain** - Contains the business Logic
- **App** - Is the presentation layer
- **di** - Contains all koin modules except viewmodels modules 
- **extentions** - Contains some extentions functions useful to the other modules



## Extra
This application was developed using the latest version of Android studio, called Artic fox (both intel and Apple silicon).
In case you want to compile with an older version, you need to change the gradle version from the build.gradle file at the project level.






   [retrofit]: <https://square.github.io/retrofit/>
   [gson]: <https://github.com/google/gson/>
   [coroutines]: <https://developer.android.com/kotlin/coroutines/>
   [glide]: <https://github.com/bumptech/glide/>
   [koin]: <https://insert-koin.io/>
   [mockwebserver]: <https://github.com/square/okhttp/tree/master/mockwebserver/>

   
   
