package org.usfirst.frc.team2606.robot.commands.autonomous;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import org.usfirst.frc.team2606.robot.Robot;
import org.usfirst.frc.team2606.robot.RobotMap;
import org.usfirst.frc.team2606.robot.subsystems.Drive;

public abstract class PathfinderDrive extends Command {

    Waypoint[] waypoints = new Waypoint[256];

    private Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
            Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
    private Trajectory trajectory = Pathfinder.generate(waypoints, config);
    TankModifier modifier = new TankModifier(trajectory).modify(RobotMap.WHEELBASE_RATIO);
    protected EncoderFollower leftEncoderFollower = new EncoderFollower(modifier.getLeftTrajectory());
    protected EncoderFollower rightEncoderFollower = new EncoderFollower(modifier.getRightTrajectory());

    public PathfinderDrive(Waypoint startingPoint) {
        requires(Robot.drive);
        leftEncoderFollower.configureEncoder(Robot.drive.getLeftEncoderCount(), 1440, RobotMap.WHEEL_DIAMETER);
        rightEncoderFollower.configureEncoder(Robot.drive.getRightEncoderCount(), 1440, RobotMap.WHEEL_DIAMETER);
    }
    abstract Trajectory calculatePath(Waypoint startingPoint);

}
