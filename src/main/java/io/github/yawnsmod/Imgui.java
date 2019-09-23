// package io.github.yawnsmod;

// import imgui.ImGui;
// import imgui.ImguiKt;
// import imgui.imgui.Context;
// import imgui.impl.gl.ImplGL3;
// import imgui.impl.glfw.ImplGlfw;
// import net.minecraft.client.Minecraft;
// import net.minecraft.client.gui.GuiScreen;
// import net.minecraftforge.client.event.RenderGameOverlayEvent;
// //import net.minecraft.util.text.ITextComponent;
// import uno.glfw.GlfwWindow;

// public class Imgui extends GuiScreen {
// 	public Imgui(Minecraft mc, RenderGameOverlayEvent.Post event)
//     {

// 	    ImGui imgui = ImGui.INSTANCE;
	
// 	    ImplGL3 implGl3;
// 	    ImplGlfw implGlfw;

//         ImguiKt.MINECRAFT_BEHAVIORS = true;
//         GlfwWindow window = GlfwWindow.from(Minecraft.getInstance().mainWindow.getHandle());
//         window.makeContextCurrent();
//         new Context();
//         implGlfw = new ImplGlfw(window, false, null);
//         implGl3 = new ImplGL3();
//         implGl3.newFrame();
//         implGlfw.newFrame();

//         imgui.newFrame();

//         //Render things here

//         imgui.text("Hello Minecraft!");

//         //and stop here

//         implGl3.renderDrawData(imgui.getDrawData());

//     }
// }
