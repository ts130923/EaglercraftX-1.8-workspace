package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiTSPClientExclusive extends GuiScreen {

	private final GuiScreen parentScreen;
	private final GameSettings gameSettings;
	private String screenTitle = "TSP client exclusive";

	public GuiTSPClientExclusive(GuiScreen parentScreen, GameSettings gameSettings) {
		this.parentScreen = parentScreen;
		this.gameSettings = gameSettings;
	}

	public void initGui() {
		this.buttonList.clear();
		int top = this.height / 6 + 24;
		this.buttonList.add(new GuiButton(2001, this.width / 2 - 155, top, 150, 20,
				getFarmModeText()));
		this.buttonList.add(new GuiOptionButton(GameSettings.Options.PERFORMANCE_MODE.returnEnumOrdinal(),
				this.width / 2 + 5, top, 150, 20,
				this.gameSettings.getKeyBinding(GameSettings.Options.PERFORMANCE_MODE)));
		this.buttonList.add(new GuiButton(2002, this.width / 2 - 155, top + 24, 150, 20,
				getScrollZoomText(this.gameSettings)));
		this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height - 27,
				I18n.format("gui.done", new Object[0])));
	}

	protected void actionPerformed(GuiButton button) {
		if(!button.enabled) {
			return;
		}
		if(button.id == 2001) {
			Minecraft.farmMode = !Minecraft.farmMode;
			button.displayString = getFarmModeText();
		}
		if(button.id == GameSettings.Options.PERFORMANCE_MODE.returnEnumOrdinal()) {
			this.gameSettings.setOptionValue(GameSettings.Options.PERFORMANCE_MODE, 1);
			button.displayString = this.gameSettings.getKeyBinding(GameSettings.Options.PERFORMANCE_MODE);
		}
		if(button.id == 2002) {
			this.gameSettings.tspScrollZoom = !this.gameSettings.tspScrollZoom;
			button.displayString = getScrollZoomText(this.gameSettings);
		}
		if(button.id == 200) {
			this.gameSettings.saveOptions();
			this.mc.displayGuiScreen(this.parentScreen);
		}
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 15, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	private static String getFarmModeText() {
		return "Farm Mode: " + (Minecraft.farmMode ? "ON" : "OFF");
	}

	private static String getScrollZoomText(GameSettings gameSettings) {
		return "Scroll Zoom: " + (gameSettings.tspScrollZoom ? "ON" : "OFF");
	}
}
