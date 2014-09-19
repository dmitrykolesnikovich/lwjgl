package featurea.lwjgl;

import de.matthiasmann.twl.utils.PNGDecoder;
import featurea.util.ClassLoaderUtil;
import featurea.util.Size;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public final class TextureLoader {

  private TextureLoader(){}

  public static Size load(String file, ClassLoaderUtil classLoader){
    try {
      PNGDecoder dec = new PNGDecoder(classLoader.getResourceAsStream(file));
      float width = dec.getWidth();
      float height = dec.getHeight();
      ByteBuffer buffer = BufferUtils.createByteBuffer((int) (4 * width * height));
      dec.decode(buffer, (int) (width * 4), PNGDecoder.Format.RGBA);
      buffer.flip();
      GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
      GL11.glPixelStorei(GL11.GL_PACK_ALIGNMENT, 1);
      GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, (int)width, (int)height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
      return new Size(width, height);
    } catch (IOException e) {
      e.printStackTrace();
      return new Size(0, 0);
    }
  }

  public static IntBuffer getPixels(int id, int width, int height) {
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
    IntBuffer result = BufferUtils.createIntBuffer(width*height);
    GL11.glGetTexImage(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, GL12.GL_UNSIGNED_INT_8_8_8_8, result);
    return result;
  }

}
