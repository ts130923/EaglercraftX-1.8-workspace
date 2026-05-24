// GuiSubtitleOverlay.java
// Put in: net.minecraft.client.gui

package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;

public class GuiSubtitleOverlay extends Gui {
    private final Minecraft mc;
    private final List<Subtitle> subtitles = new ArrayList<>();

    public GuiSubtitleOverlay(Minecraft mc) {
        this.mc = mc;
    }

    public void addSubtitle(String text) {
        long now = System.currentTimeMillis();

        // refresh if already exists
        for (Subtitle s : subtitles) {
            if (s.text.equals(text)) {
                s.time = now;
                return;
            }
        }

        subtitles.add(new Subtitle(text, now));
    }

    public void renderSubtitles(ScaledResolution sr) {
    if (subtitles.isEmpty()) return;

    long now = System.currentTimeMillis();

    int screenW = sr.getScaledWidth();
    int screenH = sr.getScaledHeight();

    int y = screenH - 20; // ✅ bottom-right anchor

    int shown = 0;

    for (int i = subtitles.size() - 1; i >= 0; i--) {

        Subtitle sub = subtitles.get(i);

        long age = now - sub.time;

        if (age > 3000L) {
            subtitles.remove(i);
            continue;
        }

        float fade = 1.0F - (age / 3000.0F);
        int alpha = (int)(fade * 255.0F);

        String txt = sub.text;
        int width = mc.fontRendererObj.getStringWidth(txt);

        // ✅ right-align like 1.9+
        int x = screenW - width - 14;

        drawRect(
            x - 4,
            y - 2,
            screenW - 6,
            y + 10,
            (alpha / 2 << 24)
        );

        mc.fontRendererObj.drawString(
            txt,
            x,
            y,
            0xFFFFFF | (alpha << 24)
        );

        y -= 14; // stack upward

        shown++;
        if (shown >= 5) break;
    }
}

    private static class Subtitle {
        public String text;
        public long time;

        public Subtitle(String t, long tm) {
            text = t;
            time = tm;
        }
    }
}