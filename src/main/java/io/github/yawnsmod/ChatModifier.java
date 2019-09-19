package io.github.yawnsmod;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ChatModifier
{
	@SubscribeEvent
	public void onClientChat(ClientChatEvent event)
	{
		if (event.getMessage().charAt(0) != '/') {
			event.setMessage("> " + event.getMessage().replace('0', 'ⓞ').replace('1', '➀')
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
		   		.replace('X', 'Ⓧ').replace('Y', 'Ⓨ').replace('Z', 'Ⓩ') + " < (Install YAWNS Mod)"); // try getOriginalMessage maybe
		}
	}
}