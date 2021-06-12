# walmart-challenge

# TO RUN THE CODE

- Checkout the code in local
- Open the project with android studio
- Configure local sdk if needed
- Connect emulator or device
- Click run

## Considerations 

-The code uses MVVM to connect the data layer with the views, also a network layer with retrofit and a local storage with room, while the database is not yet connected
it can be connected in the future, proving the concept of stage integrations.
-The architecture uses Databinding and a set of BASE elements that can be replicated easily for multiple features
-All the libraries and tools are used because they simplify a lot of manual work that is required for client / server applications

## Libraries

### Navigation library
- This library is used to navigate between fragments using one single activity as source in this project, but the library has more capabilities than that
- implementation "androidx.navigation:navigation-fragment-ktx:2.3.1"
- implementation "androidx.navigation:navigation-ui-ktx:2.3.1"

### Google dagger library
- This library helps to develop faster big applications by injecting dependencies creating source code, 
- implementation "com.google.dagger:dagger:2.27"
- kapt "com.google.dagger:dagger-compiler:2.16"


### Glide image loading
- This library is used to put the https image into the view in the adapters and the movie detail
- implementation "com.github.bumptech.glide:glide:4.11.0"

### custom adapter
- This library has a set of utilities to make adapters repetitive work more easy
- implementation 'com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.0'

### Retrofit and gson and okhttp
- While retrofit helps to create data objects from services and API's, gson gives the flexibility to create multiple type converters for more complex objects
- Okhttp is the network layer client

### Android Room
- Android room is a local database that is easily to integrate with retrofit to store information locally, while it was not fully used or required, I introduced it as a POC.

