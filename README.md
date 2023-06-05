# Spotify Picture-in-Picture Application
The Picture-in-Picture (PiP) project for Spotify aims to enhance the user experience by introducing a seamless multitasking feature that allows users to enjoy their favorite music while engaging in other activities on their device. This project leverages the power of Picture-in-Picture technology to bring a miniaturized, resizable player overlay to the Spotify application, enabling users to play pause skip and backtrack music while using other apps or browsing the web.

## Key Features:
### **Resizable and Movable:** 
The overlay will be resizable and movable, giving users the flexibility to adjust its size and position according to their preferences and screen real estate. This feature ensures that users can seamlessly integrate the PiP player into their workflow without obstructing important content or interactions.

### **Multitasking Support:** 
By implementing Picture-in-Picture, users will be able to listen to their favorite music on Spotify while simultaneously using other applications, such as messaging, browsing, or productivity tools. This feature enhances productivity and entertainment possibilities, allowing users to have a truly immersive and seamless multitasking experience.

### **User-Friendly Controls:** 
The player will include intuitive controls, enabling users to effortlessly interact with the Spotify app without disrupting their primary tasks. 

### **Customization Options:** 
To cater to individual preferences, the PiP integration will offer customization options such as theme selection, transparency adjustments, and display preferences. These options will allow users to personalize their experience, ensuring it aligns with their aesthetic preferences and usage patterns.

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

