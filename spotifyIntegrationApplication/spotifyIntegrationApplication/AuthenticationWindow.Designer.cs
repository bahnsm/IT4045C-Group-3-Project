
namespace spotifyIntegrationApplication
{
    partial class AuthenticationWindow
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
            this.authenticationBut = new System.Windows.Forms.Button();
            this.testingLbl = new System.Windows.Forms.Label();
            this.updatePlayButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // authenticationBut
            // 
            this.authenticationBut.Location = new System.Drawing.Point(97, 121);
            this.authenticationBut.Name = "authenticationBut";
            this.authenticationBut.Size = new System.Drawing.Size(75, 23);
            this.authenticationBut.TabIndex = 0;
            this.authenticationBut.Text = "Authenticate";
            this.authenticationBut.UseVisualStyleBackColor = true;
            this.authenticationBut.Click += new System.EventHandler(this.authenticationBut_Click);
            // 
            // testingLbl
            // 
            this.testingLbl.AutoSize = true;
            this.testingLbl.Location = new System.Drawing.Point(58, 211);
            this.testingLbl.Name = "testingLbl";
            this.testingLbl.Size = new System.Drawing.Size(0, 13);
            this.testingLbl.TabIndex = 1;
            // 
            // updatePlayButton
            // 
            this.updatePlayButton.Location = new System.Drawing.Point(87, 150);
            this.updatePlayButton.Name = "updatePlayButton";
            this.updatePlayButton.Size = new System.Drawing.Size(94, 23);
            this.updatePlayButton.TabIndex = 2;
            this.updatePlayButton.Text = "Update Player";
            this.updatePlayButton.UseVisualStyleBackColor = true;
            this.updatePlayButton.Click += new System.EventHandler(this.updatePlayerClicked);
            // 
            // AuthenticationWindow
            // 
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.updatePlayButton);
            this.Controls.Add(this.testingLbl);
            this.Controls.Add(this.authenticationBut);
            this.Name = "AuthenticationWindow";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button authenticationBut;
        private System.Windows.Forms.Label testingLbl;
        private System.Windows.Forms.Button updatePlayButton;
    }
}