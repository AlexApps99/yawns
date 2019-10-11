package io.github.yawnsmod;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.event.HyperlinkEvent;

class Installer {
	public static void main(final String[] args) {
		final JEditorPane ep = new JEditorPane("text/html", "This mod needs to be placed in your Forge mods folder.<br>Visit <a href='https://yawnsmod.github.io'>our website</a> for more info.");

		ep.addHyperlinkListener(e -> {
			if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED) && Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				try {
					Desktop.getDesktop().browse(e.getURL().toURI());
				} catch (IOException | URISyntaxException err) {
					err.printStackTrace();
				}
			}
		});
		
		JOptionPane.showMessageDialog(null, ep, "Creeper? Aw man...", JOptionPane.WARNING_MESSAGE);
	}
}