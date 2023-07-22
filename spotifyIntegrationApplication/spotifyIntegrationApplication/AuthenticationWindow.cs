using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using SpotifyAPI.Web;
using SpotifyAPI.Web.Auth;
using Swan.Logging;

namespace spotifyIntegrationApplication
{
    public partial class AuthenticationWindow : Form
    {
        public MainPage _mainPage;
        private static EmbedIOAuthServer server = null;
        public CurrentlyPlaying currentlyPlaying;
        private ISpotifyClient _spotifyClient = null;
        private SpotifyClient playerSpotifyClient = null;
        private string _currentTrackName;

        public AuthenticationWindow()
        {
            _mainPage = new MainPage();
            _mainPage.Hide();
            InitializeComponent();
        }

        private async void AuthenticationWindow_Load(object sender, EventArgs e)
        {
            await AuthenticateSpotify(); 
        }

        // ASYNC TASKS //

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
            BrowserUtil.Open(request.ToUri() );

        }

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

            await UpdatePlayer(_spotifyClient);
            var holder = "";
        }

        private async Task UpdatePlayer(SpotifyClient spotifyClient)
        {
            playerSpotifyClient = spotifyClient;
            var playing = await GetCurrentlyPlayingAsync(playerSpotifyClient);
            
            if(playing == null)
            {
                testingLbl.Text = "Nothing is currently playing";
                return;
            }

            if (playing.Item.Type == ItemType.Track)
            {
                //await NotifyTrackUpdate(playing.Item as FullTrack);
                FullTrack track = playing.Item as FullTrack;
                MessageBox.Show($"Now playing: {track.Name}");
                var holder = "";
            }

        }

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
                return playing;
            }

            catch (SpotifyAPI.Web.APIException e) 
            {
                Logger.Error(e.Response.Body.ToString());
            }

            return null;
        }

        private async Task NotifyTrackUpdate(FullTrack track)
        {
            if(track.Name == _currentTrackName) 
            {
                return;
            }

            _currentTrackName = track.Name;
            testingLbl.Text = _currentTrackName;
        }

        private static async Task OnErrorReceived(object sender, string error, string state)
        {
            Console.WriteLine($"Aborting authorization, error received: {error}");
            await server.Stop();
        }

        // BUTTON MANAGEMENT //
        private void authenticationBut_Click(object sender, EventArgs e)
        {
            Task.Run(AuthenticateSpotify);
        }
    }
}
