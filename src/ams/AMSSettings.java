/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams;

import ams.serial.COMPortSettings;
import gnu.io.SerialPort;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author yaroslav
 */
public class AMSSettings {
    //MEMBERS
    private final COMPortSettings m_pCOMPortSettings;
    
    private String m_strADC3Address;
    private String m_strADC4Address;
    private String m_strDAC3Address;
    private String m_strDAC4Address;
    private String m_strREL2Address;
    
    private String m_strADC1Address;
    private String m_strADC2Address;
    private String m_strDAC1Address;
    private String m_strDAC2Address;
    private String m_strREL1Address;
    
    private final HashMap m_mapDevs;
    
    //GETTERS
    public COMPortSettings GetCOMPortSettings() { return m_pCOMPortSettings; }    
    
    public String GetADC3Addr() { return m_strADC3Address;}
    public String GetADC4Addr() { return m_strADC4Address;}
    public String GetDAC3Addr() { return m_strDAC3Address;}
    public String GetDAC4Addr() { return m_strDAC4Address;}
    public String GetREL2Addr() { return m_strREL2Address;}
    
    public String GetADC1Addr() { return m_strADC1Address;}
    public String GetADC2Addr() { return m_strADC2Address;}
    public String GetDAC1Addr() { return m_strDAC1Address;}
    public String GetDAC2Addr() { return m_strDAC2Address;}
    public String GetREL1Addr() { return m_strREL1Address;}
    
    public AMSSettingsTDev GetDev( int nDevice) throws Exception {
        if( nDevice != AMSConstants.T_DEVICE1 &&
                nDevice != AMSConstants.T_DEVICE2 &&
                nDevice != AMSConstants.T_DEVICE3 &&
                nDevice != AMSConstants.T_DEVICE4 &&
                nDevice != AMSConstants.T_DEVICE5 &&
                nDevice != AMSConstants.T_DEVICE6 &&
                nDevice != AMSConstants.T_DEVICE7 &&
                nDevice != AMSConstants.T_DEVICE8)
        
            throw new Exception( "Wrong device descriptor!");
        
        return ( AMSSettingsTDev) m_mapDevs.get( nDevice);
    }
    public AMSSettingsTDev GetDev1() { return ( AMSSettingsTDev) m_mapDevs.get(AMSConstants.T_DEVICE1);}
    public AMSSettingsTDev GetDev2() { return ( AMSSettingsTDev) m_mapDevs.get(AMSConstants.T_DEVICE2);}
    public AMSSettingsTDev GetDev3() { return ( AMSSettingsTDev) m_mapDevs.get(AMSConstants.T_DEVICE3);}
    public AMSSettingsTDev GetDev4() { return ( AMSSettingsTDev) m_mapDevs.get(AMSConstants.T_DEVICE4);}
    public AMSSettingsTDev GetDev5() { return ( AMSSettingsTDev) m_mapDevs.get(AMSConstants.T_DEVICE5);}
    public AMSSettingsTDev GetDev6() { return ( AMSSettingsTDev) m_mapDevs.get(AMSConstants.T_DEVICE6);}
    public AMSSettingsTDev GetDev7() { return ( AMSSettingsTDev) m_mapDevs.get(AMSConstants.T_DEVICE7);}
    public AMSSettingsTDev GetDev8() { return ( AMSSettingsTDev) m_mapDevs.get(AMSConstants.T_DEVICE8);}
    
    
    //SETTERS
    public void SetADC3Addr( String val) { m_strADC3Address = val;}
    public void SetADC4Addr( String val) { m_strADC4Address = val;}
    public void SetDAC3Addr( String val) { m_strDAC3Address = val;}
    public void SetDAC4Addr( String val) { m_strDAC4Address = val;}
    public void SetREL2Addr( String val) { m_strREL2Address = val;}
    
    public void SetADC1Addr( String val) { m_strADC1Address = val;}
    public void SetADC2Addr( String val) { m_strADC2Address = val;}
    public void SetDAC1Addr( String val) { m_strDAC1Address = val;}
    public void SetDAC2Addr( String val) { m_strDAC2Address = val;}
    public void SetREL1Addr( String val) { m_strREL1Address = val;}
    
    //REST
    private final AMSApp theApp;
    static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger( AMSSettings.class);
    
    public AMSSettings( AMSApp app) {
        m_pCOMPortSettings = new COMPortSettings();
        theApp = app;
        
        //Default values for ADC,DAC,REL modules
        m_strADC1Address = "01";
        m_strADC2Address = "02";
        m_strDAC1Address = "03";
        m_strDAC2Address = "04";
        m_strREL1Address = "05";
        
        m_strADC3Address = "11";
        m_strADC4Address = "12";
        m_strDAC3Address = "13";
        m_strDAC4Address = "14";
        m_strREL2Address = "15";        
        
        m_mapDevs = new HashMap( 8);
        
        AMSSettingsTDev dev;
        
        //DEVICE1
        dev = new AMSSettingsTDev( AMSConstants.T_DEVICE1);
        dev.SetAnoAdcVolt( AMSConstants.ADC1, AMSConstants.CHANNEL1);
        dev.SetAnoAdcCurr( AMSConstants.ADC2, AMSConstants.CHANNEL1);
        dev.SetAnoDac( AMSConstants.DAC1, AMSConstants.CHANNEL1);
        dev.SetAnoRel( AMSConstants.REL1, AMSConstants.CHANNEL1);        
        dev.SetTubAdcVolt( AMSConstants.ADC3, AMSConstants.CHANNEL1);
        dev.SetTubAdcCurr( AMSConstants.ADC4, AMSConstants.CHANNEL1);
        dev.SetTubDac( AMSConstants.DAC3, AMSConstants.CHANNEL1);
        dev.SetTubRel( AMSConstants.REL2, AMSConstants.CHANNEL1);
        m_mapDevs.put(AMSConstants.T_DEVICE1, dev);
        
        //DEVICE2
        dev = new AMSSettingsTDev( AMSConstants.T_DEVICE2);
        dev.SetAnoAdcVolt( AMSConstants.ADC1, AMSConstants.CHANNEL2);
        dev.SetAnoAdcCurr( AMSConstants.ADC2, AMSConstants.CHANNEL2);
        dev.SetAnoDac( AMSConstants.DAC1, AMSConstants.CHANNEL2);
        dev.SetAnoRel( AMSConstants.REL1, AMSConstants.CHANNEL2);
        dev.SetTubAdcVolt( AMSConstants.ADC3, AMSConstants.CHANNEL2);
        dev.SetTubAdcCurr( AMSConstants.ADC4, AMSConstants.CHANNEL2);
        dev.SetTubDac( AMSConstants.DAC3, AMSConstants.CHANNEL2);
        dev.SetTubRel( AMSConstants.REL2, AMSConstants.CHANNEL2);
        m_mapDevs.put(AMSConstants.T_DEVICE2, dev);
        
        
        //DEVICE3
        dev = new AMSSettingsTDev( AMSConstants.T_DEVICE3);
        dev.SetAnoAdcVolt( AMSConstants.ADC1, AMSConstants.CHANNEL3);
        dev.SetAnoAdcCurr( AMSConstants.ADC2, AMSConstants.CHANNEL3);
        dev.SetAnoDac( AMSConstants.DAC1, AMSConstants.CHANNEL3);
        dev.SetAnoRel( AMSConstants.REL1, AMSConstants.CHANNEL3);
        dev.SetTubAdcVolt( AMSConstants.ADC3, AMSConstants.CHANNEL3);
        dev.SetTubAdcCurr( AMSConstants.ADC4, AMSConstants.CHANNEL3);
        dev.SetTubDac( AMSConstants.DAC3, AMSConstants.CHANNEL3);
        dev.SetTubRel( AMSConstants.REL2, AMSConstants.CHANNEL3);
        m_mapDevs.put(AMSConstants.T_DEVICE3, dev);
        
        
        //DEVICE4
        dev = new AMSSettingsTDev( AMSConstants.T_DEVICE4);
        dev.SetAnoAdcVolt( AMSConstants.ADC1, AMSConstants.CHANNEL4);
        dev.SetAnoAdcCurr( AMSConstants.ADC2, AMSConstants.CHANNEL4);
        dev.SetAnoDac( AMSConstants.DAC1, AMSConstants.CHANNEL4);
        dev.SetAnoRel( AMSConstants.REL1, AMSConstants.CHANNEL4);
        dev.SetTubAdcVolt( AMSConstants.ADC3, AMSConstants.CHANNEL4);
        dev.SetTubAdcCurr( AMSConstants.ADC4, AMSConstants.CHANNEL4);
        dev.SetTubDac( AMSConstants.DAC3, AMSConstants.CHANNEL4);
        dev.SetTubRel( AMSConstants.REL2, AMSConstants.CHANNEL4);
        m_mapDevs.put(AMSConstants.T_DEVICE4, dev);
        
        
        //DEVICE5
        dev = new AMSSettingsTDev( AMSConstants.T_DEVICE5);
        dev.SetAnoAdcVolt( AMSConstants.ADC1, AMSConstants.CHANNEL5);
        dev.SetAnoAdcCurr( AMSConstants.ADC2, AMSConstants.CHANNEL5);
        dev.SetAnoDac( AMSConstants.DAC2, AMSConstants.CHANNEL1);
        dev.SetAnoRel( AMSConstants.REL1, AMSConstants.CHANNEL5);
        dev.SetTubAdcVolt( AMSConstants.ADC3, AMSConstants.CHANNEL5);
        dev.SetTubAdcCurr( AMSConstants.ADC4, AMSConstants.CHANNEL5);
        dev.SetTubDac( AMSConstants.DAC4, AMSConstants.CHANNEL1);
        dev.SetTubRel( AMSConstants.REL2, AMSConstants.CHANNEL5);
        m_mapDevs.put(AMSConstants.T_DEVICE5, dev);
        
        
        //DEVICE6
        dev = new AMSSettingsTDev( AMSConstants.T_DEVICE6);
        dev.SetAnoAdcVolt( AMSConstants.ADC1, AMSConstants.CHANNEL6);
        dev.SetAnoAdcCurr( AMSConstants.ADC2, AMSConstants.CHANNEL6);
        dev.SetAnoDac( AMSConstants.DAC2, AMSConstants.CHANNEL2);
        dev.SetAnoRel( AMSConstants.REL1, AMSConstants.CHANNEL6);
        dev.SetTubAdcVolt( AMSConstants.ADC3, AMSConstants.CHANNEL6);
        dev.SetTubAdcCurr( AMSConstants.ADC4, AMSConstants.CHANNEL6);
        dev.SetTubDac( AMSConstants.DAC4, AMSConstants.CHANNEL2);
        dev.SetTubRel( AMSConstants.REL2, AMSConstants.CHANNEL6);
        m_mapDevs.put(AMSConstants.T_DEVICE6, dev);
        
        
        //DEVICE7
        dev = new AMSSettingsTDev( AMSConstants.T_DEVICE7);
        dev.SetAnoAdcVolt( AMSConstants.ADC1, AMSConstants.CHANNEL7);
        dev.SetAnoAdcCurr( AMSConstants.ADC2, AMSConstants.CHANNEL7);
        dev.SetAnoDac( AMSConstants.DAC2, AMSConstants.CHANNEL3);
        dev.SetAnoRel( AMSConstants.REL1, AMSConstants.CHANNEL7);
        dev.SetTubAdcVolt( AMSConstants.ADC3, AMSConstants.CHANNEL7);
        dev.SetTubAdcCurr( AMSConstants.ADC4, AMSConstants.CHANNEL7);
        dev.SetTubDac( AMSConstants.DAC4, AMSConstants.CHANNEL3);
        dev.SetTubRel( AMSConstants.REL2, AMSConstants.CHANNEL7);
        m_mapDevs.put(AMSConstants.T_DEVICE7, dev);
        
        
        //DEVICE8
        dev = new AMSSettingsTDev( AMSConstants.T_DEVICE8);
        dev.SetAnoAdcVolt( AMSConstants.ADC1, AMSConstants.CHANNEL8);
        dev.SetAnoAdcCurr( AMSConstants.ADC2, AMSConstants.CHANNEL8);
        dev.SetAnoDac( AMSConstants.DAC2, AMSConstants.CHANNEL4);
        dev.SetAnoRel( AMSConstants.REL1, AMSConstants.CHANNEL8);
        dev.SetTubAdcVolt( AMSConstants.ADC3, AMSConstants.CHANNEL8);
        dev.SetTubAdcCurr( AMSConstants.ADC4, AMSConstants.CHANNEL8);
        dev.SetTubDac( AMSConstants.DAC4, AMSConstants.CHANNEL4);
        dev.SetTubRel( AMSConstants.REL2, AMSConstants.CHANNEL8);
        m_mapDevs.put(AMSConstants.T_DEVICE8, dev);
    }
    
    /**
     * Функция загрузки текущих настроек в диалог редактирования и его отображения
    */
    public void ShowSettingsDialog() {
        theApp.closePort(); //открываем обратно порт в трех местах: чуть ниже (если вышли через крест), и в DlgSettings2.java onButtonExit, OnButtonSave
        DlgSettings2 dlg = new DlgSettings2( theApp.m_pMainWnd, true, theApp);
        
        dlg.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                theApp.openPort();
                theApp.QueueStartPollingCommands();
            }
        });
        
        dlg.LoadDataFromSettings();
        dlg.setVisible( true);
    }
    
    /**
     * Функция сохранения настроек в .xml файл
     */
    public void SaveSettings() {
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement( "Settings" );
            
            
            root.addElement( "COM_Port"     ).addText( m_pCOMPortSettings.GetPort());
            
            root.addElement( "COM_Baudrate" ).addText( Integer.toString( m_pCOMPortSettings.GetBaudRate()));
            
            if( m_pCOMPortSettings.GetDataBits() == SerialPort.DATABITS_5)
                root.addElement( "COM_DataBits" ).addText( "5");
            else if( m_pCOMPortSettings.GetDataBits() == SerialPort.DATABITS_6)
                root.addElement( "COM_DataBits" ).addText( "6");
            else if( m_pCOMPortSettings.GetDataBits() == SerialPort.DATABITS_7)
                root.addElement( "COM_DataBits" ).addText( "7");
            else if( m_pCOMPortSettings.GetDataBits() == SerialPort.DATABITS_8)
                root.addElement( "COM_DataBits" ).addText( "8");
            else {
                logger.warn( "Unknown COM_DataBits value '" + m_pCOMPortSettings.GetDataBits() + "'! Saving default!");
                root.addElement( "COM_DataBits" ).addText( "8");
            }
            
            switch( m_pCOMPortSettings.GetParity()) {
                case SerialPort.PARITY_NONE:    root.addElement( "COM_Parity" ).addText( "None");   break;
                case SerialPort.PARITY_ODD:     root.addElement( "COM_Parity" ).addText( "Odd");    break;
                case SerialPort.PARITY_EVEN:    root.addElement( "COM_Parity" ).addText( "Even");   break;
                case SerialPort.PARITY_MARK:    root.addElement( "COM_Parity" ).addText( "Mark");   break;
                case SerialPort.PARITY_SPACE:   root.addElement( "COM_Parity" ).addText( "Space");  break;
                default:
                    logger.warn( "Unknown COM_Parity value '" + m_pCOMPortSettings.GetParity() + "'! Saving default!");
                    root.addElement( "COM_Parity" ).addText( "None");
                break;
            }

            switch( m_pCOMPortSettings.GetStopBits()) {
                case SerialPort.STOPBITS_1: root.addElement( "COM_StopBits" ).addText( "1"); break;
                case SerialPort.STOPBITS_2: root.addElement( "COM_StopBits" ).addText( "2"); break;
                default: 
                    logger.warn( "Unknown COM_StopBits value '" + m_pCOMPortSettings.GetStopBits() + "'! Saving default!");
                    root.addElement( "COM_StopBits" ).addText( "1");
                break;
            }
            
            
            Element adams = root.addElement( "Devices" );
            adams.addElement( "ADC1").addText( m_strADC1Address);
            adams.addElement( "ADC2").addText( m_strADC2Address);
            adams.addElement( "DAC1").addText( m_strDAC1Address);
            adams.addElement( "DAC2").addText( m_strDAC2Address);
            adams.addElement( "REL1").addText( m_strREL1Address);
            adams.addElement( "ADC3").addText( m_strADC3Address);
            adams.addElement( "ADC4").addText( m_strADC4Address);
            adams.addElement( "DAC3").addText( m_strDAC3Address);
            adams.addElement( "DAC4").addText( m_strDAC4Address);
            adams.addElement( "REL2").addText( m_strREL2Address);
            
            // ***** ***** ***** ***** *****
            //
            //Iterator it = AMSConstants.getInstance().T_DEVICES.iterator();
            //while( it.hasNext()) {            
            for( int nDevice = AMSConstants.T_DEVICE1; nDevice <= AMSConstants.T_DEVICE8; nDevice++) {
                
                //int nDevice = ( int) it.next();
            
                Element device = root.addElement( "Device" + nDevice + "_channels" );            
                Element devAn = device.addElement( "anode");
                devAn.addElement( "ADC_V_DEV").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetAnoAdcVoltDev());
                devAn.addElement( "ADC_V_CHAN").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetAnoAdcVoltChan());
                
                devAn.addElement( "ADC_C_DEV").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetAnoAdcCurrDev());
                devAn.addElement( "ADC_C_CHAN").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetAnoAdcCurrChan());
                
                devAn.addElement( "DAC_DEV").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetAnoDacDev());
                devAn.addElement( "DAC_CHAN").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetAnoDacChan());
                
                devAn.addElement( "REL_DEV").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetAnoRelDev());
                devAn.addElement( "REL_CHAN").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetAnoRelChan());
            
                Element devTu = device.addElement( "tubulation");
                devTu.addElement( "ADC_V_DEV").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetTubAdcVoltDev());
                devTu.addElement( "ADC_V_CHAN").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetTubAdcVoltChan());
                
                devTu.addElement( "ADC_C_DEV").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetTubAdcCurrDev());
                devTu.addElement( "ADC_C_CHAN").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetTubAdcCurrChan());
                
                devTu.addElement( "DAC_DEV").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetTubDacDev());
                devTu.addElement( "DAC_CHAN").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetTubDacChan());
                
                devTu.addElement( "REL_DEV").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetTubRelDev());
                devTu.addElement( "REL_CHAN").addText(
                        "" + ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).GetTubRelChan());
            
            }
            
            OutputFormat format = OutputFormat.createPrettyPrint();
            String strSettingsXmlFile = System.getenv( "AMS_ROOT") + "/settings.xml";
            XMLWriter writer = new XMLWriter( new FileWriter( strSettingsXmlFile), format);
            
            writer.write( document );
            writer.close();
        } catch (IOException ex) {
            logger.error( "IOException caught while saving settings!", ex);
        }
    }
    
    /**
     * Функция загрузки настроек из .xml файла
     * @return 
     * true  - загрузка прошла успешно
     * false - загрузка прошла с ошибками
     */
    public boolean LoadSettings() {
        boolean bResOk = true;
        try {
            SAXReader reader = new SAXReader();
            
            String strSettingsFilePathName = System.getenv( "AMS_ROOT") + "/settings.xml";
            URL url = ( new java.io.File( strSettingsFilePathName)).toURI().toURL();
            
            Document document = reader.read( url);
            
            Element root = document.getRootElement();

            // iterate through child elements of root
            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                String name = element.getName();
                String value = element.getText();
                
                //logger.debug( "Pairs: [" + name + " : " + value + "]");
                
                if( "COM_Port".equals( name)) m_pCOMPortSettings.SetPort( value);
                
                if( "COM_Baudrate".equals( name)) m_pCOMPortSettings.SetBaudRate( Integer.parseInt( value));
                
                if( "COM_DataBits".equals( name)) {
                    switch (value) {
                        case "5":   m_pCOMPortSettings.SetDataBits( SerialPort.DATABITS_5); break;
                        case "6":   m_pCOMPortSettings.SetDataBits( SerialPort.DATABITS_6); break;
                        case "7":   m_pCOMPortSettings.SetDataBits( SerialPort.DATABITS_7); break;
                        case "8":   m_pCOMPortSettings.SetDataBits( SerialPort.DATABITS_8); break;
                        default:    logger.warn( "Unknown COM_DataBits value '" + value + "' in settings.xml! Using default!"); bResOk = false; break;
                    }
                }
                        
                if( "COM_Parity".equals( name)) {
                    switch (value) {
                        case "None":    m_pCOMPortSettings.SetParity( SerialPort.PARITY_NONE);  break;
                        case "Odd":     m_pCOMPortSettings.SetParity( SerialPort.PARITY_ODD);   break;
                        case "Even":    m_pCOMPortSettings.SetParity( SerialPort.PARITY_EVEN);  break;
                        case "Mark":    m_pCOMPortSettings.SetParity( SerialPort.PARITY_MARK);  break;
                        case "Space":   m_pCOMPortSettings.SetParity( SerialPort.PARITY_SPACE); break;
                        default:        logger.warn( "Unknown COM_Parity value '" + value + "' in settings.xml! Using default!"); bResOk = false; break;
                    }
                }
                
                if( "COM_StopBits".equals( name)) {
                    switch (value) {
                        case "1":   m_pCOMPortSettings.SetStopBits( SerialPort.STOPBITS_1); break;
                        case "2":   m_pCOMPortSettings.SetParity( SerialPort.STOPBITS_2);   break;
                        default:    logger.warn( "Unknown COM_StopBits value '" + value + "' in settings.xml! Using default!"); bResOk = false; break;
                    }
                }
                
                if( "Devices".equals( name)) {
                    for ( Iterator andevsparams = element.elementIterator(); andevsparams.hasNext(); ) {
                    
                        Element elementAnDev = (Element) andevsparams.next();
                        name = elementAnDev.getName();
                        value = elementAnDev.getText();
                        
                        //logger.debug( "Pairs: Devices.[" + name + " : " + value + "]");
                        
                        boolean bOk = true;
                        try {
                            Integer.parseInt( value, 16);
                        } catch( NumberFormatException e) {
                            logger.error( "Param Anode_devices." + name + " is not a valid hex-number [" + value + "]! using default!");
                            bOk = false;
                            bResOk = false;
                        }
                        
                        if( bOk) {
                            switch( name) {
                                case "ADC1": m_strADC1Address = value; break;
                                case "ADC2": m_strADC2Address = value; break;
                                case "DAC1": m_strDAC1Address = value; break;
                                case "DAC2": m_strDAC2Address = value; break;
                                case "REL1": m_strREL1Address = value; break;
                                case "ADC3": m_strADC3Address = value; break;
                                case "ADC4": m_strADC4Address = value; break;
                                case "DAC3": m_strDAC3Address = value; break;
                                case "DAC4": m_strDAC4Address = value; break;
                                case "REL2": m_strREL2Address = value; break;
                                default:
                                    logger.warn( "Unknown param Device.'" + name + "' in settings.xml! Pay attention!");
                                    bResOk = false;
                            }
                        }
                    }
                }
                
                if( "Device0_channels".equals( name))   bResOk = ParseDeviceBlock(AMSConstants.T_DEVICE1, element) & bResOk;
                if( "Device1_channels".equals( name))   bResOk = ParseDeviceBlock(AMSConstants.T_DEVICE2, element) & bResOk;
                if( "Device2_channels".equals( name))   bResOk = ParseDeviceBlock(AMSConstants.T_DEVICE3, element) & bResOk;
                if( "Device3_channels".equals( name))   bResOk = ParseDeviceBlock(AMSConstants.T_DEVICE4, element) & bResOk;
                if( "Device4_channels".equals( name))   bResOk = ParseDeviceBlock(AMSConstants.T_DEVICE5, element) & bResOk;
                if( "Device5_channels".equals( name))   bResOk = ParseDeviceBlock(AMSConstants.T_DEVICE6, element) & bResOk;
                if( "Device6_channels".equals( name))   bResOk = ParseDeviceBlock(AMSConstants.T_DEVICE7, element) & bResOk;
                if( "Device7_channels".equals( name))   bResOk = ParseDeviceBlock(AMSConstants.T_DEVICE8, element) & bResOk;
                
            }

            
            /*
            // iterate through child elements of root with element name "foo"
            for ( Iterator i = root.elementIterator( "foo" ); i.hasNext(); ) {
                Element foo = (Element) i.next();
                // do something
            }

            // iterate through attributes of root 
            for ( Iterator i = root.attributeIterator(); i.hasNext(); ) {
                Attribute attribute = (Attribute) i.next();
                // do something
            }
            */
            
        } catch( MalformedURLException ex) {
            logger.error( "MalformedURLException caught while loading settings!", ex);
            bResOk = false;
        } catch( DocumentException ex) {
            logger.error( "DocumentException caught while loading settings!", ex);
            bResOk = false;
        }
        
        return bResOk;
    }
    
    /**
     * Вспомогательная функция чтения настроек из .xml файла
     * Зачитывает секцию DeviceX_channels (каналы приборов)
     * @param nDevice       номер прибора стенда
     * @param deviceRoot    .xml-root объекта с настройками каналов
     * @return 
     * true  - загрузка прошла успешно;
     * false - загрузка прошла с ошибками
     */
    private boolean ParseDeviceBlock( int nDevice, Element deviceRoot) {
        boolean bResOk = true;
        for ( Iterator dev = deviceRoot.elementIterator(); dev.hasNext(); ) {
                    
            Element devElem = (Element) dev.next();
            String name = devElem.getName();
                        
            if( name.equals( "anode")) {
                for( Iterator dev_an_chans = devElem.elementIterator(); dev_an_chans.hasNext(); ) {
                    
                    Element elementAnDev = (Element) dev_an_chans.next();
                    name = elementAnDev.getName();
                    String value = elementAnDev.getText();
                    Integer test = new Integer( value);
                    logger.debug( "Pairs: Device" + nDevice + ".anode.[" + name + " : " + value + "]");

                    boolean bOk = true;
                    if( name.contains( "DAC") == true) {
                        if( test.shortValue() < 1 && test.shortValue() > 4) {
                            logger.warn( "Bad value for Device" + nDevice + ".anode.[" + name + "] = '" + value + "'. Using default!");
                            bOk = false;
                            bResOk = false;
                        }
                    }
                    else {
                        if( test.shortValue() < 1 && test.shortValue() > 9) {
                            logger.warn( "Bad value for Device" + nDevice + ".anode.[" + name + "] = '" + value + "'. Using default!");
                            bOk = false;
                            bResOk = false;
                        }
                    }
                    
                    if( bOk) {
                        switch( name) {
                            case "ADC_V_DEV": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetAnoAdcVoltDev( Integer.parseInt( value));   break;
                            case "ADC_V_CHAN": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetAnoAdcVoltChan( Integer.parseInt( value));   break;
                            case "ADC_C_DEV": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetAnoAdcCurrDev( Integer.parseInt( value));   break;
                            case "ADC_C_CHAN": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetAnoAdcCurrChan( Integer.parseInt( value));   break;
                            case "DAC_DEV": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetAnoDacDev( Integer.parseInt( value));   break;
                            case "DAC_CHAN": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetAnoDacChan( Integer.parseInt( value));   break;
                            case "REL_DEV": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetAnoRelDev( Integer.parseInt( value));   break;
                            case "REL_CHAN": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetAnoRelChan( Integer.parseInt( value));   break;
                        }
                    }
                }
            }
            else if( name.equals( "tubulation")) {
                for( Iterator dev_an_chans = devElem.elementIterator(); dev_an_chans.hasNext(); ) {
                    
                    Element elementAnDev = (Element) dev_an_chans.next();
                    name = elementAnDev.getName();
                    String value = elementAnDev.getText();
                    Integer test = new Integer( value);
                    logger.debug( "Pairs: Device" + nDevice + ".tubulation.[" + name + " : " + value + "]");

                    boolean bOk = true;
                    if( name.contains( "DAC") == true) {
                        if( test.shortValue() < 1 && test.shortValue() > 4) {
                            logger.warn( "Bad value for Device" + nDevice + ".tubulation.[" + name + "] = '" + value + "'. Using default!");
                            bOk = false;
                            bResOk = false;
                        }
                    }
                    else {
                        if( test.shortValue() < 1 && test.shortValue() > 9) {
                            logger.warn( "Bad value for Device" + nDevice + ".tubulation.[" + name + "] = '" + value + "'. Using default!");
                            bOk = false;
                            bResOk = false;
                        }
                    }
                    
                    if( bOk) {
                        switch( name) {
                            case "ADC_V_DEV": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetTubAdcVoltDev( Integer.parseInt( value));   break;
                            case "ADC_V_CHAN": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetTubAdcVoltChan( Integer.parseInt( value));   break;
                            case "ADC_C_DEV": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetTubAdcCurrDev( Integer.parseInt( value));   break;
                            case "ADC_C_CHAN": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetTubAdcCurrChan( Integer.parseInt( value));   break;
                            case "DAC_DEV": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetTubDacDev( Integer.parseInt( value));   break;
                            case "DAC_CHAN": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetTubDacChan( Integer.parseInt( value));   break;
                            case "REL_DEV": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetTubRelDev( Integer.parseInt( value));   break;
                            case "REL_CHAN": ( ( AMSSettingsTDev) m_mapDevs.get( nDevice)).SetTubRelChan( Integer.parseInt( value));   break;
                        }
                    }
                }
            }
        }
        return bResOk;
    }
    
    public static void main( String args[]) {
        BasicConfigurator.configure();
        logger.setLevel( Level.TRACE);
        AMSSettings ams = new AMSSettings( null);
        ams.SaveSettings();
        ams.LoadSettings();
    }
}
