# Spotify Pre-Wrapped
Have you ever sat at home and wondered what are my top-played songs or artists?

With Spotify pre-wrapped this becomes possible. We took inspiration from the infamous Spotify Wrapped which tells users their favorite artists or favorite tracks over the course of a year and implemented that technology into our bite-sized pre-wrapped version. By utilizing Spotify's new API and spring boot technology we are able to deliver a user-friendly experience that not only tells you what you want to know but  also offers an ability to limit the time frame of your top artists, and also the ability to search for songs on Spotify while offering real-time audio-visualization


## Key Features:
### **User Authentication:** 
The app allows users to log in or sign up using their Spotify credentials and access personalized data and playback features.

### **Top Artists Analysis:** 
The app analyzes the user's listening history on Spotify and presents a list of their top artists based on play counts, popularity, or other metrics.

### **Real-time Data Sync:** 
The app is able to sync with the user's Spotify account regularly and constantly updates the list of top artists as the user's listening habits change.

### **Search Functionality:** 
The app allows users to search for songs, albums, artists, and playlists within the Spotify library.

### **Song Playback:** 
Users are able to access history of full-length songs and artist directories by simply clicking a button within the app.

### **Intuitive UI/UX:** 
An easy-to-navigate and visually appealing interface is utilized to enhance the user experience.

### **Audio Visualization:** 
The app provides a visual representation of the currently playing song, such as album artwork or a music visualizer.

## Product Backlog
### Sprint 0:
Get Everything Set Up so the rest of the sprints move flawlessly and we are able to produce a good final product

### Sprint 1:
User Authentication and User Interface

### Sprint 2:
Button Controls and Profile implementation 

### Sprint 3:
Top Artists Analysis and Search Functionality

## Functional Requirements
### Functional Requirement 1: User Authentication
##### Scenario
User: Sarah, a Spotify Premium user.

Sarah opens the Spotify Pre-Wrapped application for the first time. She is greeted with a login screen where she can sign in using her 
Spotify credentials. Sarah enters her Spotify credentials and the application verifies her credentials with Spotify's API. Once verified, 
Sarah is redirected to the main application interface.

#### Example 1
**Given** that Sarah is a Spotify Premium user

**When** she enters her correct Spotify credentials

**Then** she should be authenticated and redirected to the main application interface.

#### Example 2
**Given** that Sarah is a Spotify Premium user

**When** she enters incorrect Spotify credentials

**Then** she should receive an error message and an opportunity to try logging in again.

### Functional Requirement 2: Top Artists Analysis
##### Scenario
User: Mike, a Spotify Free user.

Mike opens the Spotify Pre-Wrapped application and navigates to the 'Top Artists' section. The application retrieves 
Mike's listening history from Spotify's API and analyses it to present a list of Mike's top artists based on his play counts, 
popularity, and other metrics. Mike can now view and interact with this list of top artists.

#### Example 1
**Given** that Mike is on the 'Top Artists' section of the Spotify Pre-Wrapped application

**When** he requests his top artists

**Then** the application should analyze his listening history and present a list of his top artists based on play counts, popularity, and other metrics.

#### Example 2
**Given** that Mike is a Spotify Free user using the 'Top Artists' section of the Spotify Pre-Wrapped application

**When** his listening history does not have sufficient data (for example, he is a new user)

**Then** the application should inform him that more listening history data is needed to generate a top artists list.

### Functional Requirement 3: Song Search and Playback
##### Scenario
User: Emma, a Spotify Premium user.

Emma wants to listen to a specific song while using the Spotify Pre-Wrapped application. She goes to the search bar and types the name of the song. 
The application uses Spotify's search API to find and present a list of matching songs. Emma finds her desired song in the list and clicks on it. 
The song starts playing and Emma can control the playback using the play, pause, and skip buttons.

#### Example 1
**Given** that Emma is a Spotify Premium user and she is in the search section of the Spotify Pre-Wrapped application

**When** she types the name of a specific song and selects the song from the list of results

**Then** the song should start playing and Emma should be able to control the playback using the play, pause, and skip buttons.

#### Example 2
**Given** that Emma is a Spotify Premium user and she is in the search section of the Spotify Pre-Wrapped application

**When** she types the name of a song that doesn't exist or isn't available on Spotify

**Then** the application should inform her that the song is not available.

## Scrum Roles
**Product Owner:** Marco Bahns

**Scrum Master:** Ben Hicks

**Dev Team:** Keshawn Thomas & Summer Gasaway
