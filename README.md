# MontyMobileAssessment
Android Application that fetches google repos from a remote api. Build using Kotlin.

Network calls handled with Retrofit.

Pagination handled with Android's Paging 3 Library.

Search functionality is applied by simply filtering the flow received from PagingSource.

###  !!IMPORTANT!!
  
  <b>Since the provided api does not allow searches by repository names, the search functionality simply traverses all pages one by one 
  to get the resulting list of repos matching the filter.</b>
  
  ***Hence it's completly inefficient and slow***
  
  Network errors are handled in the PagingSource, a footer containing the error code and a retry button appears on the screen.
  the reload button attemps to reload the last requested page from the network.
  

The Application consists of one main activity containing two fragments:
  - Fragment 1: RepoListFragment: Contains the list of all repositories fetched from the database
  - Fragment 2: Contains the details of the selected repository in fragment 1 (model sent to destination using safeArgs).
Navigation is handled with the navigation component.

Implemented a TimeUtils function to show dates as (... time ago). 

Created a folder to contain all BindingAdapters (contains 2 functions: getTimeAgo and an extension function ImageView.LoadImage)
Images are loaded using glide.

Splash screen implemented using the SplashScreen Api introduced in Android 12 with a compatibility libraries for earlier versions
<p>
<img src="https://user-images.githubusercontent.com/63586731/185801645-6bda7ce3-91be-42c1-8791-dfbe65dcbc47.png" width="190"/>

<img src="https://user-images.githubusercontent.com/63586731/185801273-1f303e17-d873-493e-81bd-32f4388a53ed.png" width="190"/>

<img src="https://user-images.githubusercontent.com/63586731/185801271-0dd2a349-8ae1-4a17-ab79-d7340de2e33b.png" width="190"/>

<img src="https://user-images.githubusercontent.com/63586731/185801603-c9b13fcd-4f23-486c-a8f6-fff502b4c2a8.png" width="190"/>

<img src="https://user-images.githubusercontent.com/63586731/185801275-7dbe5fcb-9607-4703-91dd-34c259a4ed2c.png" width="190"/>
</p>
