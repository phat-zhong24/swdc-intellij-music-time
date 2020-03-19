package com.softwareco.intellij.plugin.fs;

public class FileManager {

    public static String readmeMdFile = "# Music Time\n\n"
            + "[Music Time](https://www.software.com/music-time) is an IntelliJ plugin that discovers the most productive music to listen to as you code.\n\n"
            + "## Features\n\n"
            + "-   **Integrated player controls**: Control your music right from the status bar of your editor.\n\n"
            + "-   **Embedded playlists**: Browse and play your Spotify and iTunes playlists and songs from your editor.\n\n"
            + "-   **AI playlists**: Get a personalized AI playlist generated using deep learning to help you focus.\n\n"
            + "-   **Metrics profile**: Learn how the underlying metrics of music (including tempo, loudness, speechiness, energy, and valence) impact how you code.\n\n"
            + "-   **Personal top 40**: See your most productive songs, artists, and genres every week in your weekly top 40.\n\n"
            + "-   **Weekly music dashboard**: See your top songs, artists, and genres each week by productivity score and plays while coding.\n\n"
            + "-   **Global top 40**: Discover new music from developers around the world in our Software Top 40 playlist.\n\n"
            + "-   **Slack integration**: Connect Slack to share songs and playlists in channels in your workspace.\n\n"
            + "Music Time currently supports Spotify. We will support iTunes and other players in a future release. You can also check out the [cody-music](https://www.npmjs.com/package/cody-music) npm package for more information about how this extension works.\n\n"
            + "## Getting started\n\n"
            + "### **1. Connect your Spotify account**\n\n"
            + "Click the **Connect Spotify** button in the status bar or in the playlist tree, which will prompt you to log in to your Spotify account.\n\n"
            + "### **2. Control your music and playlists right from your editor**\n\n"
            + "Click on any song in your list of playlists. Music Time will prompt you to open a Spotify player-either the desktop app or web player.\n\n"
            + "⚠ Music Time requires a premium Spotify account and an internet connection to control your music on Windows and Linux. If you are on a Mac, Music Time can also control the Spotify desktop app using AppleScript as either a premium or non-premium user.\n\n"
            + "### **3. Generate your personal playlist**\n\n"
            + "Click the **Generate AI Playlist** button to get a personalized AI playlist generated using deep learning. Your AI Top 40 playlist is initially based on your liked songs and global developer data, but will improve as you listen to more music while you code.\n\n"
            + "### **4. Try a song recommendation**\n\n"
            + "We also recommend songs by genre and mood of music based on your listening history. Try happy, energetic, or danceable music for upbeat work or classical or jazz for slower, more complex tasks. You can add a song to a playlist by right click on a song and then \"Add to playlist\" button.\n\n"
            + "### **5. Like a song**\n\n"
            + "Like a song from the status bar by pressing the \"♡\" button, which helps us improve your song recommendations and adds that song to your Liked Songs playlist on Spotify.\n\n"
            + "### **6. Check out the Software Top 40**\n\n"
            + "Discover new music from developers around the world in a playlist generated by our algorithms. The Software Top 40 playlist is refreshed every week.\n\n"
            + "## Find your most productive music\n\n"
            + "As you listen to music while you code, we calculate a productivity score by combining your coding metrics with your listening history and data from over 10,000 developers.\n\n"
            + "Here are the different ways you can discover your most productive music.\n\n"
            + "### **1. View your web analytics**\n\n"
            + "Click on the \"See web analytics\" button to see your most productive songs, artists, and genres by productivity score. You can also visit app.software.com/login and use your Spotify email address to log in.\n\n"
            + "### **2. Open your Music Time dashboard**\n\n"
            + "Click the \"Music Time\" on the menu bar then **Open Dashboard** to generate an in-editor report of your top songs, artists, and genres by productivity score.\n\n"
            + "### **3. Explore your music metrics**\n\n"
            + "Discover how the underlying [metrics of music](https://app.software.com/music/metrics) (including tempo, loudness, speechiness, energy, and valence) impact how you code.\n\n"
            + "### **4. Visualize your Code Time metrics**\n\n"
            + "Music Time is built on our [Code Time plugin](https://github.com/swdotcom/swdc-intellij). You will be able to see data-such as your keystrokes, time by file and project, and lines of code-which is used calculate to your productivity scores. [Visit your feed](https://app.software.com) to see simple visualizations of your Code Time data, such as a rolling heatmap of your top programming times by hour of the day.\n\n"
            + "## Share your top songs\n\n"
            + "Share your top songs on Facebook, Twitter, WhatsApp, and Tumblr by right click on a song in the playlist tree then share. You can also Connect Slack to share songs with your team.\n\n"
            + "Connecting Slack requires team member permissions or above. You will not be able to connect Slack as a single or multi-channel guest.\n\n"
            + "## Contributing & Feedback\n\n"
            + "Enjoying Music Time? Tweet at us ([@softwaretop40](https://twitter.com/softwaretop40)) and follow us on Instagram ([@softwaretop40](https://www.instagram.com/softwaretop40/)).\n\n"
            + "You can open an issue on a GitHub page or contact us at [support@software.com](mailto:support@software.com) with any additional questions or feedback.\n\n";

    public static String getReadmeMdContent() {
        return readmeMdFile;
    }
}
