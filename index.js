const express = require('express');
const SpotifyWebApi = require('spotify-web-api-node');
const request = require('request');

const app = express();
var port = 3000,
scopes = ['user-read-private user-read-currently-playing user-modify-playback-state user-read-playback-state'],
redirectUri = 'http://localhost:3000/callback',
clientId= 'a1720c5508a7429bb488915d1bd94288',
clientSecret= '59ce06a6194849a48e75bcfb065305bf';

//Configure the style.css
app.use(express.static('public'));

// Configure Spotify API
const spotifyApi = new SpotifyWebApi({
  clientId: clientId,
  clientSecret: clientSecret,
  redirectUri: redirectUri
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

// Authenticate the User
app.get('/login', (req, res) => {
  //console.log(spotifyApi.createAuthorizeURL(scopes));
  res.redirect(spotifyApi.createAuthorizeURL(scopes))
});

app.get('/callback', (req, res) => {
  const code = req.query.code || null;
  console.log(code);
  const authOptions = {
    url: 'https://accounts.spotify.com/api/token',
    form: {
      code: code,
      redirect_uri: redirectUri,
      grant_type: 'authorization_code',
    },
    headers: {
      'Authorization': `Basic ${Buffer.from(`${clientId}:${clientSecret}`).toString('base64')}`,
    },
    json: true,
  };

  request.post(authOptions, (error, response, body) => {
    if (!error && response.statusCode === 200) {
      const accessToken = body.access_token;
      const options = {
        url: 'https://api.spotify.com/v1/me',
        headers: { 'Authorization': `Bearer ${accessToken}` },
        json: true,
      };

      request.get(options, (error, response, body) => {
        // Step 3: Check if the user is a premium user
        if (!error && response.statusCode === 200) {
          const isPremium = body.product === 'premium';
          res.render('status', { isPremium });
        } else {
          res.status(500).send('Error');
        }
      });
    } else {
      res.status(500).send('Error');
    }
  });
});
  

// play
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
