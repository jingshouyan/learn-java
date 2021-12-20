package com.github.jingshouyan.learn;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

public class TestAgentMain {

    
    public static void main(String[] args) {
        System.out.println("run JVM start");
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            System.out.println(vmd.id() + "----" + vmd.displayName() + ":::::" + vmd);
        }
    }
}
