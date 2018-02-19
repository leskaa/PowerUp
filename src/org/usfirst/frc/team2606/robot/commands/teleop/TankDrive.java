package org.usfirst.frc.team2606.robot.commands.teleop;

import org.usfirst.frc.team2606.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2606.robot.RobotMap;

public class TankDrive extends Command {

    double startTime = 0;
    double leftDrive = 0;
    double rightDrive = 0;

    boolean isRealigning = false;
    boolean isRealigningLong = false;

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
        //TODO faster start
        //if(Robot.oi.getLeftAnalogYValue()>0.05 || Robot.oi.getLeftAnalogYValue()<-0.05) {
        //    if(Robot.oi.getLeftAnalogYValue()<0.3&&Robot.oi.getRightAnalogYValue()>-0.3){
        //        if(Robot.oi)
        //        leftDrive = 0.3;
        //    }
        //}
        //if(Robot.oi.getLeftAnalogYValue() < 0.3 && Robot.oi.getRightAnalogYValue() > -0.3) {
        //   Robot.oi.getLeftAnalogYValue()
        //}

        Robot.drive.move(Robot.oi.getLeftAnalogYValue()*-1, Robot.oi.getRightAnalogYValue()*-1);

        if(Robot.oi.getRightTriggerValue()>0.1 && Robot.oi.getRightTriggerValue()>Robot.oi.getLeftTriggerValue()) {
            Robot.lift.setLiftMotor(Robot.oi.getRightTriggerValue());
        } else if(Robot.oi.getLeftTriggerValue()>0.1) {
            Robot.lift.setLiftMotor(-1 * Robot.oi.getLeftTriggerValue());
        } else {
            Robot.lift.setLiftMotor(0);
        }

        // TODO Original code at 50% power
        /*if(Robot.oi.getRightBumper().get()) {
            Robot.intake.setMotors(-0.5, -0.5);
        } else if(Robot.oi.getLeftBumper().get()) {
            Robot.intake.setMotors(0.5, 0.5);
        } else {
            Robot.intake.setMotors(0, 0);
        }
        */

        // Test Intake Code
        if(Robot.oi.getRightBumper().get()) {
            Robot.intake.setMotors(RobotMap.intakeSpeedLeft, RobotMap.intakeSpeedRight);
        } else if(Robot.oi.getLeftBumper().get()) {
            Robot.intake.setMotors(-RobotMap.intakeSpeedLeft, -RobotMap.intakeSpeedRight);
        } else {
            Robot.intake.setMotors(0, 0);
        }

        // Realign Cube
        if(Robot.oi.getXboxA().get()) {
            startTime = System.currentTimeMillis();
            isRealigning = true;
            isRealigningLong = false;
        }

        if(Robot.oi.getXboxY().get()) {
            startTime = System.currentTimeMillis();
            isRealigning = true;
            isRealigningLong = true;
        }

        if(Robot.oi.getXboxB().get()) {
            Robot.intake.setMotors(-1, -1);
        }

        if (isRealigning) {
            if (!isRealigningLong) {
                if (System.currentTimeMillis() <= startTime + RobotMap.quickTimeOut) {
                    Robot.intake.setMotors(-0.4, -0.30);
                } else if (System.currentTimeMillis() <= startTime + RobotMap.quickTimeStop) {
                    Robot.intake.setMotors(0.0, 0.0);
                } else if (System.currentTimeMillis() <= startTime + RobotMap.quickTimeIn) {
                    Robot.intake.setMotors(RobotMap.intakeSpeedLeft, RobotMap.intakeSpeedRight);
                } else { //
                    startTime = 0;
                    isRealigning = false;
                    isRealigningLong = false;

                    Robot.intake.setMotors(0.0, 0.0);
                }
            }
            else if (isRealigningLong) {
                if (System.currentTimeMillis() <= startTime + RobotMap.longTimeOut) {
                    Robot.intake.setMotors(-0.3, -0.20);
                } else if (System.currentTimeMillis() <= startTime + RobotMap.longTimeStop) {
                    Robot.intake.setMotors(0.0, 0.0);
                } else if (System.currentTimeMillis() <= startTime + RobotMap.longTimeIn) {
                    Robot.intake.setMotors(RobotMap.intakeSpeedLeft, RobotMap.intakeSpeedRight);
                } else { //
                    startTime = 0;
                    isRealigning = false;
                    isRealigningLong = false;

                    Robot.intake.setMotors(0.0, 0.0);
                }
            }
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
