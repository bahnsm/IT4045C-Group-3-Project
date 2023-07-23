
namespace spotifyIntegrationApplication
{
    partial class MainPage
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainPage));
            this.ctrlPanel = new System.Windows.Forms.Panel();
            this.btnPlay = new System.Windows.Forms.Button();
            this.btnPause = new System.Windows.Forms.Button();
            this.btnForward = new System.Windows.Forms.Button();
            this.btnBack = new System.Windows.Forms.Button();
            this.errorProvider1 = new System.Windows.Forms.ErrorProvider(this.components);
            this.lblSongTitle = new System.Windows.Forms.Label();
            this.lblArtistName = new System.Windows.Forms.Label();
            this.imgAlbumArt = new System.Windows.Forms.PictureBox();
            this.ctrlPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.imgAlbumArt)).BeginInit();
            this.SuspendLayout();
            // 
            // ctrlPanel
            // 
            this.ctrlPanel.Controls.Add(this.btnPlay);
            this.ctrlPanel.Controls.Add(this.btnPause);
            this.ctrlPanel.Controls.Add(this.btnForward);
            this.ctrlPanel.Controls.Add(this.btnBack);
            this.ctrlPanel.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.ctrlPanel.Location = new System.Drawing.Point(0, 93);
            this.ctrlPanel.Margin = new System.Windows.Forms.Padding(2);
            this.ctrlPanel.Name = "ctrlPanel";
            this.ctrlPanel.Size = new System.Drawing.Size(296, 62);
            this.ctrlPanel.TabIndex = 0;
            // 
            // btnPlay
            // 
            this.btnPlay.AutoSize = true;
            this.btnPlay.BackColor = System.Drawing.Color.Transparent;
            this.btnPlay.Image = ((System.Drawing.Image)(resources.GetObject("btnPlay.Image")));
            this.btnPlay.Location = new System.Drawing.Point(158, 14);
            this.btnPlay.Margin = new System.Windows.Forms.Padding(2);
            this.btnPlay.Name = "btnPlay";
            this.btnPlay.Size = new System.Drawing.Size(36, 36);
            this.btnPlay.TabIndex = 3;
            this.btnPlay.UseVisualStyleBackColor = false;
            this.btnPlay.Click += new System.EventHandler(this.btnPlay_OnClick);
            // 
            // btnPause
            // 
            this.btnPause.AutoSize = true;
            this.btnPause.BackColor = System.Drawing.Color.Transparent;
            this.btnPause.Image = ((System.Drawing.Image)(resources.GetObject("btnPause.Image")));
            this.btnPause.Location = new System.Drawing.Point(92, 14);
            this.btnPause.Margin = new System.Windows.Forms.Padding(2);
            this.btnPause.Name = "btnPause";
            this.btnPause.Size = new System.Drawing.Size(36, 36);
            this.btnPause.TabIndex = 2;
            this.btnPause.UseVisualStyleBackColor = false;
            this.btnPause.Click += new System.EventHandler(this.btnPause_onClick);
            // 
            // btnForward
            // 
            this.btnForward.AutoSize = true;
            this.btnForward.BackColor = System.Drawing.Color.Transparent;
            this.btnForward.Image = ((System.Drawing.Image)(resources.GetObject("btnForward.Image")));
            this.btnForward.Location = new System.Drawing.Point(227, 14);
            this.btnForward.Margin = new System.Windows.Forms.Padding(0);
            this.btnForward.Name = "btnForward";
            this.btnForward.Size = new System.Drawing.Size(36, 36);
            this.btnForward.TabIndex = 1;
            this.btnForward.UseVisualStyleBackColor = false;
            this.btnForward.Click += new System.EventHandler(this.btnForward_onClick);
            // 
            // btnBack
            // 
            this.btnBack.AutoSize = true;
            this.btnBack.BackColor = System.Drawing.Color.Transparent;
            this.btnBack.Image = ((System.Drawing.Image)(resources.GetObject("btnBack.Image")));
            this.btnBack.Location = new System.Drawing.Point(29, 14);
            this.btnBack.Margin = new System.Windows.Forms.Padding(2);
            this.btnBack.Name = "btnBack";
            this.btnBack.Size = new System.Drawing.Size(36, 36);
            this.btnBack.TabIndex = 0;
            this.btnBack.UseVisualStyleBackColor = false;
            this.btnBack.Click += new System.EventHandler(this.btnBack_onClick);
            // 
            // errorProvider1
            // 
            this.errorProvider1.ContainerControl = this;
            // 
            // lblSongTitle
            // 
            this.lblSongTitle.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblSongTitle.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblSongTitle.Location = new System.Drawing.Point(101, 17);
            this.lblSongTitle.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.lblSongTitle.Name = "lblSongTitle";
            this.lblSongTitle.Size = new System.Drawing.Size(190, 13);
            this.lblSongTitle.TabIndex = 1;
            this.lblSongTitle.Text = "songTitle";
            this.lblSongTitle.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.lblSongTitle.TextChanged += new System.EventHandler(this.lblSongTitle_onTrackTitleChanged);
            // 
            // lblArtistName
            // 
            this.lblArtistName.Anchor = System.Windows.Forms.AnchorStyles.Right;
            this.lblArtistName.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblArtistName.Location = new System.Drawing.Point(101, 46);
            this.lblArtistName.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.lblArtistName.Name = "lblArtistName";
            this.lblArtistName.Size = new System.Drawing.Size(190, 13);
            this.lblArtistName.TabIndex = 2;
            this.lblArtistName.Text = "ArtistName";
            this.lblArtistName.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            this.lblArtistName.TextChanged += new System.EventHandler(this.lblArtistName_onTextChanged);
            // 
            // imgAlbumArt
            // 
            this.imgAlbumArt.Anchor = System.Windows.Forms.AnchorStyles.Left;
            this.imgAlbumArt.Location = new System.Drawing.Point(14, 6);
            this.imgAlbumArt.Margin = new System.Windows.Forms.Padding(2);
            this.imgAlbumArt.Name = "imgAlbumArt";
            this.imgAlbumArt.Size = new System.Drawing.Size(75, 78);
            this.imgAlbumArt.TabIndex = 3;
            this.imgAlbumArt.TabStop = false;
            this.imgAlbumArt.BackgroundImageChanged += new System.EventHandler(this.imgAlbumArt_onAlbumArtChanged);
            // 
            // MainPage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.LightGreen;
            this.ClientSize = new System.Drawing.Size(296, 155);
            this.Controls.Add(this.imgAlbumArt);
            this.Controls.Add(this.lblArtistName);
            this.Controls.Add(this.lblSongTitle);
            this.Controls.Add(this.ctrlPanel);
            this.Margin = new System.Windows.Forms.Padding(2);
            this.Name = "MainPage";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.TopMost = true;
            this.ctrlPanel.ResumeLayout(false);
            this.ctrlPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.errorProvider1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.imgAlbumArt)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel ctrlPanel;
        private System.Windows.Forms.Button btnPlay;
        private System.Windows.Forms.Button btnPause;
        private System.Windows.Forms.Button btnForward;
        private System.Windows.Forms.Button btnBack;
        private System.Windows.Forms.ErrorProvider errorProvider1;
        private System.Windows.Forms.Label lblSongTitle;
        private System.Windows.Forms.Label lblArtistName;
        private System.Windows.Forms.PictureBox imgAlbumArt;
    }
}

