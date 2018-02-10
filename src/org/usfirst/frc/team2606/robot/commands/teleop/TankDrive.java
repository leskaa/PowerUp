package org.usfirst.frc.team2606.robot.commands.teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2606.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {

    public TankDrive() {
        requires(Robot.drive);
        requires(Robot.lift);
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drive.move(Robot.oi.getLeftAnalogYValue(), Robot.oi.getRightAnalogYValue());

        if(Robot.oi.getRightTriggerValue()>0.5 && Robot.oi.getRightTriggerValue()>Robot.oi.getLeftTriggerValue()) {
            Robot.lift.setLiftMotor(0.5);
        } else if(Robot.oi.getLeftTriggerValue()>0.5) {
            Robot.lift.setLiftMotor(-0.5);
        } else {
            Robot.lift.setLiftMotor(0);
        }

        if(Robot.oi.getRightBumper().get()) {
            Robot.intake.setMotors(-0.5, -0.5);
        } else if(Robot.oi.getLeftBumper().get()) {
            Robot.intake.setMotors(0.5, 0.5);
        } else {
            Robot.intake.setMotors(0, 0);
        }
    }

    // TODO be able to finish?
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drive.move(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
