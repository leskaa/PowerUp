package org.usfirst.frc.team2606.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2606.robot.Robot;

public class BackupAndReturn extends Command{

    private double startTime;

    public BackupAndReturn() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(System.currentTimeMillis() < startTime + 200) {
            Robot.drive.move(-0.6, -0.6);
        } else if(System.currentTimeMillis() < startTime + 3000) {
            Robot.drive.move(0,0);
        }
        else if(System.currentTimeMillis() < startTime + 5400) {
            Robot.drive.move(0.5, 0.5);
        }
    }

    protected boolean isFinished() {
        if(System.currentTimeMillis() > startTime + 5400) {
            return true;
        }
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