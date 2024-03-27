package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import java.awt.*; 
import javax.swing.JOptionPane;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	
		public static void main (String[] arg) {
			if ( GetScreenWidth() != 1920 || GetScreenHeight() != 1080 ) { //if screen is not 1920 x 1080 @ 100%
				if (JOptionPane.showConfirmDialog(null, "This application is best suited for 1920x1080 (Scale size: 100%) resolution. "
						+ "Do you still want to continue?", "Resolution Problem", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){ //ask question, if answer is no
					System.exit(0); //kill program
				}
			}
			Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
			config.setForegroundFPS(60); //max fps
			config.setTitle("Aunty's Kopitiam"); //game title
			config.setWindowedMode(GetScreenWidth(), GetScreenHeight()); //fullscreen
			config.setDecorated(false); //borderless fullscreen
			config.useVsync(true); //v-sync
			new Lwjgl3Application(new GameAssets(), config); //initialize game
		}
	
	   public static int GetScreenWidth() {
		   Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		   return (int)size.getWidth();
	   }

	   public static int GetScreenHeight() {
		   Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		   return (int)size.getHeight();
	   }
}
