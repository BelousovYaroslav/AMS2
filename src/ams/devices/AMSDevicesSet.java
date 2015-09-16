/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ams.devices;

import ams.AMSApp;
import ams.AMSConstants;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class AMSDevicesSet {
    AMSApp theApp;
    static Logger logger = Logger.getLogger( AMSDevicesSet.class);
    private HashMap m_mapDevices;
    
    public AMSDevicesSet( AMSApp app) {
        theApp = app;
    }
    
    public AbstractDevice GetDevice( int nDeviceDescriptor) throws Exception {
        if( nDeviceDescriptor != AMSConstants.ADC1 &&
                nDeviceDescriptor != AMSConstants.ADC2 &&
                nDeviceDescriptor != AMSConstants.ADC3 &&
                nDeviceDescriptor != AMSConstants.ADC4 &&
                nDeviceDescriptor != AMSConstants.DAC1 &&
                nDeviceDescriptor != AMSConstants.DAC2 &&
                nDeviceDescriptor != AMSConstants.DAC3 &&
                nDeviceDescriptor != AMSConstants.DAC4 &&
                nDeviceDescriptor != AMSConstants.REL1 &&
                nDeviceDescriptor != AMSConstants.REL2) {
            throw new Exception( "Некорректный дескриптор устройства '" + nDeviceDescriptor + "'");
        }

        AbstractDevice device = ( AbstractDevice) m_mapDevices.get( nDeviceDescriptor);
        return device;
    }
    
    public Adam4017plus GetADC( int nDeviceDescriptor) throws Exception {
        if( nDeviceDescriptor != AMSConstants.ADC1 &&
                nDeviceDescriptor != AMSConstants.ADC2 &&
                nDeviceDescriptor != AMSConstants.ADC3 &&
                nDeviceDescriptor != AMSConstants.ADC4) {
            throw new Exception( "Некорректный дескриптор устройства '" + nDeviceDescriptor + "'");
        }

        Adam4017plus device = ( Adam4017plus) m_mapDevices.get( nDeviceDescriptor);
        return device;
    }
    
    public Adam4024 GetDAC( int nDeviceDescriptor) throws Exception {
        if( nDeviceDescriptor != AMSConstants.DAC1 &&
                nDeviceDescriptor != AMSConstants.DAC2 &&
                nDeviceDescriptor != AMSConstants.DAC3 &&
                nDeviceDescriptor != AMSConstants.DAC4) {
            throw new Exception( "Некорректный дескриптор устройства '" + nDeviceDescriptor + "'");
        }

        Adam4024 device = ( Adam4024) m_mapDevices.get( nDeviceDescriptor);
        return device;
    }
    
    public Adam4068 GetRelay( int nDeviceDescriptor) throws Exception {
        if( nDeviceDescriptor != AMSConstants.REL1 &&
                nDeviceDescriptor != AMSConstants.REL2) {
            throw new Exception( "Некорректный дескриптор устройства '" + nDeviceDescriptor + "'");
        }

        Adam4068 device = ( Adam4068) m_mapDevices.get( nDeviceDescriptor);
        return device;
    }
    
    public boolean initialize() throws Exception {
        if( theApp.GetSettings() == null) {
            logger.error( "Ошибка при инициализации устройств. Нет настроек.");
            return false;
        }
        
        m_mapDevices = new HashMap( 10);
        //************************************************************
        //Инициализация устройств (АЦП, ЦАПов, Реле)
        //ADC1
        try {
            Adam4017plus adc1 = new Adam4017plus( theApp, AMSConstants.ADC1);
            adc1.SetDescription( "АЦП1");
            m_mapDevices.put( AMSConstants.ADC1, adc1);
            
        } catch( Exception ex) {
            logger.fatal( "Caught exception on creating ADC1 class instances", ex);
            throw new Exception( "Ошибка при инициализации АЦП1!");
        }
        
        //ADC2
        try {
            Adam4017plus adc2 = new Adam4017plus( theApp, AMSConstants.ADC2);
            adc2.SetDescription( "АЦП2");
            m_mapDevices.put( AMSConstants.ADC2, adc2);
        } catch( Exception ex) {
            logger.fatal( "Caught exception on creating ADC2 class instances", ex);
            throw new Exception( "Ошибка при инициализации АЦП2!");
        }
        
        //ADC3
        try {
            Adam4017plus adc3 = new Adam4017plus( theApp, AMSConstants.ADC3);
            adc3.SetDescription( "АЦП3");
            m_mapDevices.put( AMSConstants.ADC3, adc3);
            
        } catch( Exception ex) {
            logger.fatal( "Caught exception on creating ADC3 class instances", ex);
            throw new Exception( "Ошибка при инициализации АЦП3!");
        }
        
        //ADC4
        try {
            Adam4017plus adc4 = new Adam4017plus( theApp, AMSConstants.ADC4);
            adc4.SetDescription( "АЦП4");
            m_mapDevices.put( AMSConstants.ADC4, adc4);
        } catch( Exception ex) {
            logger.fatal( "Caught exception on creating ADC4 class instances", ex);
            throw new Exception( "Ошибка при инициализации АЦП4!");
        }        
        
        //************************************************************
        //DAC1
        try {
            Adam4024 dac1 = new Adam4024( theApp, AMSConstants.DAC1);
            dac1.SetDescription( "ЦАП1");
            m_mapDevices.put( AMSConstants.DAC1, dac1);
        }
        catch( Exception ex) {
            logger.fatal( "Caught exception on creating DAC1 class instances", ex);
            throw new Exception( "Ошибка при инициализации ЦАП1!");
        }
        
        //DAC2
        try {
            Adam4024 dac2 = new Adam4024( theApp, AMSConstants.DAC2);
            dac2.SetDescription( "ЦАП2");
            m_mapDevices.put( AMSConstants.DAC2, dac2);
        }
        catch( Exception ex) {
            logger.fatal( "Caught exception on creating DAC2 class instances", ex);
            throw new Exception( "Ошибка при инициализации ЦАП2!");
        }
        
        //DAC3
        try {
            Adam4024 dac3 = new Adam4024( theApp, AMSConstants.DAC3);
            dac3.SetDescription( "ЦАП3");
            m_mapDevices.put( AMSConstants.DAC3, dac3);
        }
        catch( Exception ex) {
            logger.fatal( "Caught exception on creating DAC3 class instances", ex);
            throw new Exception( "Ошибка при инициализации ЦАП3!");
        }
        
        //DAC4
        try {
            Adam4024 dac4 = new Adam4024( theApp, AMSConstants.DAC4);
            dac4.SetDescription( "ЦАП4");
            m_mapDevices.put( AMSConstants.DAC4, dac4);
        }
        catch( Exception ex) {
            logger.fatal( "Caught exception on creating DAC4 class instances", ex);
            throw new Exception( "Ошибка при инициализации ЦАП4!");
        }
        
        //************************************************************
        //RELAY1
        try {
            Adam4068 rel1 = new Adam4068( theApp, AMSConstants.REL1);
            rel1.SetDescription( "РЕЛЕ1");
            m_mapDevices.put( AMSConstants.REL1, rel1);
        }
        catch( Exception ex) {
            logger.fatal( "Caught exception on creating REL1 class instances", ex);
            throw new Exception( "Ошибка при инициализации РЕЛЕ1!");
        }
        
        //RELAY2
        try {
            Adam4068 rel2 = new Adam4068( theApp, AMSConstants.REL2);
            rel2.SetDescription( "РЕЛЕ2");
            m_mapDevices.put( AMSConstants.REL2, rel2);
        }
        catch( Exception ex) {
            logger.fatal( "Caught exception on creating REL2 class instances", ex);
            throw new Exception( "Ошибка при инициализации РЕЛЕ2!");
        }
        
        return true;
    }
}
