package mcda;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.Display;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class MCDAClientWindowHelper {
	private static ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException {
		BufferedImage bufferedimage = ImageIO.read(imageStream);
		int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), null, 0, bufferedimage.getWidth());
		ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
		for (int i : aint) {
			bytebuffer.putInt(i << 8 | i >> 24 & 0xFF);
		}
		bytebuffer.flip();
		return bytebuffer;
	}

	public static void setWindowIcon() {
		Util.EnumOS os = Util.getOSType();
		if (os != Util.EnumOS.OSX) {
			InputStream inputstream = null;
			InputStream inputstream1 = null;
			try {
				inputstream = MCDAMod.class.getResourceAsStream("/assets/mcda/icons/icon_16x16.png");
				inputstream1 = MCDAMod.class.getResourceAsStream("/assets/mcda/icons/icon_32x32.png");
				if (inputstream != null && inputstream1 != null) {
					Display.setIcon(new ByteBuffer[]{readImageToBuffer(inputstream), readImageToBuffer(inputstream1)});
				}
			} catch (Exception ioexception) {
				MCDAMod.getLogger().error("Couldn't set icon", ioexception);
			} finally {
				IOUtils.closeQuietly(inputstream);
				IOUtils.closeQuietly(inputstream1);
			}
			MCDAMod.getLogger().info("Window icon changed");
		}
	}

	public static void setWindowTitle(String title) {
		Runnable r = () -> {
			try {
				Display.setTitle("Hummel009's Minecraft 1.7.10");
				MCDAMod.getLogger().info("Window title changed");
			} catch (Exception e) {
				MCDAMod.getLogger().catching(e);
			}
		};
		if (Minecraft.getMinecraft().func_152345_ab()) {
			r.run();
		} else {
			Minecraft.getMinecraft().func_152344_a(r);
		}
	}
}

