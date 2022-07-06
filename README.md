# The Shoe Store

This project will consist of five screens. You don't have to create a shoe store, you can use any
other item as long as you create the following screens. You will be creating:

1. Login screen: Email and password fields and labels plus create and login buttons
2. Welcome onboarding screen
3. Instructions onboarding screen
4. Shoe Listing screen
5. Shoe Detail screen for adding a new shoe

## Built With

* [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - View binding is a
  feature that allows you to more easily write code that interacts with views.
* [DataBinding](https://developer.android.com/topic/libraries/data-binding) - The Data Binding
  Library is a support library that allows you to bind UI components in your layouts to data sources
  in your app using a declarative format rather than programmatically.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - LiveData is an
  observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it
  respects the lifecycle of other app components, such as activities, fragments, or services.
* [Room](https://developer.android.com/jetpack/androidx/releases/room) - The Room persistence
  library provides an abstraction layer over SQLite to allow for more robust database access while
  harnessing the full power of SQLite.
* [Coroutines](https://developer.android.com/kotlin/coroutines) - A coroutine is a concurrency
  design pattern that you can use on Android to simplify code that executes asynchronously.
* [Jetpack Navigation](https://developer.android.com/guide/navigation) - It helps in implementing
  navigation, from simple button clicks to more complex patterns
* [ViewPager2](#) - Display Views or Fragments in a swipeable format.
* [Dot Indicator](https://github.com/tommybuonomo/dotsindicator) - This library makes it possible to
  represent View Pager Dots Indicator with 3 different awesome styles.
* [circularprogressbar](https://github.com/lopspower/CircularProgressBar) - This is an Android
  project allowing to realize a circular ProgressBar in the simplest way possible.

## Steps

1. Open the starter project in Android Studio

2. Add the navigation libraries to the app build.gradle file

3. Add the safe-arg plugin to the main and app build.gradle file

4. Create a new navigation xml file

5. Create a new Login destination.

    * Include email and password labels

    - Include email and password fields
    - Create buttons for creating a new login and logging in with an existing account
    - Clicking either button should navigate to the Welcome Screen.

6. Create a new Welcome screen destination that includes:

    * A new layout
    * At least 2 textviews
    * A navigation button with actions to navigate to the instructions screen

7. Create a new Instruction destination that includes:

    * A new layout
    * At least 2 textviews
    * A navigation button with actions to navigate to the shoe list screen

8. Create a class that extends ViewModel

    * Use a LiveData field that returns the list of shoes

9. Create a new Shoe List destination that includes:

    * A new layout
    * A ScrollView
    * A LinearLayout for Shoe Items
    * A FloatingActionButton with an action to navigate to the shoe detail screen

10. In MainActivity, setup the nav controller with the toolbar and an AppBarConfiguration.

11. Create a new Shoe Detail destination that includes:

    * A new layout
    * A TextView label and EditView for the
        * Shoe Name
        * Company
        * Shoe Size
        * Description
    * A Cancel button with an action to navigate back to the shoe list screen
    * A Save button with an action to navigate back to the shoe list screen and add a new Shoe to
      the Shoe View Model

12. Make sure you can’t go back to onboarding screens

13. In the Shoe List screen:

    * Use an Activity level ViewModel to hold a list of Shoes (use by activityViewModels)
    * Observe the shoes variable from the ViewModel
    * Use DataBindingUtil to inflate the shoe_list layout
    * Add a new layout item into the scrollview for each shoe.