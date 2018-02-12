/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2606.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2606.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Lift extends Subsystem {

	private WPI_TalonSRX liftMotor;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public Lift() {
		super();
		liftMotor = new WPI_TalonSRX(RobotMap.LIFT_MOTOR);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setLiftMotor(double speed) {
		if(speed > 0 && liftMotor.getSensorCollection().isFwdLimitSwitchClosed()) {
			speed = 0;
		}
		if(speed < 0 && liftMotor.getSensorCollection().isRevLimitSwitchClosed()) {
			speed = 0;
		}
		System.out.println("FORWARD: " + liftMotor.getSensorCollection().isFwdLimitSwitchClosed());
		System.out.println("BACKWARD: " + liftMotor.getSensorCollection().isRevLimitSwitchClosed());
		System.out.println("ENCODER: " + liftMotor.getSensorCollection().getQuadraturePosition());
		System.out.println("Speed: " + speed);
		liftMotor.set(speed);
	}
}

