package io.github.yawnsmod.hacks;

import io.github.yawnsmod.Hack;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.lwjgl.glfw.GLFW;

public class FancyChat extends Hack {
	public FancyChat() {
		super("Fancy Chat", "Makes the chat ⓕⓐⓝⓒⓨ!", Hack.Category.CHAT, GLFW.GLFW_KEY_EQUAL);
	}
	
	@SubscribeEvent(receiveCanceled=true)
	public void onClientChat(ClientChatEvent event) {
		if (event.getMessage().charAt(0) != '/') {
			event.setMessage(">" + event.getMessage().replace('0', 'ⓞ').replace('1', '➀')
		 		.replace('2', '➁').replace('3', '➂').replace('4', '➃')
		 		.replace('5', '➄').replace('6', '➅').replace('7', '➆')
		 		.replace('8', '➇').replace('9', '➈').replace('a', 'ⓐ')
		 		.replace('b', 'ⓑ').replace('c', 'ⓒ').replace('d', 'ⓓ')
		 		.replace('e', 'ⓔ').replace('f', 'ⓕ').replace('g', 'ⓖ')
		 		.replace('h', 'ⓗ').replace('i', 'ⓘ').replace('j', 'ⓙ')
		 		.replace('k', 'ⓚ').replace('l', 'ⓛ').replace('m', 'ⓜ')
		 		.replace('n', 'ⓝ').replace('o', 'ⓞ').replace('p', 'ⓟ')
		 		.replace('q', 'ⓠ').replace('r', 'ⓡ').replace('s', 'ⓢ')
		 		.replace('t', 'ⓣ').replace('u', 'ⓤ').replace('v', 'ⓥ')
		 		.replace('w', 'ⓦ').replace('x', 'ⓧ').replace('y', 'ⓨ')
		 		.replace('z', 'ⓩ').replace('A', 'Ⓐ').replace('B', 'Ⓑ')
		 		.replace('C', 'Ⓒ').replace('D', 'Ⓓ').replace('E', 'Ⓔ')
		 		.replace('F', 'Ⓕ').replace('G', 'Ⓖ').replace('H', 'Ⓗ')
		 		.replace('I', 'Ⓘ').replace('J', 'Ⓙ').replace('K', 'Ⓚ')
		 		.replace('L', 'Ⓛ').replace('M', 'Ⓜ').replace('N', 'Ⓝ')
		 		.replace('O', 'Ⓞ').replace('P', 'Ⓟ').replace('Q', 'Ⓠ')
		 		.replace('R', 'Ⓡ').replace('S', 'Ⓢ').replace('T', 'Ⓣ')
		 		.replace('U', 'Ⓤ').replace('V', 'Ⓥ').replace('W', 'Ⓦ')
		 		.replace('X', 'Ⓧ').replace('Y', 'Ⓨ').replace('Z', 'Ⓩ') + "<v.ht/yawns"); // try getOriginalMessage maybe
		}
	}
}