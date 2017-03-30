# Schauspieler
To use this project: Add your own key to the themoviedb.org service in the constructor for the MovieQueries class.

This program:
- Provides information about movies that all contain references to computer science
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
- Uses the **find** method of the themoviedb.org service (with IMDB identification numbers for the movies)

In writing this program, I learned:
- how to use the Picasso image loading API and the themoviedb.org movie database API
- how to use RecyclerViews, Adapters, and ViewHolders
- how to use GridLayoutManager
- how to create multiple Activities
- how to send information from one Activity to another using an Intent and a serializable object
- how to use the **singleton** pattern
- how to use resource files to specify dimensions, margins, paddings, colors, values of strings
- how to use Android Studio (especially features that help with debugging: logging, logcat, code completion,
shortcuts for commenting/uncommenting blocks of code, **Toasts**)
