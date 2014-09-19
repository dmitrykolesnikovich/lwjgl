package featurea.lwjgl;

import featurea.util.Array;
import featurea.util.ClassLoaderUtil;
import featurea.util.Platform;

public final class LwjglNatives {

  private LwjglNatives(){}

  public static void addToJavaLibraryPath(ClassLoaderUtil classLoader){
    if(System.getProperty("org.lwjgl.librarypath")==null){
      for(String lib : getLibs()){
        classLoader.natives.addNativeLib(lib);
      }
      System.setProperty("org.lwjgl.librarypath", classLoader.natives.getNativesDir());
    }
  }

  public static Array<String> getLibs(){
    Array<String> result = new Array<String>();
    if (Platform.isWindows) {
      result.add(Platform.is64Bit ? "lwjgl64.dll" : "lwjgl.dll");
      result.add(Platform.is64Bit ? "OpenAL64.dll" : "OpenAL32.dll");
    } else if (Platform.isMac) {
      result.add("liblwjgl.jnilib");
      result.add("openal.dylib");      
    } else if (Platform.isLinux) {
      result.add(Platform.is64Bit ? "liblwjgl64.so" : "liblwjgl.so");
      result.add(Platform.is64Bit ? "libopenal64.so" : "libopenal.so");      
    }
    return result;
  }

}
