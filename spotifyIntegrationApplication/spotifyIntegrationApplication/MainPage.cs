using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace spotifyIntegrationApplication
{
    public partial class MainPage : Form
    {
        public MainPage()
        {
            InitializeComponent();
            GetSongData();
        }

        public void GetSongData()
        {
            //get artist info
            //get album image
            //get song info
        }

        private void btnPlay_OnClick(object sender, EventArgs e)
        {

        }

        private void lblSongTitle_onTrackTitleChanged(object sender, EventArgs e)
        {

        }

        private void btnPause_onClick(object sender, EventArgs e)
        {

        }

        private void btnForward_onClick(object sender, EventArgs e)
        {

        }

        private void btnBack_onClick(object sender, EventArgs e)
        {

        }

        private void lblArtistName_onTextChanged(object sender, EventArgs e)
        {

        }

        private void imgAlbumArt_onAlbumArtChanged(object sender, EventArgs e)
        {

        }
    }
}
