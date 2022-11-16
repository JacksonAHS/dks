package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.WPI_CANCoder;

import frc.robot.Constants;

public class SwerveModule {
    
    private WPI_TalonFX mRotor;
    private WPI_TalonFX mThrottle;
    private WPI_CANCoder mRotorEncoder;

    public SwerveModule (int RID, int TID, int CID, boolean TR, double OffsetAngle){
        
        mRotor = new WPI_TalonFX (RID);
        mThrottle = new WPI_TalonFX (TID);
        mRotorEncoder = new WPI_CANCoder (CID);

        mRotor.configFactoryDefault();
        mThrottle.configFactoryDefault();
        mRotorEncoder.configFactoryDefault();

        mRotorEncoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180);
        mRotorEncoder.configMagnetOffset(OffsetAngle);
        mRotorEncoder.configSensorDirection(false);
        mRotorEncoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);

        mRotor.setInverted(TalonFXInvertType.Clockwise);
        mThrottle.setInverted(TR);
        
        mRotor.configVoltageCompSaturation(Constants.kVoltageCompensation);
        mThrottle.configVoltageCompSaturation(Constants.kVoltageCompensation);

        mRotor.enableVoltageCompensation(true);
        mThrottle.enableVoltageCompensation(true);

        mRotor.setNeutralMode(NeutralMode.Brake);
        mThrottle.setNeutralMode(NeutralMode.Brake);



    }

    public void ThrottleSpeed (double speed)
    {
        mThrottle.set(speed);
    }
}