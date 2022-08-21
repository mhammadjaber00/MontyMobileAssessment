# MontyMobileAssessment
Android Application that fetches google repos from a remote api. Build using Kotlin.

Network calls handled with Retrofit.

Pagination handled with Android's Paging 3 Library.

Search functionality is applied by simply filtering the flow received from PagingSource.

  !!IMPORTANT!!
  
  Since the provided api does not allow searches by repository names, the search functionality simply traverses all pages one by one 
  to get the resulting list of repos matching the filter.
  (Hence it's completly inefficient and slow)
  Network errors are handled in the PagingSource, a footer containing the error code and a retry button appears on the screen.
  the reload button attemps to reload the last requested page from the network.
  

The Application consists of one main activity containing two fragments:
  - Fragment 1: RepoListFragment: Contains the list of all repositories fetched from the database
  - Fragment 2: Contains the details of the selected repository in fragment 1 (model sent to destination using safeArgs).
Navigation is handled with the navigation component.

Implemented a TimeUtils function to show dates as (... time ago). 

Created a folder to contain all BindingAdapters (contains 2 functions: getTimeAgo and an extension function ImageView.LoadImage)
Images are loaded using glide.
