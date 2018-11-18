package com.advertstv.clientui.player;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class PlayerInformation {
    public static void main(String[] args) throws Exception {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "d:/vlc-2.2.1");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise();
        System.out.println(" version: {}" + LibVlc.INSTANCE.libvlc_get_version());
        System.out.println(" compiler: {}" + LibVlc.INSTANCE.libvlc_get_compiler());
        System.out.println("changeset: {}" + LibVlc.INSTANCE.libvlc_get_changeset());
    }
}
