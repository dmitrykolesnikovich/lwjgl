package org.lwjgl;

import featurea.util.ClassPath;
import featurea.util.Environment;
import featurea.util.FileUtil;
import featurea.util.Files;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.jar.JarFile;

public final class LwjglNatives {
  static {
  }

  private LwjglNatives() {
  }

  public final static LwjglNatives instance = new LwjglNatives();

  public static void register() {
    instance.performRegisterNativeLibsInJavaLibraryPath();
  }

  private void performRegisterNativeLibsInJavaLibraryPath() {
    JarFile jarFile = isJarFile();
    if (jarFile != null) {
      Files.getContextFiles().addJarFile(jarFile);
      ClassPath.getContextClassPath().addClasspath(jarFile);
    }
    setLibraryPath(Environment.nativesDir);
  }

  private JarFile isJarFile() {
    try {
      String file = FileUtil.getFile(LwjglNatives.class);
      if (file != null && file.endsWith(".jar")) {
        return new JarFile(file);
      }
    } catch (Exception skip) {
      skip.printStackTrace();
    }
    return null;
  }

  private void setLibraryPath(String libraryPath) {
    System.setProperty("org.lwjgl.librarypath", libraryPath);
    try {
      Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
      usrPathsField.setAccessible(true);
      String[] paths = (String[]) usrPathsField.get(null);
      for (String path : paths) {
        if (path.equals(path)) {
          return;
        }
      }
      String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
      newPaths[newPaths.length - 1] = libraryPath;
      usrPathsField.set(null, newPaths);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
