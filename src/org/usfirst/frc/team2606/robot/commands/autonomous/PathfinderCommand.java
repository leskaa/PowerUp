package org.usfirst.frc.team2606.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2606.robot.Robot;
import org.usfirst.frc.team2606.robot.RobotMap;


public class PathfinderCommand extends Command {
    private EncoderFollower left, right;
    private Waypoint[] points;
    PathfinderCommand(Waypoint[] points) {
        this.points = points;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drive);
    }

    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC,
                Trajectory.Config.SAMPLES_LOW, 0.02, RobotMap.MAX_VELOCITY, 0.50, 60);
        Trajectory trajectory = Pathfinder.generate(points, config);
        TankModifier modifier = new TankModifier(trajectory).modify(RobotMap.WHEELBASE_RATIO);
        left = new EncoderFollower(modifier.getLeftTrajectory());
        right = new EncoderFollower(modifier.getRightTrajectory());
        left.configureEncoder(Robot.drive.getLeftEncoderCount(), 362, RobotMap.WHEEL_DIAMETER);
        right.configureEncoder(Robot.drive.getRightEncoderCount(), 252, RobotMap.WHEEL_DIAMETER);
        double prop = .5;
        double der = .5;
        left.configurePIDVA(prop, 0.0, der, 1 / RobotMap.MAX_VELOCITY, 0);
        right.configurePIDVA(prop, 0.0, der, 1 / RobotMap.MAX_VELOCITY, 0);
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        //double l = left.calculate(Robot.drive.getLeftEncoderCount());
        //double r = right.calculate(Robot.drive.getRightEncoderCount());
        //double gyroHeading = Robot.drive.getGyroRealHeading();
        //double desiredHeading = Pathfinder.r2d(left.getHeading());
        //double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
        //double turn = 0.8 * (-1.0 / 80.0) * angleDifference;
        Robot.drive.move(left.calculate(Robot.drive.getLeftEncoderCount()), right.calculate(Robot.drive.getRightEncoderCount()));
        //System.out.println("LEFT MOTOR: "+(l+turn));
        //System.out.println("RIGHT MOTOR: "+ (r-turn));
    }


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        return left.isFinished() && right.isFinished();
    }

    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        Robot.drive.move(0, 0);
    }

    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
