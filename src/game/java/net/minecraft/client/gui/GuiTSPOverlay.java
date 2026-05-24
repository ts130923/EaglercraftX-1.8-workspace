package net.minecraft.client.gui; 

import net.minecraft.client.gui.Gui; 
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager; 
import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.GL_COLOR_BUFFER_BIT; 
import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.GL_DEPTH_BUFFER_BIT;

public class GuiTSPOverlay { 
    public static void draw(int width, int height, float time) { 
        // 1. Fully reset OpenGL colors to white so it doesn't tint black into pink
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
        // 2. Clear out any residual missing texture states from the previous screen
        GlStateManager.disableTexture2D(); 
        
        // 3. Force-render a pure black backdrop to the WebGL screen buffer
        GlStateManager.clearColor(0.0f, 0.0f, 0.0f, 1.0f); 
        GlStateManager.clear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
        
        // 4. Draw a massive pure black rectangle covering the viewport to stop alpha leaks
        Gui.drawRect(0, 0, width, height, 0xFF000000); 

        // 5. Safely restore texture mapping for downstream Eaglercraft UI code
        GlStateManager.enableTexture2D();
    } 
}
