/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import ams.devices.AMSCalibration;
import ams.devices.AMSDevicesSet;
import ams.devices.AbstractDevice;
import ams.devices.Adam4017plus;
import ams.devices.Adam4024;
import ams.devices.Adam4068;
import ams.serial.TwoWaySerialComm;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.swing.JOptionPane;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author yaroslav
 */
public class AMSApp {
    public final static int REGIME_UIC = 1;
    public final static int REGIME_I_EDGE = 2;
    public final static int REGIME_PROCESSING = 3;
    public final static int REGIME_MANUAL = 4;
    
    public final static int CURRENT_DISCRETE = 10;

    private final int MIN_CURRENT = 100;
    private final int MAX_CURRENT = 3000;
    
    //public static final Level LOG_LEVEL = Level.DEBUG;
    static Logger logger = Logger.getLogger( AMSApp.class);
    
    private final AMSSettings m_Settings;
    public AMSSettings GetSettings() { return m_Settings;}
    
    private final AMSCalibration m_Calibration;
    public AMSCalibration GetCalibration() { return m_Calibration;}
    
    private TwoWaySerialComm m_rxtx;
    public TwoWaySerialComm GetRxTx() { return m_rxtx; }
    
    private final AMSDeviceManager m_DeviceManager0_a;
    private final AMSDeviceManager m_DeviceManager0_t;
    private AMSDeviceManager m_DeviceManager1_a;
    private AMSDeviceManager m_DeviceManager1_t;
    private AMSDeviceManager m_DeviceManager2_a;
    private AMSDeviceManager m_DeviceManager2_t;
    private AMSDeviceManager m_DeviceManager3_a;
    private AMSDeviceManager m_DeviceManager3_t;
    private AMSDeviceManager m_DeviceManager4_a;
    private AMSDeviceManager m_DeviceManager4_t;
    private AMSDeviceManager m_DeviceManager5_a;
    private AMSDeviceManager m_DeviceManager5_t;
    private AMSDeviceManager m_DeviceManager6_a;
    private AMSDeviceManager m_DeviceManager6_t;
    private AMSDeviceManager m_DeviceManager7_a;
    private AMSDeviceManager m_DeviceManager7_t;

    public AMSDeviceManager GetDevManager_a( int nDevice) {
        AMSDeviceManager mngr = null;
        switch( nDevice) {
            case AMSConstants.T_DEVICE1: mngr = GetDevManager_0_a(); break;
            case AMSConstants.T_DEVICE2: mngr = GetDevManager_1_a(); break;
            case AMSConstants.T_DEVICE3: mngr = GetDevManager_2_a(); break;
            case AMSConstants.T_DEVICE4: mngr = GetDevManager_3_a(); break;
            case AMSConstants.T_DEVICE5: mngr = GetDevManager_4_a(); break;
            case AMSConstants.T_DEVICE6: mngr = GetDevManager_5_a(); break;
            case AMSConstants.T_DEVICE7: mngr = GetDevManager_6_a(); break;
            case AMSConstants.T_DEVICE8: mngr = GetDevManager_7_a(); break;
        }
        return mngr;
    }
    public AMSDeviceManager GetDevManager_t( int nDevice) {
        AMSDeviceManager mngr = null;
        switch( nDevice) {
            case AMSConstants.T_DEVICE1: mngr = GetDevManager_0_t(); break;
            case AMSConstants.T_DEVICE2: mngr = GetDevManager_1_t(); break;
            case AMSConstants.T_DEVICE3: mngr = GetDevManager_2_t(); break;
            case AMSConstants.T_DEVICE4: mngr = GetDevManager_3_t(); break;
            case AMSConstants.T_DEVICE5: mngr = GetDevManager_4_t(); break;
            case AMSConstants.T_DEVICE6: mngr = GetDevManager_5_t(); break;
            case AMSConstants.T_DEVICE7: mngr = GetDevManager_6_t(); break;
            case AMSConstants.T_DEVICE8: mngr = GetDevManager_7_t(); break;
        }
        return mngr;
    }
    public AMSDeviceManager GetDevManager_0_a() { return m_DeviceManager0_a; }
    public AMSDeviceManager GetDevManager_0_t() { return m_DeviceManager0_t; }
    public AMSDeviceManager GetDevManager_1_a() { return m_DeviceManager1_a; }
    public AMSDeviceManager GetDevManager_1_t() { return m_DeviceManager1_t; }
    public AMSDeviceManager GetDevManager_2_a() { return m_DeviceManager2_a; }
    public AMSDeviceManager GetDevManager_2_t() { return m_DeviceManager2_t; }
    public AMSDeviceManager GetDevManager_3_a() { return m_DeviceManager3_a; }
    public AMSDeviceManager GetDevManager_3_t() { return m_DeviceManager3_t; }
    public AMSDeviceManager GetDevManager_4_a() { return m_DeviceManager4_a; }
    public AMSDeviceManager GetDevManager_4_t() { return m_DeviceManager4_t; }
    public AMSDeviceManager GetDevManager_5_a() { return m_DeviceManager5_a; }
    public AMSDeviceManager GetDevManager_5_t() { return m_DeviceManager5_t; }
    public AMSDeviceManager GetDevManager_6_a() { return m_DeviceManager6_a; }
    public AMSDeviceManager GetDevManager_6_t() { return m_DeviceManager6_t; }
    public AMSDeviceManager GetDevManager_7_a() { return m_DeviceManager7_a; }
    public AMSDeviceManager GetDevManager_7_t() { return m_DeviceManager7_t; }
    
    AMSDevicesSet m_devicesSet;
    /*public final Adam4068 m_Relay_anodes;
    public final Adam4017plus m_ADC_anodes_currents;
    public final Adam4017plus m_ADC_anodes_voltages;
    public final Adam4024 m_DAC_anodes_01;
    public final Adam4024 m_DAC_anodes_02;
    
    public final Adam4068 m_Relay_tubulations;
    public final Adam4017plus m_ADC_tubulations_currents;
    public final Adam4017plus m_ADC_tubulations_voltages;
    public final Adam4024 m_DAC_tubulations_01;
    public final Adam4024 m_DAC_tubulations_02;
    */
    
    
    private final AMSResources m_resources;
    public AMSResources GetResources() { return m_resources; }
    
    private final AMSDevSerialNumbers m_devSerNums;
    public AMSDevSerialNumbers GetDevSerNums() { return m_devSerNums;}
            
    public MainFrame3 m_pMainWnd;

    /**
     * Current work regime of the program
     * Should be any of these values:<br>
     * REGIME_UIC<br>
     * EGIME_I_EDGE<br>
     * REGIME_PROCESSING<br>
     * REGIME_MANUAL
     */
    private int m_nRegime;
    
    /**
     * Set current work regime of the program
     * @param nNewRegime
     * New regime. Should be any of these values:<br>
     * REGIME_UIC<br>
     * EGIME_I_EDGE<br>
     * REGIME_PROCESSING<br>
     * REGIME_MANUAL<br>
     * @return
     * previous regime
     * @throws java.lang.Exception
     * when in parameter is not valid
     */
    public int SetRegime( int nNewRegime) throws Exception {
        if( nNewRegime != REGIME_UIC &&
            nNewRegime != REGIME_I_EDGE &&
            nNewRegime != REGIME_PROCESSING &&
            nNewRegime != REGIME_MANUAL) {
            
            throw new Exception( "Wrong parameter for regime");
        }
    
        int nPrevRegime = m_nRegime;
        m_nRegime = nNewRegime;
        return nPrevRegime;
    }
    
    /**
     * Get current work regime of the program
     * @return 
     * work regime of the program<br>
     * Should be any of these values:<br>
     * REGIME_UIC<br>
     * EGIME_I_EDGE<br>
     * REGIME_PROCESSING<br>
     * REGIME_MANUAL
     */
    public int GetRegime() {
        return m_nRegime;
    }
    
    /**
     * Preset current (value in mcA)
     */
    private int m_nOuterCurrent;
    public int SetOuterCurrent( int nNewOuterCurrent) {
        int nPrevCurrent = m_nOuterCurrent;
        m_nOuterCurrent = nNewOuterCurrent;
        CheckCurrentLimits();        
        logger.info( "SetOuterCurrent(): New outer current = " + m_nOuterCurrent);       
        return nPrevCurrent;
    }
    
    public void CheckCurrentLimits() {
        if( m_nOuterCurrent < MIN_CURRENT) m_nOuterCurrent = MIN_CURRENT;
        if( m_nOuterCurrent > MAX_CURRENT) m_nOuterCurrent = MAX_CURRENT;
        if( m_nOuterCurrent % CURRENT_DISCRETE < 5) m_nOuterCurrent -= m_nOuterCurrent % CURRENT_DISCRETE;
        if( m_nOuterCurrent % CURRENT_DISCRETE >= 5) m_nOuterCurrent += (CURRENT_DISCRETE - m_nOuterCurrent % CURRENT_DISCRETE);
    }
    
    public int IncDecOuterCurrent( int nCurrentIncDec) {
        m_nOuterCurrent -= nCurrentIncDec;
        CheckCurrentLimits();        
        logger.info( "IncDecOuterCurrent(): New outer current = " + m_nOuterCurrent);        
        return m_nOuterCurrent;
    }
    
    public int GetOuterCurrent() {
        return m_nOuterCurrent;
    }
    
    /**
     * Main Switcher state
     */
    private boolean m_bMainSwitcher;
    
    /**
     * Get main switcher state
     * @return 
     */
    public boolean GetMainSwitcher() { return m_bMainSwitcher; }
    
    public void TurnMainSwitcherOn() {
        m_bMainSwitcher = true;
        
        //ставим на всех DAC'ах 3,0v
        //и включаем реле
        AMSDeviceManager mngr = m_DeviceManager0_a;
        Adam4024 dac;
        int nDacChannel;
        Adam4068 relay;
        int nRelayChannel;
        
        for( int i = 0; i < 16; i++) {
            switch( i) {
                case  0: mngr = m_DeviceManager0_a; break;                    
                case  1: mngr = m_DeviceManager0_t; break;
                case  2: mngr = m_DeviceManager1_a; break;                        
                case  3: mngr = m_DeviceManager1_t; break;
                case  4: mngr = m_DeviceManager2_a; break;                    
                case  5: mngr = m_DeviceManager2_t; break;
                case  6: mngr = m_DeviceManager3_a; break;                        
                case  7: mngr = m_DeviceManager3_t; break;
                case  8: mngr = m_DeviceManager4_a; break;                    
                case  9: mngr = m_DeviceManager4_t; break;
                case 10: mngr = m_DeviceManager5_a; break;                        
                case 11: mngr = m_DeviceManager5_t; break;
                case 12: mngr = m_DeviceManager6_a; break;                    
                case 13: mngr = m_DeviceManager6_t; break;
                case 14: mngr = m_DeviceManager7_a; break;                        
                case 15: mngr = m_DeviceManager7_t; break;
            }
            
            if( mngr != null) {
                if( mngr.GetEnabled()) {
                    //ставим 6,0V на DAC
                    dac = mngr.GetDAC();
                    nDacChannel = mngr.GetDacChannel();
            
                    String strCmd = dac.GetSetChannelOutputValueCommand( nDacChannel, 6.0);
                    m_rxtx.AddCommandToQueueEmergent( strCmd, dac);
                
                    //замыкаем реле
                    relay = mngr.GetRelay();
                    nRelayChannel = mngr.GetRelayChannel();
                    relay.QueueSetRelayStateBitCommand( nRelayChannel, true);
                    relay.QueueRequestDataCommand();
                }
            }
            else
                logger.warn( "DeviceManger for [" + i + "] is null!");
        }
    }
    
    public void TurnMainSwitcherOff() {
        m_bMainSwitcher = false;
        
        Adam4068 rel1;
        Adam4068 rel2;
        try {
            rel1 = m_devicesSet.GetRelay( AMSConstants.REL1);
            rel2 = m_devicesSet.GetRelay( AMSConstants.REL2);
        }
        catch( Exception e) {
            MessageBoxError( "Ошибка в программе. Рубильник не срабатывает. Выключите рубильник на стенде!", "AMS");
            logger.fatal( "Exception при получении объектов реле", e);
            return;
        }
        
        if( rel1 == null || rel2 == null) {
            if( rel1 != null)
                m_rxtx.AddCommandToQueueEmergent( rel1.GetSetRelayStateByteCommand( ( byte) 0), rel1);
            
            if( rel2 != null)
                m_rxtx.AddCommandToQueueEmergent( rel2.GetSetRelayStateByteCommand( ( byte) 0), rel2);
            
            MessageBoxError( "Ошибка в программе. Рубильник не срабатывает. Выключите рубильник на стенде!", "AMS");
            logger.fatal( "Exception при получении объектов реле");
            return;
        }
        
        //РАЗМЫКАЕМ РЕЛЕ
        m_rxtx.AddCommandToQueueEmergent( rel1.GetSetRelayStateByteCommand( ( byte) 0), rel1);
        m_rxtx.AddCommandToQueueEmergent( rel2.GetSetRelayStateByteCommand( ( byte) 0), rel2);
        
        rel1.QueueRequestDataCommand();
        rel2.QueueRequestDataCommand();
        
        //и ставим на всех DAC'ах 3,0v
        AMSDeviceManager mngr = m_DeviceManager0_a;
        Adam4024 dac;
        int nDacChannel;
        
        for( int i = 0; i < 16; i++) {
            switch( i) {
                case  0: mngr = m_DeviceManager0_a; break;
                case  1: mngr = m_DeviceManager0_t; break;
                case  2: mngr = m_DeviceManager1_a; break;
                case  3: mngr = m_DeviceManager1_t; break;
                case  4: mngr = m_DeviceManager2_a; break;
                case  5: mngr = m_DeviceManager2_t; break;
                case  6: mngr = m_DeviceManager3_a; break;
                case  7: mngr = m_DeviceManager3_t; break;
                case  8: mngr = m_DeviceManager4_a; break;
                case  9: mngr = m_DeviceManager4_t; break;
                case 10: mngr = m_DeviceManager5_a; break;
                case 11: mngr = m_DeviceManager5_t; break;
                case 12: mngr = m_DeviceManager6_a; break;
                case 13: mngr = m_DeviceManager6_t; break;
                case 14: mngr = m_DeviceManager7_a; break;
                case 15: mngr = m_DeviceManager7_t; break;
            }
            
            if( mngr != null) {           
                dac = mngr.GetDAC();
                nDacChannel = mngr.GetDacChannel();
            
                String strCmd = dac.GetSetChannelOutputValueCommand( nDacChannel, AMSConstants.DAC_CHANNEL_REG_OFF_DAC_VOLT);
                m_rxtx.AddCommandToQueueEmergent( strCmd, dac);
            
                mngr.SetEnabled( false);
                //mngr.GetActiveLed().setIcon( GetResources().getLittleIconOff());
            }
            else
                logger.warn( "Manager for [" + i + "] is NULL!");
        }
        
        
        m_pMainWnd.lblDev1_anode.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev2_tubu.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev2_anode.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev2_tubu.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev3_anode.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev3_tubu.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev4_anode.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev4_tubu.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev5_anode.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev5_tubu.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev6_anode.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev6_tubu.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev7_anode.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev7_tubu.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev8_anode.setIcon( GetResources().getLittleIconOff());
        m_pMainWnd.lblDev8_tubu.setIcon( GetResources().getLittleIconOff());
        
    }
    
    public AMSApp() throws Exception {
        //SplashScreen???
        
        //default value for switcher is false.... realy?
        //TODO: think about check relays state
        m_bMainSwitcher = false;
        
        //Start value for pre-set current
        m_nOuterCurrent = 1000;
        m_nRegime = REGIME_MANUAL;
        
        //Настройки
        m_Settings = new AMSSettings( this);
        if( m_Settings.LoadSettings() == false) {
            throw new Exception( "Ошибка при загрузке настроек!");
        }
        
        //Калибровка
        m_Calibration = new AMSCalibration( this);
        if( m_Calibration.LoadCalibration() == false) {
            throw new Exception( "Ошибка при загрузке калибровочных данных!");
        }
        
        //COM Port
        m_rxtx = null;
        
        //RESOURCES (icons, bitmaps)
        m_resources = new AMSResources( this);
        
        //Измерительные устройства
        m_devicesSet = new AMSDevicesSet( this);
        m_devicesSet.initialize();
        
        //Номера испытуемых приборов
        m_devSerNums = new AMSDevSerialNumbers();
        
        
        
        Adam4017plus adcV; int adcV_channel;
        Adam4017plus adcC; int adcC_channel;
        Adam4024 dac;      int dac_channel;
        Adam4068 relay;    int relay_channel;
        
        //************************************************************
        //DEVICE MANAGERS
        //DEVICE1
        //DEVICE1.ANODE
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev1().GetAnoAdcVoltDev());
        adcV_channel  = m_Settings.GetDev1().GetAnoAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev1().GetAnoAdcCurrDev());
        adcC_channel  = m_Settings.GetDev1().GetAnoAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev1().GetAnoDacDev());
        dac_channel   = m_Settings.GetDev1().GetAnoDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev1().GetAnoRelDev());
        relay_channel = m_Settings.GetDev1().GetAnoRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_ANODE_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_ANODE_CURRENT);
        
        m_DeviceManager0_a = new AMSDeviceManager( this);
        m_DeviceManager0_a.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager0_a.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager0_a.SetDAC( dac, dac_channel);
        m_DeviceManager0_a.SetRelay(relay, relay_channel);
        m_DeviceManager0_a.SetEnabled( false);
        
        //DEVICE1.TUBULATION
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev1().GetTubAdcVoltDev());
        adcV_channel  = m_Settings.GetDev1().GetTubAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev1().GetTubAdcCurrDev());
        adcC_channel  = m_Settings.GetDev1().GetTubAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev1().GetTubDacDev());
        dac_channel   = m_Settings.GetDev1().GetTubDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev1().GetTubRelDev());
        relay_channel = m_Settings.GetDev1().GetTubRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_TUBULATION_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_TUBULATION_CURRENT);
        
        m_DeviceManager0_t = new AMSDeviceManager( this);
        m_DeviceManager0_t.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager0_t.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager0_t.SetDAC( dac, dac_channel);
        m_DeviceManager0_t.SetRelay(relay, relay_channel);
        m_DeviceManager0_t.SetEnabled( false);
        
        //************************************************************
        //DEVICE2
        //DEVICE2.ANODE
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev2().GetAnoAdcVoltDev());
        adcV_channel  = m_Settings.GetDev2().GetAnoAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev2().GetAnoAdcCurrDev());
        adcC_channel  = m_Settings.GetDev2().GetAnoAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev2().GetAnoDacDev());
        dac_channel   = m_Settings.GetDev2().GetAnoDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev2().GetAnoRelDev());
        relay_channel = m_Settings.GetDev2().GetAnoRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_ANODE_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_ANODE_CURRENT);
        
        m_DeviceManager1_a = new AMSDeviceManager( this);
        m_DeviceManager1_a.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager1_a.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager1_a.SetDAC( dac, dac_channel);
        m_DeviceManager1_a.SetRelay(relay, relay_channel);
        m_DeviceManager1_a.SetEnabled( false);
        
        //DEVICE2.TUBULATION
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev2().GetTubAdcVoltDev());
        adcV_channel  = m_Settings.GetDev2().GetTubAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev2().GetTubAdcCurrDev());
        adcC_channel  = m_Settings.GetDev2().GetTubAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev2().GetTubDacDev());
        dac_channel   = m_Settings.GetDev2().GetTubDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev2().GetTubRelDev());
        relay_channel = m_Settings.GetDev2().GetTubRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_TUBULATION_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_TUBULATION_CURRENT);
        
        m_DeviceManager1_t = new AMSDeviceManager( this);
        m_DeviceManager1_t.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager1_t.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager1_t.SetDAC( dac, dac_channel);
        m_DeviceManager1_t.SetRelay(relay, relay_channel);
        m_DeviceManager1_t.SetEnabled( false);
        
        //************************************************************
        //DEVICE3
        //DEVICE3.ANODE
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev3().GetAnoAdcVoltDev());
        adcV_channel  = m_Settings.GetDev3().GetAnoAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev3().GetAnoAdcCurrDev());
        adcC_channel  = m_Settings.GetDev3().GetAnoAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev3().GetAnoDacDev());
        dac_channel   = m_Settings.GetDev3().GetAnoDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev3().GetAnoRelDev());
        relay_channel = m_Settings.GetDev3().GetAnoRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_ANODE_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_ANODE_CURRENT);
        
        m_DeviceManager2_a = new AMSDeviceManager( this);
        m_DeviceManager2_a.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager2_a.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager2_a.SetDAC( dac, dac_channel);
        m_DeviceManager2_a.SetRelay(relay, relay_channel);
        m_DeviceManager2_a.SetEnabled( false);
        
        //DEVICE3.TUBULATION
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev3().GetTubAdcVoltDev());
        adcV_channel  = m_Settings.GetDev3().GetTubAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev3().GetTubAdcCurrDev());
        adcC_channel  = m_Settings.GetDev3().GetTubAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev3().GetTubDacDev());
        dac_channel   = m_Settings.GetDev3().GetTubDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev3().GetTubRelDev());
        relay_channel = m_Settings.GetDev3().GetTubRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_TUBULATION_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_TUBULATION_CURRENT);
        
        m_DeviceManager2_t = new AMSDeviceManager( this);
        m_DeviceManager2_t.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager2_t.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager2_t.SetDAC( dac, dac_channel);
        m_DeviceManager2_t.SetRelay(relay, relay_channel);
        m_DeviceManager2_t.SetEnabled( false);
        
        //************************************************************
        //DEVICE4
        //DEVICE4.ANODE
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev4().GetAnoAdcVoltDev());
        adcV_channel  = m_Settings.GetDev4().GetAnoAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev4().GetAnoAdcCurrDev());
        adcC_channel  = m_Settings.GetDev4().GetAnoAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev4().GetAnoDacDev());
        dac_channel   = m_Settings.GetDev4().GetAnoDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev4().GetAnoRelDev());
        relay_channel = m_Settings.GetDev4().GetAnoRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_ANODE_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_ANODE_CURRENT);
        
        m_DeviceManager3_a = new AMSDeviceManager( this);
        m_DeviceManager3_a.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager3_a.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager3_a.SetDAC( dac, dac_channel);
        m_DeviceManager3_a.SetRelay(relay, relay_channel);
        m_DeviceManager3_a.SetEnabled( false);
        
        //DEVICE4.TUBULATION
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev4().GetTubAdcVoltDev());
        adcV_channel  = m_Settings.GetDev4().GetTubAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev4().GetTubAdcCurrDev());
        adcC_channel  = m_Settings.GetDev4().GetTubAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev4().GetTubDacDev());
        dac_channel   = m_Settings.GetDev4().GetTubDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev4().GetTubRelDev());
        relay_channel = m_Settings.GetDev4().GetTubRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_TUBULATION_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_TUBULATION_CURRENT);
        
        m_DeviceManager3_t = new AMSDeviceManager( this);
        m_DeviceManager3_t.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager3_t.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager3_t.SetDAC( dac, dac_channel);
        m_DeviceManager3_t.SetRelay(relay, relay_channel);
        m_DeviceManager3_t.SetEnabled( false);
        
        //************************************************************
        //DEVICE5
        //DEVICE5.ANODE
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev5().GetAnoAdcVoltDev());
        adcV_channel  = m_Settings.GetDev5().GetAnoAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev5().GetAnoAdcCurrDev());
        adcC_channel  = m_Settings.GetDev5().GetAnoAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev5().GetAnoDacDev());
        dac_channel   = m_Settings.GetDev5().GetAnoDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev5().GetAnoRelDev());
        relay_channel = m_Settings.GetDev5().GetAnoRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_ANODE_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_ANODE_CURRENT);
        
        m_DeviceManager4_a = new AMSDeviceManager( this);
        m_DeviceManager4_a.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager4_a.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager4_a.SetDAC( dac, dac_channel);
        m_DeviceManager4_a.SetRelay(relay, relay_channel);
        m_DeviceManager4_a.SetEnabled( false);
        
        //DEVICE5.TUBULATION
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev5().GetTubAdcVoltDev());
        adcV_channel  = m_Settings.GetDev5().GetTubAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev5().GetTubAdcCurrDev());
        adcC_channel  = m_Settings.GetDev5().GetTubAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev5().GetTubDacDev());
        dac_channel   = m_Settings.GetDev5().GetTubDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev5().GetTubRelDev());
        relay_channel = m_Settings.GetDev5().GetTubRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_TUBULATION_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_TUBULATION_CURRENT);
        
        m_DeviceManager4_t = new AMSDeviceManager( this);
        m_DeviceManager4_t.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager4_t.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager4_t.SetDAC( dac, dac_channel);
        m_DeviceManager4_t.SetRelay(relay, relay_channel);
        m_DeviceManager4_t.SetEnabled( false);
        
        //************************************************************
        //DEVICE6
        //DEVICE6.ANODE
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev6().GetAnoAdcVoltDev());
        adcV_channel  = m_Settings.GetDev6().GetAnoAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev6().GetAnoAdcCurrDev());
        adcC_channel  = m_Settings.GetDev6().GetAnoAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev6().GetAnoDacDev());
        dac_channel   = m_Settings.GetDev6().GetAnoDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev6().GetAnoRelDev());
        relay_channel = m_Settings.GetDev6().GetAnoRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_ANODE_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_ANODE_CURRENT);
        
        m_DeviceManager5_a = new AMSDeviceManager( this);
        m_DeviceManager5_a.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager5_a.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager5_a.SetDAC( dac, dac_channel);
        m_DeviceManager5_a.SetRelay(relay, relay_channel);
        m_DeviceManager5_a.SetEnabled( false);
        
        //DEVICE6.TUBULATION
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev6().GetTubAdcVoltDev());
        adcV_channel  = m_Settings.GetDev6().GetTubAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev6().GetTubAdcCurrDev());
        adcC_channel  = m_Settings.GetDev6().GetTubAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev6().GetTubDacDev());
        dac_channel   = m_Settings.GetDev6().GetTubDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev6().GetTubRelDev());
        relay_channel = m_Settings.GetDev6().GetTubRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_TUBULATION_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_TUBULATION_CURRENT);
        
        m_DeviceManager5_t = new AMSDeviceManager( this);
        m_DeviceManager5_t.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager5_t.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager5_t.SetDAC( dac, dac_channel);
        m_DeviceManager5_t.SetRelay(relay, relay_channel);
        m_DeviceManager5_t.SetEnabled( false);
        
        //************************************************************
        //DEVICE7
        //DEVICE7.ANODE
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev7().GetAnoAdcVoltDev());
        adcV_channel  = m_Settings.GetDev7().GetAnoAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev7().GetAnoAdcCurrDev());
        adcC_channel  = m_Settings.GetDev7().GetAnoAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev7().GetAnoDacDev());
        dac_channel   = m_Settings.GetDev7().GetAnoDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev7().GetAnoRelDev());
        relay_channel = m_Settings.GetDev7().GetAnoRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_ANODE_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_ANODE_CURRENT);
        
        m_DeviceManager6_a = new AMSDeviceManager( this);
        m_DeviceManager6_a.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager6_a.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager6_a.SetDAC( dac, dac_channel);
        m_DeviceManager6_a.SetRelay(relay, relay_channel);
        m_DeviceManager6_a.SetEnabled( false);
        
        //DEVICE7.TUBULATION
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev7().GetTubAdcVoltDev());
        adcV_channel  = m_Settings.GetDev7().GetTubAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev7().GetTubAdcCurrDev());
        adcC_channel  = m_Settings.GetDev7().GetTubAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev7().GetTubDacDev());
        dac_channel   = m_Settings.GetDev7().GetTubDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev7().GetTubRelDev());
        relay_channel = m_Settings.GetDev7().GetTubRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_TUBULATION_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_TUBULATION_CURRENT);
        
        m_DeviceManager6_t = new AMSDeviceManager( this);
        m_DeviceManager6_t.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager6_t.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager6_t.SetDAC( dac, dac_channel);
        m_DeviceManager6_t.SetRelay(relay, relay_channel);
        m_DeviceManager6_t.SetEnabled( false);
        
        //************************************************************
        //DEVICE8
        //DEVICE8.ANODE
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev8().GetAnoAdcVoltDev());
        adcV_channel  = m_Settings.GetDev8().GetAnoAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev8().GetAnoAdcCurrDev());
        adcC_channel  = m_Settings.GetDev8().GetAnoAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev8().GetAnoDacDev());
        dac_channel   = m_Settings.GetDev8().GetAnoDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev8().GetAnoRelDev());
        relay_channel = m_Settings.GetDev8().GetAnoRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_ANODE_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_ANODE_CURRENT);
        
        m_DeviceManager7_a = new AMSDeviceManager( this);
        m_DeviceManager7_a.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager7_a.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager7_a.SetDAC( dac, dac_channel);
        m_DeviceManager7_a.SetRelay(relay, relay_channel);
        m_DeviceManager7_a.SetEnabled( false);
        
        //DEVICE8.TUBULATION
        adcV          = m_devicesSet.GetADC( m_Settings.GetDev8().GetTubAdcVoltDev());
        adcV_channel  = m_Settings.GetDev8().GetTubAdcVoltChan();
        adcC          = m_devicesSet.GetADC( m_Settings.GetDev8().GetTubAdcCurrDev());
        adcC_channel  = m_Settings.GetDev8().GetTubAdcCurrChan();
        dac           = m_devicesSet.GetDAC( m_Settings.GetDev8().GetTubDacDev());
        dac_channel   = m_Settings.GetDev8().GetTubDacChan();
        relay         = m_devicesSet.GetRelay( m_Settings.GetDev8().GetTubRelDev());
        relay_channel = m_Settings.GetDev8().GetTubRelChan();
        
        adcV.SetChannelDescriptor( adcV_channel, AMSConstants.ADC_TUBULATION_VOLTAGE);
        adcC.SetChannelDescriptor( adcC_channel, AMSConstants.ADC_TUBULATION_CURRENT);
        
        m_DeviceManager7_t = new AMSDeviceManager( this);
        m_DeviceManager7_t.SetADCVoltage( adcV, adcV_channel);
        m_DeviceManager7_t.SetADCCurrent( adcC, adcC_channel);
        m_DeviceManager7_t.SetDAC( dac, dac_channel);
        m_DeviceManager7_t.SetRelay(relay, relay_channel);
        m_DeviceManager7_t.SetEnabled( false);
        
        
        //*******************************************************************
        // Setting neighbours
        m_DeviceManager0_a.SetNeighbourManager( m_DeviceManager0_t);
        m_DeviceManager0_t.SetNeighbourManager( m_DeviceManager0_a);
        m_DeviceManager1_a.SetNeighbourManager( m_DeviceManager1_t);
        m_DeviceManager1_t.SetNeighbourManager( m_DeviceManager1_a);
        m_DeviceManager2_a.SetNeighbourManager( m_DeviceManager2_t);
        m_DeviceManager2_t.SetNeighbourManager( m_DeviceManager2_a);
        m_DeviceManager3_a.SetNeighbourManager( m_DeviceManager3_t);
        m_DeviceManager3_t.SetNeighbourManager( m_DeviceManager3_a);
        m_DeviceManager4_a.SetNeighbourManager( m_DeviceManager4_t);
        m_DeviceManager4_t.SetNeighbourManager( m_DeviceManager4_a);
        m_DeviceManager5_a.SetNeighbourManager( m_DeviceManager5_t);
        m_DeviceManager5_t.SetNeighbourManager( m_DeviceManager5_a);
        m_DeviceManager6_a.SetNeighbourManager( m_DeviceManager6_t);
        m_DeviceManager6_t.SetNeighbourManager( m_DeviceManager6_a);
        m_DeviceManager7_a.SetNeighbourManager( m_DeviceManager7_t);
        m_DeviceManager7_t.SetNeighbourManager( m_DeviceManager7_a);
    }
    
    
    public void openPort() {
        try {
            if( m_rxtx != null) {
                closePort();
                m_rxtx = null;
            }
            m_rxtx = new TwoWaySerialComm();
            
            //m_rxtx.connect( port);//"/dev/ttyUSB0");
            m_rxtx.connect( m_Settings.GetCOMPortSettings());
        } catch( Exception ex) {
            logger.error( "Exception on open COM port device", ex);
            MessageBoxError( "При открытии COM-порта произошла исключительная ситуация.\nПроверьте параметры в настройках.", "Ошибка");
        }
    }
    
    public void closePort() {
        try {
            m_rxtx.disconnect( m_Settings.GetCOMPortSettings());
            m_rxtx = null;
        } catch( Exception ex) {
            logger.error( "Exception on open COM port device", ex);
            MessageBoxError( "При закрытии COM-порта произошла исключительная ситуация.", "Ошибка");
        }
    }
    
    /**
     * Queue initialize commands, and queue first poling commands (starts polling)
     */
    public void QueueStartPollingCommands() {
        String strCmd;
        ArrayList lstInitCmds;
        Iterator it;
        
        Adam4068 relay;
        Adam4017plus adc;
        Adam4024 dac;
        
        //RELAY1
        try {
            relay = m_devicesSet.GetRelay( AMSConstants.REL1);
            lstInitCmds = relay.initialize( GetSettings().GetREL1Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) relay);
                }
            }
            relay.QueueRequestDataCommand();
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации RELAY1", e);
            return;
        }
        
        //RELAY2
        try {
            relay = m_devicesSet.GetRelay( AMSConstants.REL2);
            lstInitCmds = relay.initialize( GetSettings().GetREL2Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) relay);
                }
            }
            relay.QueueRequestDataCommand();
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации RELAY2", e);
            return;
        }
        
        //**********************************************************************
        //ADC1
        try {
            adc = m_devicesSet.GetADC( AMSConstants.ADC1);
            lstInitCmds = adc.initialize( GetSettings().GetADC1Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) adc);
                }
            }
            //TODO корекция на измеряемое на 500кОм напряжения напряжения -= m_ADC_anode_voltages / (5 * 10^5)
            logger.info( "TODO: Serge note: корекция на измеряемое на 500кОм напряжения напряжения -= m_ADC_anode_voltages / (5 * 10^5)");
            m_rxtx.AddCommandToQueue( adc.GetRequestDataCommand(), adc);
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации ADC1", e);
            return;
        }
        
        //****************************************************************
        //ADC2
        try {
            adc = m_devicesSet.GetADC( AMSConstants.ADC2);
            lstInitCmds = adc.initialize( GetSettings().GetADC2Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) adc);
                }
            }
            m_rxtx.AddCommandToQueue( adc.GetRequestDataCommand(), adc);
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации ADC2", e);
            return;
        }
                
        
        //**********************************************************************
        //ADC3
        try {
            adc = m_devicesSet.GetADC( AMSConstants.ADC3);
            lstInitCmds = adc.initialize( GetSettings().GetADC3Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) adc);
                }
            }
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации ADC3", e);
            return;
        }
        //TODO корекция на измеряемое на 500кОм напряжения напряжения -= m_ADC_anode_voltages / (5 * 10^5)
        logger.info( "TODO: Serge note: корекция на измеряемое на 500кОм напряжения напряжения -= m_ADC_tubulations_voltages / (5 * 10^5)");
        m_rxtx.AddCommandToQueue( adc.GetRequestDataCommand(), adc);
        
        
        //**********************************************************************
        //ADC4
        try {
            adc = m_devicesSet.GetADC( AMSConstants.ADC4);
            lstInitCmds = adc.initialize( GetSettings().GetADC4Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) adc);
                }
            }
            m_rxtx.AddCommandToQueue( adc.GetRequestDataCommand(), adc);
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации ADC4", e);
            return;
        }
        
        //**********************************************************************
        //DAC1
        try {
            dac = m_devicesSet.GetDAC( AMSConstants.DAC1);
            lstInitCmds = dac.initialize( GetSettings().GetDAC1Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) dac);
                }
            }
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации DAC1", e);
            return;
        }
        
        
        //DAC2
        try {
            dac = m_devicesSet.GetDAC( AMSConstants.DAC2);
            lstInitCmds = dac.initialize( GetSettings().GetDAC2Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) dac);
                }
            }
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации DAC2", e);
            return;
        }
        
        //**********************************************************************
        //DAC3
        try {
            dac = m_devicesSet.GetDAC( AMSConstants.DAC3);
            lstInitCmds = dac.initialize( GetSettings().GetDAC3Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) dac);
                }
            }
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации DAC3", e);
            return;
        }
        
        //DAC4
        try {
            dac = m_devicesSet.GetDAC( AMSConstants.DAC4);
            lstInitCmds = dac.initialize( GetSettings().GetDAC4Addr(), true);
            it = lstInitCmds.iterator();
            while( it.hasNext()) {
                Object obj = it.next();
                if( obj.getClass().equals( String.class)) {
                    strCmd = ( String) obj;
                    if( !strCmd.isEmpty())
                        m_rxtx.AddCommandToQueue( strCmd, ( AbstractDevice) dac);
                }
            }
        }
        catch( Exception e) {
            logger.fatal( "Exception при инициализации DAC4", e);
            return;
        }
    }
    
    public void start() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        m_pMainWnd = new MainFrame3( this);
        java.awt.EventQueue.invokeLater( new Runnable() {
            @Override
            public void run() {
                m_pMainWnd.setVisible( true);                
                //String str =  Thread.currentThread().getContextClassLoader().getResource();
            }
        });
        
        
        //theApp.openPort( "/dev/ttyS0");
        //theApp.openPort( "/dev/ttyUSB0");
        openPort();
        
        //ИНИЦИАЛИЗАЦИЯ ADAM-УСТРОЙСТВ
        QueueStartPollingCommands();
    }
    
    public static void main( String args[]) {
        //BasicConfigurator.configure();
        
        String strAMSrootEnvVar = System.getenv( "AMS_ROOT");
        if( strAMSrootEnvVar == null) {
            MessageBoxError( "Не задана переменная окружения AMS_ROOT!", "Ошибка");
            return;
        }
        
        String strlog4jPropertiesFile = strAMSrootEnvVar + "/log4j.properties";
        File file = new File( strlog4jPropertiesFile);
        if(!file.exists())
            System.out.println("It is not possible to load the given log4j properties file :"+file.getAbsolutePath());
        else
            PropertyConfigurator.configure( file.getAbsolutePath());
        //logger.setLevel( LOG_LEVEL);
        
        //Тут вызывается конструктор AMSApp (т.е. в момент конструктора 
        //потом вызвается его метод start, где создается окно,  потом инициализируются устройства
        try {
            new AMSApp().start();
        }
        catch( Exception ex) {
            logger.error( "Exception caught while constructing theApp", ex);
            MessageBoxError( "Произошёл сбой при запуске приложения. Подробная информация в журнале.", "Ошибка");
            return;
        }
        
        String strDataFilesDirectory = strAMSrootEnvVar + "/DevicesData";
        if( !( new File( strDataFilesDirectory).exists())) {
            boolean bSuccess = (new File( strDataFilesDirectory)).mkdirs();
            if (!bSuccess) {
                // Directory creation failed
                logger.error( "Problems while creating non-existing DevicesData directory");
                MessageBoxError( "Произошёл сбой при запуске приложения. Подробная информация в журнале.", "Ошибка");
            }   
        }
    }
    
    public void ManageAdcChannel( Adam4017plus adc, int nChannel) {
        AMSDeviceManager processor = null;
        
        if( m_nRegime == this.REGIME_PROCESSING) {
            logger.info( "Режим 'обработка'. Управление каналами отключено - напряжение управляется DAC'ом вручную.");
            return;
        }
        
        boolean bIsCurrent = false;
        boolean bIsVoltage = false;
        
        if( adc.GetChannelDescriptor( nChannel) == AMSConstants.ADC_ANODE_CURRENT ||
            adc.GetChannelDescriptor( nChannel) == AMSConstants.ADC_TUBULATION_CURRENT)
            bIsCurrent = true;
        
        if( adc.GetChannelDescriptor( nChannel) == AMSConstants.ADC_ANODE_VOLTAGE ||
            adc.GetChannelDescriptor( nChannel) == AMSConstants.ADC_TUBULATION_VOLTAGE)
            bIsVoltage = true;
        
        if( bIsCurrent) {  
            //ЭТО АЦП ТОКОВ
            logger.debug( "Обработка сигнала измеренного тока. Устройство: " + adc.GetDescription() + ". Канал: " + nChannel);
            
            logger.trace( "adc=" + adc);
            /*logger.debug( "dev0_a_adcCur=" + m_DeviceManager0_a.GetADCCurrent());
            logger.debug( "dev0_t_adcCur=" + m_DeviceManager1_a.GetADCCurrent());*/
            
            /*
            if( "02".equals( adc.GetAddress()) ) processor = m_DeviceManager0_a;
            if( "12".equals( adc.GetAddress()) ) processor = m_DeviceManager0_t;
            */
            
            
            if( m_DeviceManager0_a.GetADCCurrent()== adc && m_DeviceManager0_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager0_a;
            if( m_DeviceManager0_t.GetADCCurrent()== adc && m_DeviceManager0_t.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager0_t;
        
            if( m_DeviceManager1_a.GetADCCurrent() == adc && m_DeviceManager1_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager1_a;
            if( m_DeviceManager1_t.GetADCCurrent() == adc && m_DeviceManager1_t.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager1_t;
        
            if( m_DeviceManager2_a.GetADCCurrent() == adc && m_DeviceManager2_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager2_a;
            if( m_DeviceManager3_a.GetADCCurrent() == adc && m_DeviceManager3_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager3_a;
            
            if( m_DeviceManager2_a.GetADCCurrent() == adc && m_DeviceManager2_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager2_a;
            if( m_DeviceManager2_t.GetADCCurrent() == adc && m_DeviceManager2_t.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager2_t;
        
            if( m_DeviceManager3_a.GetADCCurrent() == adc && m_DeviceManager3_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager3_a;
            if( m_DeviceManager3_t.GetADCCurrent() == adc && m_DeviceManager3_t.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager3_t;
        
            if( m_DeviceManager4_a.GetADCCurrent() == adc && m_DeviceManager4_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager4_a;
            if( m_DeviceManager4_t.GetADCCurrent() == adc && m_DeviceManager4_t.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager4_t;
        
            if( m_DeviceManager5_a.GetADCCurrent() == adc && m_DeviceManager5_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager5_a;
            if( m_DeviceManager5_t.GetADCCurrent() == adc && m_DeviceManager5_t.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager5_t;
        
            if( m_DeviceManager6_a.GetADCCurrent() == adc && m_DeviceManager6_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager6_a;
            if( m_DeviceManager6_t.GetADCCurrent() == adc && m_DeviceManager6_t.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager6_t;
        
            if( m_DeviceManager7_a.GetADCCurrent() == adc && m_DeviceManager7_a.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager7_a;
            if( m_DeviceManager7_t.GetADCCurrent() == adc && m_DeviceManager7_t.GetAdcCurrentChannel() == nChannel) processor = m_DeviceManager7_t;
            
        
            if( processor != null) {
                logger.info( "ManageChannel(): Нашли управленца для заданного устройства (" + adc.GetDescription() + ") и канала [" + nChannel+ "]!");
                processor.ActionC();
            }
            else {
                logger.error( "ManageChannel(): Не смогли найти управляющий объект для заданного устройства (" + adc.GetDescription() +
                                ") и канала [" + nChannel+ "]!");                    
            
                /*
                logger.error( "ManageChannel(): in: " + adc);
                logger.error( "ManageChannel(): d0: " + m_DeviceManager0.GetADC());
            
                logger.error( "ManageChannel(): in: " + nChannel);
                logger.error( "ManageChannel(): d0: " + m_DeviceManager0.GetAdcChannel());
                */
            }
        }
        else if( bIsVoltage) {
            
            //ЭТО АЦП НАПРЯЖЕНИЙ
            logger.debug( "Обработка сигнала измеренного напряжения. Устройство: " + adc.GetDescription() + ". Канал: " + nChannel);
            
            logger.trace( "adc=" + adc);
            /*
            logger.trace( "dev0_a_adcVol=" + m_DeviceManager0_a.GetADCVoltage());
            logger.trace( "dev0_t_adcVol=" + m_DeviceManager0_t.GetADCVoltage());
            */
            
            /*
            if( "01".equals( adc.GetAddress()) ) processor = m_DeviceManager0_a;
            if( "11".equals( adc.GetAddress()) ) processor = m_DeviceManager0_t;
            */
            
            
            if( m_DeviceManager0_a.GetADCVoltage()== adc && m_DeviceManager0_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager0_a;
            if( m_DeviceManager0_t.GetADCVoltage()== adc && m_DeviceManager0_t.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager0_t;
            
            if( m_DeviceManager1_a.GetADCVoltage() == adc && m_DeviceManager1_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager1_a;
            if( m_DeviceManager1_t.GetADCVoltage() == adc && m_DeviceManager1_t.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager1_t;
        
            if( m_DeviceManager2_a.GetADCVoltage() == adc && m_DeviceManager2_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager2_a;
            if( m_DeviceManager3_a.GetADCVoltage() == adc && m_DeviceManager3_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager3_a;
            
            if( m_DeviceManager3_a.GetADCVoltage() == adc && m_DeviceManager3_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager3_a;
            if( m_DeviceManager3_t.GetADCVoltage() == adc && m_DeviceManager3_t.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager3_t;
        
            if( m_DeviceManager4_a.GetADCVoltage() == adc && m_DeviceManager4_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager4_a;
            if( m_DeviceManager4_t.GetADCVoltage() == adc && m_DeviceManager4_t.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager4_t;
        
            if( m_DeviceManager5_a.GetADCVoltage() == adc && m_DeviceManager5_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager5_a;
            if( m_DeviceManager5_t.GetADCVoltage() == adc && m_DeviceManager5_t.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager5_t;
        
            if( m_DeviceManager6_a.GetADCVoltage() == adc && m_DeviceManager6_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager6_a;
            if( m_DeviceManager6_t.GetADCVoltage() == adc && m_DeviceManager6_t.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager6_t;
        
            if( m_DeviceManager7_a.GetADCVoltage() == adc && m_DeviceManager7_a.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager7_a;
            if( m_DeviceManager7_t.GetADCVoltage() == adc && m_DeviceManager7_t.GetAdcVoltageChannel() == nChannel) processor = m_DeviceManager7_t;
            
            if( processor != null) {
                logger.info( "ManageChannel(): Нашли управленца для заданного устройства (" + adc.GetDescription() + ") и канала [" + nChannel+ "]!");
                processor.ActionV();
            }
            else {
                logger.error( "ManageChannel(): Не смогли найти управляющий объект для заданного устройства (" + adc.GetDescription() +
                                ") и канала [" + nChannel+ "]!");                    
            
                /*
                logger.error( "ManageChannel(): in: " + adc);
                logger.error( "ManageChannel(): d0: " + m_DeviceManager0.GetADC());
            
                logger.error( "ManageChannel(): in: " + nChannel);
                logger.error( "ManageChannel(): d0: " + m_DeviceManager0.GetAdcChannel());
                */
            }
        }
        else {
            logger.error( "ManageChannel(): Не смог определить объект измерения АЦП (" + adc.GetDescription() + ") канал " + nChannel + ". Токи это или напряжения?");
        }
    }
    
    /**
     * Функция для сообщения пользователю информационного сообщения
     * @param strMessage сообщение
     * @param strTitleBar заголовок
     */
    public static void MessageBoxInfo( String strMessage, String strTitleBar)
    {
        JOptionPane.showMessageDialog( null, strMessage, strTitleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Функция для сообщения пользователю сообщения об ошибке
     * @param strMessage сообщение
     * @param strTitleBar заголовок
     */
    public static void MessageBoxError( String strMessage, String strTitleBar)
    {
        JOptionPane.showMessageDialog( null, strMessage, strTitleBar, JOptionPane.ERROR_MESSAGE);
    }
}
