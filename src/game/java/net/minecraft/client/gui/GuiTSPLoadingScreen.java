package net.minecraft.client.gui;

import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.GL_COLOR_BUFFER_BIT;
import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.GL_DEPTH_BUFFER_BIT;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;

public class GuiTSPLoadingScreen extends Gui {

	private static final int PURPLE = 0xFF3200AA;

	public static void draw(Minecraft mc, int width, int height, String text) {
		GlStateManager.clearColor(0.0F, 0.0F, 0.0F, 1.0F);
		GlStateManager.clear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		drawRect(0, 0, width, height, 0xFF000000);

		int iconSize = Math.max(100, Math.min(220, Math.min(width, height) / 3));

		GlStateManager.disableTexture2D();
		GlStateManager.enableBlend();
		GlStateManager.pushMatrix();
		GlStateManager.translate(width / 2.0F, height / 2.0F - 24.0F, 0.0F);
		drawGeneratedT(iconSize);
		GlStateManager.popMatrix();
		GlStateManager.enableTexture2D();

		if(text != null && mc.fontRendererObj != null) {
			mc.fontRendererObj.drawStringWithShadow(text, (width - mc.fontRendererObj.getStringWidth(text)) / 2.0F,
					height / 2.0F + iconSize / 2.0F + 2.0F, 0xFFFFFF);
		}
	}

	private static void drawGeneratedT(int size) {
		int radius = size / 2;
		int ring = Math.max(8, size / 22);
		int dot = Math.max(6, size / 24);
		for(int i = 0; i < 48; ++i) {
			double radians = i * Math.PI * 2.0D / 48.0D;
			int x = (int) Math.round(Math.cos(radians) * (radius - ring / 2.0D));
			int y = (int) Math.round(Math.sin(radians) * (radius - ring / 2.0D));
			drawRect(x - dot, y - dot, x + dot, y + dot, PURPLE);
		}

		int barHalfWidth = size / 4;
		int barHeight = Math.max(14, size / 9);
		int stemHalfWidth = Math.max(8, size / 18);
		drawRect(-barHalfWidth, -size / 4, barHalfWidth, -size / 4 + barHeight, PURPLE);
		drawRect(-stemHalfWidth, -size / 4, stemHalfWidth, size / 3, PURPLE);
	}
}
