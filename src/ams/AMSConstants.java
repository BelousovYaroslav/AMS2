/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import java.util.LinkedList;

/**
 *
 * @author yaroslav
 */
public class AMSConstants {
    
    //singletone implementation
    private static final AMSConstants instance = new AMSConstants();
    public static AMSConstants getInstance() { return instance;}
    
    //T.T_DEVICES ENUM
    public static final int T_DEVICE1 = 0;
    public static final int T_DEVICE2 = 1;
    public static final int T_DEVICE3 = 2;
    public static final int T_DEVICE4 = 3;
    public static final int T_DEVICE5 = 4;
    public static final int T_DEVICE6 = 5;
    public static final int T_DEVICE7 = 6;
    public static final int T_DEVICE8 = 7;
    public LinkedList T_DEVICES;
    
    //A.DEVICE.CHANNELS ENUM
    public static final int CHANNEL1 = 0;
    public static final int CHANNEL2 = 1;
    public static final int CHANNEL3 = 2;
    public static final int CHANNEL4 = 3;
    public static final int CHANNEL5 = 4;
    public static final int CHANNEL6 = 5;
    public static final int CHANNEL7 = 6;
    public static final int CHANNEL8 = 7;
    public LinkedList CHANNELS;
    
    
    //A.DEVICE TYPES ENUM
    public static final int ADC1 = 1;
    public static final int ADC2 = 2;
    public static final int ADC3 = 3;
    public static final int ADC4 = 4;
    
    public static final int DAC1 = 5;
    public static final int DAC2 = 6;
    public static final int DAC3 = 7;
    public static final int DAC4 = 8;
    
    public static final int REL1 = 9;
    public static final int REL2 = 10;
    
    
    //ADC SPECIALS
    public static final int ADC_UNKNOWN = -1;
    public static final int ADC_ANODE_VOLTAGE = 1;
    public static final int ADC_TUBULATION_VOLTAGE = 2;
    public static final int ADC_ANODE_CURRENT = 3;
    public static final int ADC_TUBULATION_CURRENT = 4;
    
    public static final int ADC_VALIDATION_EDGE_VOLTAGE_LOW = 350;
    public static final int ADC_VALIDATION_EDGE_VOLTAGE_HIGH = 3500;
    public static final int ADC_VALIDATION_EDGE_CURRENT_LOW = 300;
    public static final int ADC_VALIDATION_EDGE_CURRENT_HIGH = 3000;
    
    
    //DAC SPECIALS
    public static final double DAC_MAX_OUTPUT_VOLTAGE = 4.0;
    public static final double DAC_MAX_OUTPUT_VOLTAGE_WARN = 8.5;
    public static final double DAC_CHANNEL_REG_ON_DAC_VOLT = 6.0;
    public static final double DAC_CHANNEL_REG_OFF_DAC_VOLT = 3.0;
    public static final double DAC_MIN_OUTPUT_VOLTAGE_WARN = 3.0;
    public static final double DAC_MIN_OUTPUT_VOLTAGE = 2.5;
    
    private AMSConstants() {
        T_DEVICES = new LinkedList();
        T_DEVICES.add( T_DEVICE1);
        T_DEVICES.add( T_DEVICE2);
        T_DEVICES.add( T_DEVICE3);
        T_DEVICES.add( T_DEVICE4);
        T_DEVICES.add( T_DEVICE5);
        T_DEVICES.add( T_DEVICE6);
        T_DEVICES.add( T_DEVICE7);
        T_DEVICES.add( T_DEVICE8);
    }
}
