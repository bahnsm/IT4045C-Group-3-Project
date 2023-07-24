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
Users are able to access full-length songs and artist directories by simply clicking a button within the app.

### **Playback Controls:** 
Basic playback controls such as play, pause, and skip, are available to the users, using Spotify's playback functionality.

### **Intuitive UI/UX:** 
An easy-to-navigate and visually appealing interface is utilized to enhance the user experience.

### **Audio Visualization:** 
The app provides a visual representation of the currently playing song, such as album artwork or a music visualizer.

## Product Backlog
### Sprint 0:
Get Everything Set Up so the rest of the sprints move flawlessly and we are able to produce a good final product

### Sprint 1:
Make a pop-up window appear upon spotify minimizing

### Sprint 2:
Make pop-up window display a toggle-able play pause button and a skip and back button

### Sprint 3:
Make pop-up window additionally display album cover song name and appear less intrusive

## Functional Requirements
### Requirement 1: View Currently-Playing Track
##### Scenario
As a Spotify user, I want to be able to see the name of the song or podcast that's currently playing without switching back to the Spotify window.

#### Example 1
**Given** that I'm listening to Spotify

**When** I open the Spotify Picture-in-Picture app

**Then** I can see the audio track that's currently playing on the Spotify PnP widget.

#### Example 2
**Given** that the Spotify PnP app is active

**When** I want to view a window under the Spotify PnP widget

**Then** I can either minimize the widget or drag it to a more convenient location on my screen.

### Requirement 2: Adjust Playback
##### Scenario
As a Spotify user, I want to be able to pause, play, rewind, and fast-forward audio without switching back to the Spotify window.

#### Example 1
**Given** that I'm listening to Spotify and the Spotify PnP app is active

**When** I want to skip or replay a portion of a track

**Then** I can press a button to instantly rewind or fast-forward by 15 seconds, or click and drag along the progress bar to jump to a specific moment.

#### Example 2
**Given** that I'm listening to Spotify and the Spotify PnP app is active

**When** I want to pause or resume playback

**Then** I can press the pause/play button on the Spotify PnP widget without switching focus from my current task.

## Scrum Roles
**Product Owner:** Marco Bahns

**Scrum Master:** Ben Hicks

**Dev Team:** Keshawn Thomas & Summer Gasaway
