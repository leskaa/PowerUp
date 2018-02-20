package org.usfirst.frc.team2606.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2606.robot.Robot;
import org.usfirst.frc.team2606.robot.subsystems.Lift;

public class LiftToScaleHeight extends Command{

    private double startTime;

    public LiftToScaleHeight() {
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(System.currentTimeMillis() < startTime + 5000) {
            Robot.lift.setLiftMotor(1);
        } else {
            Robot.lift.setLiftMotor(0);
        }
    }

    protected boolean isFinished() {
        if(System.currentTimeMillis() > startTime + 5050) {
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.lift.setLiftMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}