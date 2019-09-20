package io.github.yawnsmod;

import glm_.vec2.Vec2;
import imgui.ImGui;
import imgui.ImguiKt;
import imgui.imgui.Context;
import imgui.impl.gl.ImplGL3;
import imgui.impl.glfw.ImplGlfw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.Display;
import net.minecraft.util.text.ITextComponent;
import uno.glfw.GlfwWindow;
import net.minecraft.client.MainWindow;

public class Imgui extends GuiScreen {

    private static ImGui imgui = ImGui.INSTANCE;

    private static ImplGL3 implGl3;
    private static ImplGlfw implGlfw;

    static {
        ImguiKt.MINECRAFT_BEHAVIORS = true;
        GlfwWindow window = GlfwWindow.from(Minecraft.getInstance().mainWindow.getHandle());
        window.makeContextCurrent();
        new Context();
        implGlfw = new ImplGlfw(window, false, null);
        implGl3 = new ImplGL3();
    }

    public void Imgui (ITextComponent title) {
        super(title);
    }

    @Override
    public void render(int x, int y, float partialTicks) {

        implGl3.newFrame();
        implGlfw.newFrame();

        imgui.newFrame();

        //Render things here

        imgui.text("Hello Minecraft!");

        //and stop here

        implGl3.renderDrawData(imgui.getDrawData());

    }
}
