package org.usfirst.frc.team2606.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2606.robot.Robot;

public class IntakeFor extends Command {

    private double startTime;
    private int duration;

    public IntakeFor(int duration) {
        requires(Robot.intake);
        this.duration = duration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.intake.setMotors(1.0, 1.0);
    }

    protected boolean isFinished() {
        if(System.currentTimeMillis() > startTime + duration) {
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.intake.setMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
