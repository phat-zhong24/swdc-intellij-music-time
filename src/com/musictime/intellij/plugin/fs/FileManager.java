package com.musictime.intellij.plugin.fs;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.musictime.intellij.plugin.KeystrokeCount;
import com.musictime.intellij.plugin.SoftwareCoMusic;
import com.musictime.intellij.plugin.SoftwareCoUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class FileManager {

    public static final Logger log = Logger.getLogger("FileManager");

    private static Timer _timer = null;

    public static String readmeMdFile = "\n" +
            "MUSIC TIME\n" +
            "----------\n" +
            "\n" +
            "Music Time is an IntelliJ plugin that discovers the most productive music to listen to as you code.\n" +
            "\n" +
            "\n" +
            "FEATURES\n" +
            "--------\n" +
            "\n" +
            "-  Integrated player controls: Control your music right from the status bar of your editor.\n" +
            "\n" +
            "-  Embedded playlists: Browse and play your Spotify and iTunes playlists and songs from your editor.\n" +
            "\n" +
            "-  AI playlists: Get a personalized AI playlist generated using deep learning to help you focus.\n" +
            "\n" +
            "-  Metrics profile: Learn how the underlying metrics of music (including tempo, loudness, speechiness, energy, and valence) impact how you code.\n" +
            "\n" +
            "-  Personal top 40: See your most productive songs, artists, and genres every week in your weekly top 40.\n" +
            "\n" +
            "-  Weekly music dashboard: See your top songs, artists, and genres each week by productivity score and plays while coding.\n" +
            "\n" +
            "-  Global top 40: Discover new music from developers around the world in our Software Top 40 playlist.\n" +
            "\n" +
            "-  Slack integration: Connect Slack to share songs and playlists in channels in your workspace.\n" +
            "\n" +
            "Music Time currently supports Spotify. We will support iTunes and other players in a future release.\n" +
            "\n" +
            "\n" +
            "GETTING STARTED\n" +
            "---------------\n" +
            "\n" +
            "1. Connect your Spotify account\n" +
            "\n" +
            "    Click the Connect Spotify button in the status bar or in the playlist tree, which will prompt you to log in to your Spotify account.\n" +
            "\n" +
            "2. Control your music and playlists right from your editor\n" +
            "\n" +
            "    Click on any song in your list of playlists. Music Time will prompt you to open a Spotify player—either the desktop app or web player.\n" +
            "\n" +
            "    NOTE: Music Time requires a premium Spotify account and an internet connection to control your music on Windows and Linux. If you are on a Mac, Music Time can also control the Spotify desktop app using AppleScript as either a premium or non-premium user.\n" +
            "\n" +
            "3. Generate your personal playlist\n" +
            "\n" +
            "    Click the Generate AI Playlist button to get a personalized AI playlist generated using deep learning. Your AI Top 40 playlist is initially based on your liked songs and global developer data, but will improve as you listen to more music while you code. \n" +
            "\n" +
            "4. Try a song recommendation\n" +
            "\n" +
            "    We also recommend songs by genre and mood of music based on your listening history. Try happy, energetic, or danceable music for upbeat work or classical or jazz for slower, more complex tasks. You can add a song to a playlist using the \"+\" button.\n" +
            "\n" +
            "5. Like a song\n" +
            "\n" +
            "    Like a song from the status bar by pressing the \"♡\" button, which helps us improve your song recommendations and adds that song to your Liked Songs playlist on Spotify.\n" +
            "\n" +
            "6. Check out the Software Top 40\n" +
            "\n" +
            "    Discover new music from developers around the world in a playlist generated by our algorithms. The Software Top 40 playlist is refreshed every week.\n" +
            "\n" +
            "\n" +
            "FIND YOUR MOST PRODUCTIVE MUSIC\n" +
            "-------------------------------\n" +
            "\n" +
            "As you listen to music while you code, we calculate a productivity score by combining your coding metrics with your listening history and data from over 10,000 developers.\n" +
            "\n" +
            "Here are the different ways you can discover your most productive music.\n" +
            "\n" +
            "-  View your web analytics\n" +
            "\n" +
            "    Click on the “See web analytics” button to see your most productive songs, artists, and genres by productivity score. You can also visit app.software.com/login and use your Spotify email address to log in.\n" +
            "\n" +
            "-  Open your Music Time dashboard\n" +
            "\n" +
            "    Click the “\uD83C\uDFA7” icon in the status bar then Music Time Dashboard to generate an in-editor report of your top songs, artists, and genres by productivity score.\n" +
            "\n" +
            "-  Explore your music metrics\n" +
            "\n" +
            "    Discover how the underlying metrics of music at app.software.com/music/metrics (including tempo, loudness, speechiness, energy, and valence) impact how you code.\n" +
            "\n" +
            "-  Visualize your Code Time metrics\n" +
            "\n" +
            "    Music Time is built on our Code Time plugin (https://github.com/swdotcom/swdc-intellij). You will be able to see data—such as your keystrokes, time by file and project, and lines of code—which is used calculate to your productivity scores. Visit your feed at app.software.com to see simple visualizations of your Code Time data, such as a rolling heatmap of your top programming times by hour of the day.\n" +
            "\n" +
            "\n" +
            "SHARE YOUR TOP SONGS\n" +
            "--------------------\n" +
            "\n" +
            "Share your top songs on Facebook, Twitter, WhatsApp, and Tumblr by clicking on the share icon next to a song in the playlist tree. You can also Connect Slack to share songs with your team.\n" +
            "\n" +
            "Connecting Slack requires team member permissions or above. You will not be able to connect Slack as a single or multi-channel guest.\n" +
            "\n" +
            "\n" +
            "CONTRIBUTING AND FEEDBACK\n" +
            "-------------------------\n" +
            "\n" +
            "Enjoying Music Time? Tweet at us @softwaretop40 and follow us on Instagram @softwaretop40.\n" +
            "\n" +
            "You can open an issue on a GitHub page or contact us at support@software.com with any additional questions or feedback.";

    public static String getReadmeMdContent() {
        return readmeMdFile;
    }

    public static String getSoftwareDir(boolean autoCreate) {
        String softwareDataDir = SoftwareCoUtils.getUserHomeDir();
        if (SoftwareCoUtils.isWindows()) {
            softwareDataDir += "\\.software";
        } else {
            softwareDataDir += "/.software";
        }

        File f = new File(softwareDataDir);
        if (!f.exists()) {
            // make the directory
            f.mkdirs();
        }

        return softwareDataDir;
    }

    public static String getSoftwareSessionFile(boolean autoCreate) {
        String file = getSoftwareDir(autoCreate);
        if (SoftwareCoUtils.isWindows()) {
            file += "\\session.json";
        } else {
            file += "/session.json";
        }
        return file;
    }

    public static String getMusicDataFile(boolean autoCreate) {
        String file = getSoftwareDir(autoCreate);
        if (SoftwareCoUtils.isWindows()) {
            file += "\\musicData.json";
        } else {
            file += "/musicData.json";
        }
        return file;
    }

    public static String getSongSessionDataFile(boolean autoCreate) {
        String file = getSoftwareDir(autoCreate);
        if (SoftwareCoUtils.isWindows()) {
            file += "\\songSessionData.json";
        } else {
            file += "/songSessionData.json";
        }
        return file;
    }

    public static String getSoftwareDataStoreFile() {
        String file = getSoftwareDir(true);
        if (SoftwareCoUtils.isWindows()) {
            file += "\\data.json";
        } else {
            file += "/data.json";
        }
        return file;
    }

    public static String getMusicDashboardFile() {
        String file = getSoftwareDir(true);
        if (SoftwareCoUtils.isWindows()) {
            file += "\\musicTime.txt";
        } else {
            file += "/musicTime.txt";
        }
        return file;
    }

    public static String getReadmeFile(boolean autoCreate) {
        String file = getSoftwareDir(autoCreate);
        if (SoftwareCoUtils.isWindows()) {
            file += "\\jetbrainsMt_README.md";
        } else {
            file += "/jetbrainsMt_README.md";
        }
        return file;
    }

    public static String getCurrentPayloadFile() {
        String file = getSoftwareDir(false);
        if (SoftwareCoUtils.isWindows()) {
            file += "\\latestKeystrokes.json";
        } else {
            file += "/latestKeystrokes.json";
        }
        return file;
    }

    public synchronized static void storeLatestPayloadLazily(final String data) {
        if (_timer != null) {
            _timer.cancel();
            _timer = null;
        }

        _timer = new Timer();
        if (_timer != null) {
            _timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    FileManager.saveFileContent(FileManager.getCurrentPayloadFile(), data);
                }
            }, 2000);
        }
    }

    public static void saveFileContent(String file, String content) {
        File f = new File(file);

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(f), Charset.forName("UTF-8")));
            writer.write(content);
        } catch (IOException ex) {
            // Report
        } finally {
            try {writer.close();} catch (Exception ex) {/*ignore*/}
        }
    }

    public static JsonObject getFileContentAsJson(String file) {
        JsonParser parser = new JsonParser();
        try {
            Object obj = parser.parse(new FileReader(file));
            JsonObject jsonArray = parser.parse(cleanJsonString(obj.toString())).getAsJsonObject();
            return jsonArray;
        } catch (Exception e) {
            log.warning("Code Time: Error trying to read and parse " + file + ": " + e.getMessage());
        }
        return new JsonObject();
    }

    public static List<KeystrokeCount> getCodeTimePayloads() {
        List<KeystrokeCount> keystrokeCounts = null;
        final String dataStoreFile = FileManager.getSoftwareDataStoreFile();
        File f = new File(dataStoreFile);

        if (f.exists()) {
            // found a data file, check if there's content
            StringBuffer sb = new StringBuffer();
            try {
                FileInputStream fis = new FileInputStream(f);

                //Construct BufferedReader from InputStreamReader
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line.length() > 0) {
                        sb.append(line).append(",");
                    }
                }

                br.close();

                if (sb.length() > 0) {
                    // we have data to send
                    String payloads = sb.toString();
                    payloads = payloads.substring(0, payloads.lastIndexOf(","));
                    payloads = "[" + payloads + "]";

                    JsonArray jsonArray = (JsonArray) SoftwareCoMusic.jsonParser.parse(payloads);
                    // convert to a list of KeystrokeCount
                    Type type = new TypeToken<List<KeystrokeCount>>() {}.getType();
                    keystrokeCounts = SoftwareCoMusic.gson.fromJson(jsonArray, type);
                }
            } catch (Exception e) {
                log.warning("Music Time: Error trying to read and send offline data, error: " + e.getMessage());
            }
        }
        return keystrokeCounts;
    }

    public static String cleanJsonString(String data) {
        data = data.replace("/\r\n/g", "").replace("/\n/g", "").trim();
        return data;
    }
}
