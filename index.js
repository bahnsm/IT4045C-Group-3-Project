const express = require('express');
const SpotifyWebApi = require('spotify-web-api-node');

const app = express();
const port = 3000;

//Configure the style.css
app.use(express.static('public'));

// Configure Spotify API
const spotifyApi = new SpotifyWebApi({
  clientId: 'a1720c5508a7429bb488915d1bd94288',
  clientSecret: '59ce06a6194849a48e75bcfb065305bf',
});

// Set up the Express app
app.set('view engine', 'ejs');
app.use(express.static('public'));

// Home route
app.get('/', (req, res) => {
  res.render('index');
});

// Search route
app.get('/search', (req, res) => {
  const { query } = req.query;
  
  spotifyApi.searchTracks(query)
    .then((data) => {
      const tracks = data.body.tracks.items;
      res.render('search', { tracks });
    })
    .catch((err) => {
      console.log('Error searching tracks:', err);
      res.render('error');
    });
});

// Play route
app.get('/play/:trackId', (req, res) => {
  const { trackId } = req.params;

  spotifyApi.play({ uris: [`spotify:track:${trackId}`] })
    .then(() => {
      console.log('Playback started');
      res.redirect('/');
    })
    .catch((err) => {
      console.log('Error starting playback:', err);
      res.render('error');
    });
});

// Authenticate with Spotify API
spotifyApi
  .clientCredentialsGrant()
  .then((data) => {
    spotifyApi.setAccessToken(data.body.access_token);
    console.log('Authenticated with Spotify API');
    app.listen(port, () => {
      console.log(`Server running on http://localhost:${port}`);
    });
  })
  .catch((err) => {
    console.log('Error authenticating with Spotify API:', err);
  });
