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
    public partial class AuthenticationWindow : Form
    {
        private MainPage mainPage;
        public AuthenticationWindow()
        {
            InitializeComponent();
        }

        private void onAuthLoad(object sender, EventArgs e)
        {
            // Set the form properties
            this.Text = "Spotify Authentication";
            this.Size = new Size(300, 200);
            this.FormBorderStyle = FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;

            // Set the background color
            this.BackColor = Color.FromArgb(30, 215, 96);

            // Create the logo label
            Label logoLabel = new Label();
            logoLabel.Text = "Spotify";
            logoLabel.Font = new Font("Arial", 24, FontStyle.Bold);
            logoLabel.ForeColor = Color.White;
            logoLabel.TextAlign = ContentAlignment.MiddleCenter;
            logoLabel.Dock = DockStyle.Top;
            logoLabel.Height = 80;
            this.Controls.Add(logoLabel);

            // Create the username label
            Label usernameLabel = new Label();
            usernameLabel.Text = "Username";
            usernameLabel.Font = new Font("Arial", 12, FontStyle.Bold);
            usernameLabel.ForeColor = Color.White;
            usernameLabel.AutoSize = true;
            usernameLabel.Location = new Point(30, 100);
            this.Controls.Add(usernameLabel);

            // Create the username textbox
            TextBox usernameTextbox = new TextBox();
            usernameTextbox.Font = new Font("Arial", 12);
            usernameTextbox.Location = new Point(130, 100);
            usernameTextbox.Size = new Size(140, 25);
            this.Controls.Add(usernameTextbox);

            // Create the password label
            Label passwordLabel = new Label();
            passwordLabel.Text = "Password";
            passwordLabel.Font = new Font("Arial", 12, FontStyle.Bold);
            passwordLabel.ForeColor = Color.White;
            passwordLabel.AutoSize = true;
            passwordLabel.Location = new Point(30, 140);
            this.Controls.Add(passwordLabel);

            // Create the password textbox
            TextBox passwordTextbox = new TextBox();
            passwordTextbox.Font = new Font("Arial", 12);
            passwordTextbox.Location = new Point(130, 140);
            passwordTextbox.Size = new Size(140, 25);
            passwordTextbox.PasswordChar = '*';
            this.Controls.Add(passwordTextbox);

            // Create the login button
            Button loginButton = new Button();
            loginButton.Text = "Login";
            loginButton.Font = new Font("Arial", 12, FontStyle.Bold);
            loginButton.ForeColor = Color.White;
            loginButton.BackColor = Color.FromArgb(30, 215, 96);
            loginButton.FlatStyle = FlatStyle.Flat;
            loginButton.FlatAppearance.BorderSize = 0;
            loginButton.Location = new Point(130, 180);
            loginButton.Size = new Size(140, 30);
            loginButton.Click += LoginButton_Click;
            this.Controls.Add(loginButton);
        }

        private void LoginButton_Click(object sender, EventArgs e)
        {
            // Perform authentication logic here
            // For demonstration purposes, we'll assume the authentication was successful

            // Close the authentication window
            this.Hide();

            // Create the playback form
            mainPage = new MainPage();

            // Subscribe to the FormClosed event to handle cleanup
            mainPage.FormClosed += MainPage_FormClosed;

            // Show the playback form
            mainPage.Show();
        }

        private void MainPage_FormClosed(object sender, FormClosedEventArgs e)
        {
            // Cleanup when the playback form is closed
            this.Close();
        }
    }
}
