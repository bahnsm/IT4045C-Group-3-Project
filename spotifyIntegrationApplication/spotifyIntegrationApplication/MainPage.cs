using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using SpotifyAPI.Web;
using SpotifyAPI.Web.Auth;
using Image = System.Drawing.Image;
using Swan.Logging;

namespace spotifyIntegrationApplication
{
    public partial class MainPage : Form
    {
        private static EmbedIOAuthServer server = null;
        public CurrentlyPlaying currentlyPlaying;

        private ISpotifyClient _spotifyClient = null;
        private SpotifyClient playerSpotifyClient = null;
        private string _currentTrackName;
        private bool isUserAuthenticated = false;
        private bool isPlayingLocalVar = false;
        private Image albumArtImage = null;
        public MainPage()
        {
            InitializeComponent();
            // Step 1: Authenticate User
            Task.Run(AuthenticateSpotify);
            // Step 2: Get Song Data
            // wait/having something waiting for the authentication to be successful
        }

        public void UpdatePlayerUI(FullTrack track, string artists, string albumURL)// , Image albumArt
        {
            string trackString = track.Name.ToString();
            string artistString = artists;

            //lblArtistName.Text = artistString;
            if (lblSongTitle.InvokeRequired)
            {
                lblSongTitle.Invoke(new Action(() =>
                {
                    lblSongTitle.Text = trackString;
                    lblSongTitle.Invalidate();
                    lblSongTitle.Update();
                }));
                
                lblArtistName.Invoke(new Action(() =>
                {
                    lblArtistName.Text = artistString;
                    lblArtistName.Invalidate();
                    lblArtistName.Update();
                }));

                imgAlbumArt.LoadAsync(albumURL);
                imgAlbumArt.SizeMode = PictureBoxSizeMode.StretchImage;
            }
            else
            {
                lblSongTitle.Text = trackString;
                lblArtistName.Text = artistString;
            }

            
        }

        // TASKS //

        private async Task AuthenticateSpotify()
        {
            // Setting the Spotify Client ID and Secret
            string clientId = "6fe102abd91a4f578fdabae42348a017";
            string clientSecret = "deded7d898d9457099b97e1bf691f004";

            // creating and starting the authentication server
            var server = new EmbedIOAuthServer(new Uri("http://localhost:3000/callback"), 3000);
            await server.Start();

            server.AuthorizationCodeReceived += OnAuthorizationCodeReceived;
            server.ErrorReceived += OnErrorReceived;

            // get the url for user authentication
            var request = new LoginRequest(server.BaseUri, clientId, LoginRequest.ResponseType.Code)
            {
                // setting the scopes
                Scope = new List<string>() { Scopes.UserReadCurrentlyPlaying, Scopes.UserModifyPlaybackState }
            };
            BrowserUtil.Open(request.ToUri());

        }

        /// <summary>
        /// An async task that runs when an authorization code is received; currently subscribed to 
        /// server.AuthorizationCodeReceived
        /// Connects the user to this application and makes the connection between Spotify servers and client
        /// Also calls the UpdatePlayer task to immediately populate the player
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="response"></param>
        /// <returns></returns>
        private async Task OnAuthorizationCodeReceived(object sender, AuthorizationCodeResponse response)
        {
            string clientId = "6fe102abd91a4f578fdabae42348a017";
            string clientSecret = "deded7d898d9457099b97e1bf691f004";

            var config = SpotifyClientConfig.CreateDefault();
            var tokenResponse = await new OAuthClient(config).RequestToken(
                new AuthorizationCodeTokenRequest(
                    clientId, clientSecret, response.Code, new Uri("http://localhost:3000/callback")
                    )
                );

            var _spotifyClient = new SpotifyClient(tokenResponse.AccessToken);

            if (_spotifyClient != null)
            {
                isUserAuthenticated = true;
                await UpdatePlayer(_spotifyClient);
            }
            else
            {
                return;
            }
        }

        /// <summary>
        /// An async task that handles the player information; currently in use instead of notifyTrack
        /// needs to be replaced since it's not as organized nor efficient as splitting up the different
        /// attributes of songs into their own tasks
        /// </summary>
        /// <param name="spotifyClient"></param>
        /// <returns></returns>
        private async Task UpdatePlayer(SpotifyClient spotifyClient)
        {
            playerSpotifyClient = spotifyClient;
            var playing = await GetCurrentlyPlayingAsync(playerSpotifyClient);

            if (playing == null)
            {
                return;
            }

            if (playing.Item.Type == ItemType.Track)
            {
                FullTrack track = playing.Item as FullTrack;
                string artistList = string.Join(", ", track.Artists?.Select(a => a?.Name));

                string albumArtUrl = track.Album?.Images.FirstOrDefault()?.Url;

                string holding = "";
                UpdatePlayerUI(track, artistList, albumArtUrl);
            }

        }

        /// <summary>
        /// An async task that gets whatever is currently playing
        /// </summary>
        /// <param name="spotifyClient"> the authenticated Spotify client/basically the connection
        /// between the application and Spotify servers for that given user</param>
        /// <returns> returns a CurrentlyPlaying variable</returns>
        private async Task<CurrentlyPlaying> GetCurrentlyPlayingAsync(SpotifyClient spotifyClient)
        {
            _spotifyClient = spotifyClient;
            try
            {
                if (_spotifyClient is null)
                {
                    return null;
                }
                var playing = await _spotifyClient.Player.GetCurrentlyPlaying(new PlayerCurrentlyPlayingRequest(PlayerCurrentlyPlayingRequest.AdditionalTypes.All));
                if (playing.IsPlaying == true)
                {
                    isPlayingLocalVar = true;
                }
                else
                {
                    isPlayingLocalVar = false;
                }
                
                return playing;
            }

            catch (SpotifyAPI.Web.APIException e)
            {
                Logger.Error(e.Response.Body.ToString());
            }

            return null;
        }

        /// <summary>
        /// An async task that handles a track and will change the correct information for when
        /// the track changes
        /// CURRENTLY DOESN'T WORK BUT IN DEVELOPMENT FOR ERROR HANDLING 
        /// </summary>
        /// <param name="track"></param>
        /// <returns></returns>

        private async Task NotifyTrackUpdate(FullTrack track)
        {
            if (track.Name == _currentTrackName)
            {
                return;
            }

            _currentTrackName = track.Name;
        }

        /// <summary>
        /// An async task that stops the server if something goes wrong in authentication
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="error"> what the error is </param>
        /// <param name="state"></param>
        /// <returns></returns>

        private static async Task OnErrorReceived(object sender, string error, string state)
        {
            Console.WriteLine($"Aborting authorization, error received: {error}");
            await server.Stop();
        }

        /// <summary>
        /// A function that I would love to use to simplify this whole process
        /// </summary>
        public void GetSongData()
        {
            //get artist info
            //get album image
            //get song info
        }

        private async void btnPlay_OnClick(object sender, EventArgs e)
        {
            if (isPlayingLocalVar = false)
            {
                await _spotifyClient.Player.ResumePlayback();
            }
            else
            {
                return;
            }
            
        }

        private void lblSongTitle_onTrackTitleChanged(object sender, EventArgs e)
        {

        }

        private async void btnPause_onClick(object sender, EventArgs e)
        {
            if (isPlayingLocalVar = true)
            {
                await _spotifyClient.Player.PausePlayback();
            }
            else
            {
                return;
            }
        }

        private async void btnForward_onClick(object sender, EventArgs e)
        {
            await _spotifyClient.Player.SkipNext();
        }

        private async void btnBack_onClick(object sender, EventArgs e)
        {
            await _spotifyClient.Player.SkipPrevious();
        }

        private void lblArtistName_onTextChanged(object sender, EventArgs e)
        {

        }

        private void imgAlbumArt_onAlbumArtChanged(object sender, EventArgs e)
        {

        }
    }
}
