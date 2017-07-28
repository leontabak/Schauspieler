# Schauspieler
To use this project: Add your own key to the themoviedb.org service in the MoviesDescriptionsTask class. A comment near the
top of the definition of that class provides instructions.

I have improved the previous version of this program by:
- using the **popular** and **top_rated** methods of the API provided by themoviedb.org rather than
the **find** method
- retrieving the (already sorted) descriptions of popular or top rated movies rather than retrieving
descriptions of movies that I had selected (I had a list of movies with references to computer science
and mathematics) and then sorting those movies using my own code and either 
criteria
- replaced some string literals with references to strings defined in /res/values/strings.xml
- placed the code that defines my class that extends AsyncTask in its own file, rather than define it as an inner class
- removing some now unused code and calls to logging methods

I received recommendations for other improvements that I have not yet made:
- I looked at the Parcelable interface as recommended but chose not to use it at this time---this version still uses a Serialiable class to pass information through an Intent to an activity
- I read more about the cost of using Enums but did not completely eliminate my use of them in this version of the program
- it is possible to programmatically determine how many columns to use in a grid (to fit the size of the screen)---I have not yet experimented with this

This program:
- Provides information about top rated and popular movies
or mathematics 
- Displays images of the movies' posters in a 2 column grid
- Sorts the movies by ratings or popularity
- Responds to a click on a poster by displaying additional information about the movie
- Responds to a click on the button at the bottom of the description by returning to
the catalog of movies (the main page)
- Connects with the themoviedb.org service. It fetches:
    - the original title
    - overview (plot summary),
    - a numerical measure of the movie's popularity
    - an average of ratings given to the movie by reviewers
    - the date on which the movie was released
    - a URL for fetching an image of the movie's poster
- Uses the **popularity** or **top_rated** methods of the themoviedb.org service

In writing this program, I learned:
- how to use the Picasso image loading API and the themoviedb.org movie database API
- how to use RecyclerViews, Adapters, and ViewHolders
- how to use GridLayoutManager
- how to extend AsyncTask to perform tasks on a background thread
- how to create multiple Activities
- how to send information from one Activity to another using an Intent and a serializable object
- how to use the **singleton** pattern
- how to use resource files to specify dimensions, margins, paddings, colors, values of strings
- how to use Android Studio (especially features that help with debugging: logging, logcat,
shortcuts for commenting/uncommenting blocks of code, **Toasts** and conveniences such as code completion)
